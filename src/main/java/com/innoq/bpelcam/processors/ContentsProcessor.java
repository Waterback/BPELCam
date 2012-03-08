package com.innoq.bpelcam.processors;

import java.lang.reflect.Method;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ContentsProcessor implements Processor {

	@SuppressWarnings("rawtypes")
	@Override
	public void process(Exchange exchange) throws Exception {
		
		System.out.println("In: ----" + exchange.getIn().getBody());
		Object o =  exchange.getIn().getBody();
		if (o == null) {
			exchange.getIn().setBody("Empty Request");
			return;
		}
			 
		Class clazz = o.getClass();
		System.out.println("---- " + clazz.getName());
		Method[] m = clazz.getDeclaredMethods();
		for (Method method : m) {
				try {
					System.out.println("Get " + method.getName() + " :" + method.invoke(o));
				} catch (Exception e) {
				}
		}
		
	}

}
