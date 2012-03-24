package com.innoq.bpelcam;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;

import com.innoq.bpelcam.processors.ContentPrinter;
import com.innoq.bpelcam.processors.PrintHeaderProcessor;
import com.innoq.bpelcam.processors.V1TransformerBean;

/**
 * This takes webservice-calls via a Camel-Http-Endpoint - Analyzes the Schema and forwards to 
 * the actual webservice of BPMS or other Service-Providers (here only in example).
 * 
 * 
 * @author martinh
 *
 */
public class SchemaEvalutionRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
 
		// ------------ Namespace-Definition ----------------
		Namespaces ns_v1 = new Namespaces("c1", "http://bpel.innoq.com/insurance/v1/types");
		Namespaces ns_v2 = new Namespaces("c2", "http://bpel.innoq.com/insurance/v2/types");
	
		from("jetty:http://0.0.0.0:9080/insuranceservice?minThreads=5")
			.process(new ContentPrinter())
			.process(new PrintHeaderProcessor())
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
					.to("cxf:bean:insuranceEndpoint-v2")
				.otherwise()
					.to("cxf:bean:insuranceEndpoint-v1");

		from("seda:evaluateV2Scheme")
			.choice()
				.when(header("targetVersion").isEqualTo("1"))
					// Conversion V2->V1 missing...
					.to("cxf:bean:insuranceEndpoint-v1")
				.otherwise()
					.to("cxf:bean:insuranceEndpoint-v2");
		
		from("seda:fault")
			.setBody(constant("FAULT!"));

		
		from("direct:transformV1V2")
			.bean(new V1TransformerBean(), "transformV1toV2");
		
	}

}
