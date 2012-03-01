package wb.bpelcam;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.innoq.bpel.insurance.v1.types.ProcessConfirmation;

public class ResponseBuilderProcessor implements Processor {

	private int version;
	
	public ResponseBuilderProcessor(int version) {
		this.version = version;
	}

	@Override
	public void process(Exchange exch) throws Exception {
		if (version == 10) {
			ProcessConfirmation pc = new ProcessConfirmation();
			pc.setProcessId(1234);
			pc.setResultCode("Accepted-V1");
			exch.getOut().setBody(pc);
		} else if (version == 11) {
			com.innoq.bpel.insurance.v2.types.ProcessConfirmation pc = new com.innoq.bpel.insurance.v2.types.ProcessConfirmation();
			pc.setProcessId(3234);
			pc.setResultCode("Accepted-V2");
			exch.getOut().setBody(pc);
		}
	}

}
