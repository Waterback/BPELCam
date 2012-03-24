package com.innoq.bpelcam.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.example.internal.SendSignedDocument;

/**
 * Camel-Processor: Generates a forwardable-JAXB-Object. In this simple example it's main
 * Info comes out of the Camel-Header for the Camel-Filename, which is a Reference for Future use
 * in "Internal".
 * 
 * @author martinh
 *
 */
public class ExternalToInternalConverter implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String fileNameHeader = (String)exchange.getIn().getHeader("CamelFileName");
		SendSignedDocument forwardedDocument = new SendSignedDocument();
		forwardedDocument.setDocumentRef(fileNameHeader);
		exchange.getIn().setBody(forwardedDocument);
		
	}
	
	

}
