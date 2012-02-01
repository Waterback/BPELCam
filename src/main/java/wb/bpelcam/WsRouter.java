package wb.bpelcam;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import wb.bpelcam.processors.OrderContentsProcessor;
import wb.bpelcam.processors.PrintHeaderProcessor;

import com.innoq.bpel.OrderInformation1;
import com.innoq.bpel.OrderInformation2;



public class WsRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
			from("cxf:bean:orderEndpoint")
				.process(new PrintHeaderProcessor())
				.choice()
					.when(header("operationname").isEqualTo("order1"))
						.to("seda:handleV1Orders")
						.transform(constant("OK"))
					.when(header("operationname").isEqualTo("order2"))
						.to("seda:handleV2Orders")
						.transform(constant("OK"))
					.otherwise()
						.setBody(constant("Wrong Version!"));
			
			from("seda:handleV1Orders")
				.convertBodyTo(OrderInformation1.class)
				.process(new OrderContentsProcessor());
		
			from("seda:handleV2Orders")
				.convertBodyTo(OrderInformation2.class)
				.process(new OrderContentsProcessor());

		
	}

}
