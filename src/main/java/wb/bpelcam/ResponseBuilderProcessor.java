package wb.bpelcam;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResponseBuilderProcessor implements Processor {

	private int version;
	
	public ResponseBuilderProcessor(int version) {
		this.version = version;
	}

	@Override
	public void process(Exchange exch) throws Exception {
		if (version == 10) {
			com.innoq.bpel.order.v1.sv0.OrderConfirmation oc = new com.innoq.bpel.order.v1.sv0.OrderConfirmation();
			oc.setOrderiD(1);
			oc.setResultCode("OK");
			exch.getOut().setBody(oc);
		} else if (version == 11) {
			com.innoq.bpel.order.v1.sv1.OrderConfirmation oc = new com.innoq.bpel.order.v1.sv1.OrderConfirmation();
			oc.setOrderiD(1);
			oc.setResultCode("OK");
			exch.getOut().setBody(oc);
		}
	}

}
