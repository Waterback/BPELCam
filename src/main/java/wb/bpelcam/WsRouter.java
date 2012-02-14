package wb.bpelcam;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;

import wb.bpelcam.processors.OrderContentsProcessor;

public class WsRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// --- Just for full CXF-Endpoint-Creation --- 
		
		from("cxf:bean:orderEndpoint-v1.0")
			.to("seda:handleV1_0_Orders");

		from("cxf:bean:orderEndpoint-v1.1")
			.to("seda:handleV1_1_Orders");

		// ------------ Working Webservice-Routes ----------------
		
		Namespaces ns_v10 = new Namespaces("c10", "http://bpel.innoq.com/order/v1/sv0");
		Namespaces ns_v11 = new Namespaces("c11", "http://bpel.innoq.com/order/v1/sv1");
		
		from("jetty:http://localhost:9080/orderservice?minThreads=5")
			.process(new ContentPrinter())
			.choice()
				.when().xpath("//c10:orderInformation", ns_v10)
					.to("seda:handleV1_0_Orders")
				.when().xpath("//c11:orderInformation", ns_v11)
					.to("seda:handleV1_1_Orders")
				.otherwise()
					.to("seda:fault");

		from("seda:handleV1_0_Orders")
			.id("handleOrders")
			.filter().xpath("//c10:orderInformation", ns_v10)
			.to("seda:save")
			.process(new ResponseBuilderProcessor(10));
		
		from("seda:handleV1_1_Orders")
			.id("handleOrders")
			.filter().xpath("//c11:orderInformation", ns_v11)
			.to("seda:save")
			.process(new ResponseBuilderProcessor(11));

		from("seda:save")
			.id("save")
			.process(new ContentPrinter())
			.to("file://target/output");
		
		from("seda:fault")
			.setBody(constant("FAULT!"));

	}

	public class ContentPrinter implements Processor {
		public void process(Exchange exchange) throws Exception {
			// just get the body as a string
			String body = exchange.getIn().getBody(String.class);
			System.out.println("---------------------------------------");
			System.out.println(body);
			System.out.println("---------------------------------------");
		}
	}

}
