package com.innoq.bpelcam;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;

import com.innoq.bpelcam.processors.ContentPrinter;
import com.innoq.bpelcam.processors.ExternalResponse;
import com.innoq.bpelcam.processors.ExternalToInternalConverter;

/**
 * The FileEntrePot- (Entrepot is a rather funny word for "Temporary Storage")
 * Routebuilder provides a Webservice-Endpoint 
 * <code> 
 * from("cxf:bean:externalSendSignedDocument")
 * </code>
 * defined in the Spring-Configuration-File. A filename is taken from a processid,
 * which is assumed to be unique in a BPMS.<br>
 * The file storage and forwarding to internal is done asynchronous, because of the
 * following seda-routes.<br>
 * seda:handlefile finally stores the file on disk. 
 * <code>
 *  .multicast()
 *				.to("seda:handlefile", "seda:forwardmessage")
 * </code>
 * 
 * @author martinh
 *
 */
public class FileEntrePost extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		Namespaces ns_external = new Namespaces("ext", "http://www.example.org/external/");
				
		from("cxf:bean:externalSendSignedDocument")
			.process(new ContentPrinter())
			.setHeader("processid").xpath("//ext:sendSignedDocument/processId/text()", ns_external)
			.setHeader("CamelFileName", simple("Process_${in.header.processid}.pdf.txt"))
			.multicast()
				.to("seda:handlefile", "seda:forwardmessage")
			// The following creates the Object of Response-Type, as the Out-Body 
			// and sends it directly back as Response to Requestor
			.bean(ExternalResponse.class, "createSSDResponse");  
		
		from("seda:handlefile")
			.transform().xpath("//ext:sendSignedDocument/pdf/text()", ns_external	)
			.to("file:target/output");
		
		from("seda:forwardmessage")
			.process(new ExternalToInternalConverter())
			.to("cxf:bean:internalSendSignedDocument"); 

		
		from("cxf:bean:internalSendSignedDocument")
			.process(new ContentPrinter())
			.to("file:target/output/internal");
		
		
	}
	

	

}
