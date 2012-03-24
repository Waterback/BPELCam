package com.innoq.bpelcam;



import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;

import com.innoq.bpelcam.processors.ContentPrinter;
import com.innoq.bpelcam.processors.ContentsProcessor;
import com.innoq.bpelcam.processors.ResponseBuilderProcessor;

/**
 * This Routebuilder here just takes the role of external Service-Providers, providing
 * Webservice-Endpoints for the test.
 * 
 * @author martinh
 *
 */
public class WebservicesRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		DataFormat jaxb10 = new JaxbDataFormat("com.innoq.bpel.insurance.v1.types");
		DataFormat jaxb11 = new JaxbDataFormat("com.innoq.bpel.insurance.v2.types");
		
		Namespaces ns_v1 = new Namespaces("c1", "http://bpel.innoq.com/insurance/v1/types");
		Namespaces ns_v2 = new Namespaces("c2", "http://bpel.innoq.com/insurance/v2/types");
		
		from("cxf:bean:insuranceEndpoint-v1")
			.id("handleProcess_v1")
			.transform().xpath("//c1:CarInsuranceFindProcess", ns_v1)
			.unmarshal(jaxb10)
			.process(new ContentsProcessor())
			.marshal(jaxb10)
			.to("seda:save")
			.process(new ResponseBuilderProcessor(10));

		from("cxf:bean:insuranceEndpoint-v2")
			.id("handleProcess_v2")
			.transform().xpath("//c2:CarInsuranceFindProcess", ns_v2)
	//		.process(new ContentPrinter())
			.unmarshal(jaxb11)
			.process(new ContentsProcessor())
			.marshal(jaxb11)
			.to("seda:save")
			.process(new ResponseBuilderProcessor(11));

		from("seda:save")
			.id("save")
			.process(new ContentPrinter())
			.to("file://target/output");

	}
		
}
