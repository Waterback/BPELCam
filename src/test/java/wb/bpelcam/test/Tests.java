package wb.bpelcam.test;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class Tests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CamelContext ctxt = new DefaultCamelContext();
		ConsumerTemplate consumer = ctxt.createConsumerTemplate();
		String s = consumer.receiveBody("file:target/output?delete=true&initialDelay=0&fileName=Process_xyxZ.pdf.txt", 500, String.class);
		System.out.println(s);
	}

}
