package wb.bpelcam.processors;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PrintHeaderProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Map<String, Object> m = (Map<String, Object>) exchange.getIn().getHeaders();
		for (String key : m.keySet()) {
			System.out.println(key + " -> " + m.get(key));
		}
	}

}
