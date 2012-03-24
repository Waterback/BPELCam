package com.innoq.bpelcam.processors;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Prints all existing Meta-Data (Headers) of the In-Message.
 * @author martinh
 *
 */
public class PrintHeaderProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("-------------- Header ------------------");
		Map<String, Object> m = (Map<String, Object>) exchange.getIn().getHeaders();
		for (String key : m.keySet()) {
			System.out.println(key + " -> " + m.get(key));
		}
		System.out.println("-------------- End Header --------------");
	}

}
