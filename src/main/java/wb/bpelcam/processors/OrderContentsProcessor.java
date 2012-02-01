package wb.bpelcam.processors;

import java.lang.reflect.Method;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class OrderContentsProcessor implements Processor {

	@SuppressWarnings("rawtypes")
	@Override
	public void process(Exchange exchange) throws Exception {
		
		System.out.println("----");
		Object o =  exchange.getIn().getBody();

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
