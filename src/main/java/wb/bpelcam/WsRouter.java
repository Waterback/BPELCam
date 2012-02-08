package wb.bpelcam;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;

import wb.bpelcam.processors.OrderContentsProcessor;
import wb.bpelcam.processors.PrintHeaderProcessor;

import com.innoq.bpel.order.types.v1.OrderConfirmation;
import com.innoq.bpel.order.types.v1.OrderInformation;



public class WsRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		DataFormat jaxb = new JaxbDataFormat("com.innoq.bpel.order.types.v1");
		
			from("cxf:bean:orderEndpoint-v1.0")
				.process(new PrintHeaderProcessor())
				.convertBodyTo(OrderInformation.class)
				.setHeader("CamelFileName", constant("Order1.0"))
				.to("seda:handleV1_Orders");				

			from("cxf:bean:orderEndpoint-v1.1")
				.process(new PrintHeaderProcessor())
				.convertBodyTo(OrderInformation.class)
				.setHeader("CamelFileName", constant("Order1.1"))
				.to("seda:handleV1_Orders");				

			
			from("seda:handleV1_Orders")
				.to("seda:save")
				.process(new Processor() {
					@Override
					public void process(Exchange arg0) throws Exception {
						OrderConfirmation oc = new OrderConfirmation();
						oc.setOrderiD(1);
						oc.setResultCode("OK");
						arg0.getOut().setBody(oc);
					}
				});
			
			from("seda:save")
				.process(new OrderContentsProcessor())
				.marshal(jaxb)
				.to("file://target/output");
		

		
	}

}
