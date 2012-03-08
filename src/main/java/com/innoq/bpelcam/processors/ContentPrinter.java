package com.innoq.bpelcam.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ContentPrinter implements Processor {
	public void process(Exchange exchange) throws Exception {
		// just get the body as a string
		String body = exchange.getIn().getBody(String.class);
		System.out.println("-------------- Body ------------------");
		System.out.println(body);
		System.out.println("-------------- End Body --------------");
		
		
	}
}