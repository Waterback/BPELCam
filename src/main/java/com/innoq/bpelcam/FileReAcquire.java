package com.innoq.bpelcam;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.binding.soap.SoapFault;

import com.innoq.bpelcam.processors.ContentPrinter;
import com.innoq.bpelcam.processors.ReAcquireProcessor;

/**
 * This Routebuilder takes care for the internal->Camel->Outgoing Exchange.
 * It receives a cxf-Request from internal. The File from Reference
 * is going to get picked up through the ReAcquireProcessor and the Exchange
 * is altered for forwarding it to the external Webservice. 
 * For Test & Example is also in here, but only saves the Exchange to file.
 *  
 * @author martinh
 *
 */
public class FileReAcquire extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		onException(SoapFault.class)
			.maximumRedeliveries(0).handled(true)
			.process(new SoapFaultProcessor());
		
		from("cxf:bean:internalMakeApplication??headerFilterStrategy=#dropAllMessageHeadersStrategy")
			.process(new ContentPrinter())
			.process(new ReAcquireProcessor())
			.process(new ContentPrinter())
			.to("seda:callExternal")
			.setBody(constant("OK"));
					
		from("seda:callExternal")
			.to("cxf:bean:externalMakeApplication");
		
		from("cxf:bean:externalMakeApplication")
			.convertBodyTo(String.class)
			.to("seda:out");
		
		from("seda:out")
			.to("file:target/output/external");
		
	}
	
	public class SoapFaultProcessor implements Processor {
		
		@Override
		public void process(Exchange exchange) throws Exception {
			SoapFault fault = exchange
            	.getProperty(Exchange.EXCEPTION_CAUGHT, SoapFault.class);
				exchange.getOut().setFault(true);
				exchange.getOut().setBody(fault);
		}

}

	

}
