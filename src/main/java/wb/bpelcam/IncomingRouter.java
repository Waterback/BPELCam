package wb.bpelcam;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;

import wb.bpelcam.processors.OrderContentsProcessor;
import wb.bpelcam.processors.PrintHeaderProcessor;
import wb.bpelcam.processors.V1TransformerBean;

public class IncomingRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
 
		// ------------ Working Webservice-Routes ----------------
		
		Namespaces ns_v1 = new Namespaces("c1", "http://bpel.innoq.com/insurance/v1/types");
		Namespaces ns_v2 = new Namespaces("c2", "http://bpel.innoq.com/insurance/v2/types");
		
		DataFormat jaxb10 = new JaxbDataFormat("com.innoq.bpel.insurance.v1.types");
		DataFormat jaxb11 = new JaxbDataFormat("com.innoq.bpel.insurance.v2.types");

		
		from("jetty:http://localhost:9080/insuranceservice?minThreads=5")
//			.process(new ContentPrinter())
//			.process(new PrintHeaderProcessor())
			.choice()
				.when().xpath("//c1:CarInsuranceFindProcess", ns_v1)
					.setHeader("schemaVersion", constant("1"))
					.setHeader("targetVersion").xpath("//c1:targetVersion", String.class, ns_v1)	
					.to("seda:evaluateV1Scheme")
				.when().xpath("//c2:CarInsuranceFindProcess", ns_v2)
					.setHeader("schemaVersion", constant("2"))
					.setHeader("targetVersion").xpath("//c2:targetVersion", String.class, ns_v2)
					.to("seda:evaluateV2Scheme")
				.otherwise()
					.to("seda:fault");
	
		from("seda:evaluateV1Scheme")
			.choice()
				.when(header("targetVersion").isEqualTo("2"))
					.pipeline("direct:transformV1V2")
					.to("cxf:bean:orderEndpoint-v2")
				.otherwise()
					.to("cxf:bean:orderEndpoint-v1");

		from("seda:evaluateV2Scheme")
			.choice()
				.when(header("targetVersion").isEqualTo("1"))
					
					.to("cxf:bean:orderEndpoint-v1")
				.otherwise()
					.to("cxf:bean:orderEndpoint-v2");
		
		from("cxf:bean:orderEndpoint-v1")
			.id("handleProcess_v1")
			.transform().xpath("//c1:CarInsuranceFindProcess", ns_v1)
			.unmarshal(jaxb10)
//			.process(new OrderContentsProcessor())
			.marshal(jaxb10)
			.to("seda:save")
			.process(new ResponseBuilderProcessor(10));

		from("cxf:bean:orderEndpoint-v2")
			.id("handleProcess_v2")
			.transform().xpath("//c2:CarInsuranceFindProcess", ns_v2)
//			.process(new ContentPrinter())
			.unmarshal(jaxb11)
//			.process(new OrderContentsProcessor())
			.marshal(jaxb11)
			.to("seda:save")
			.process(new ResponseBuilderProcessor(11));


		from("seda:save")
			.id("save")
			.process(new ContentPrinter())
			.to("file://target/output");
		
		from("seda:fault")
			.setBody(constant("FAULT!"));

		
		from("direct:transformV1V2")
			.bean(new V1TransformerBean(), "transformV1toV2");
		
	}

}
