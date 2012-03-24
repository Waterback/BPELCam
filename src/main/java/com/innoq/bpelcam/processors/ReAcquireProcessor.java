package com.innoq.bpelcam.processors;

import java.io.IOException;
import java.util.Iterator;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.converter.stream.InputStreamCache;
import org.apache.cxf.binding.soap.SoapFault;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.filter.ElementFilter;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * Converts a internal-SOAP-Message to external-SOAP Message and
 * resolving the documentReference and loading the actual document.
 * The latter is done in
 * <code>
 * private String pickUpFileFromEntrePot()
 * </code>
 * The rest-implementation takes care for the xml-contents.
 * 
 * @author martinh
 *
 */
public class ReAcquireProcessor implements Processor {

	ConsumerTemplate consumer;
	private static final Namespace ns_ext = Namespace.getNamespace("ext",
			"http://www.example.org/external/");
	private static final Namespace ns_int = Namespace.getNamespace("ext",
			"http://www.example.org/internal/");

	org.jdom.output.XMLOutputter out = new XMLOutputter();

	private static final String EXCEPTION_MESSAGE = "No Document Found!";

	@Override
	@SuppressWarnings({ "rawtypes" })
	public void process(Exchange exch) throws Exception {

		Element message = parse(exch.getIn().getBody(InputStreamCache.class));
		createConsumer(exch.getContext());
		Iterator it = message.getDescendants(new ElementFilter());
		Element documentRef = null;
		while (it.hasNext()) {
			Element ele = (Element) it.next();
			if (ele.getName().equals("documentRef")) {
				documentRef = ele;
			} else if (ele.getName().equals("makeApplication")) {
				ele.setNamespace(ns_ext);
			}
		}
		if (documentRef != null) {
			String filebytes = pickUpFileFromEntrePot(documentRef.getText());
			Element doc = new Element("document");
			doc.setText(filebytes);
			createElementAndReplaceRef(documentRef, doc);
		}
		message.removeNamespaceDeclaration(ns_int);
		message.addNamespaceDeclaration(ns_ext);

		exch.getIn().setBody(toString(message));

	}

	private String pickUpFileFromEntrePot(String text) throws SoapFault {
		String fileEndpoint = "file:target/output?delete=true&initialDelay=0&"
				+ "fileName=" + text;

		String filebytes = consumer.receiveBody(fileEndpoint, 5000,
				String.class);

		if (filebytes != null) {
			return filebytes;
		} else  {
			throw new SoapFault(EXCEPTION_MESSAGE, SoapFault.FAULT_CODE_CLIENT);
		}
	}

	private void createElementAndReplaceRef(Element docref, Element doc) {
		Element p = docref.getParentElement();
		p.removeContent(docref);
		p.addContent(doc);

	}

	public Element parse(InputStreamCache isc) throws JDOMException,
			IOException {
		org.jdom.input.SAXBuilder b = new SAXBuilder();
		return b.build(isc).getRootElement();
	}

	public static byte[] convert2SimpleByteArray(Byte[] bytes) {
		byte[] raw;
		raw = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			raw[i] = bytes[i].byteValue();
		}
		return raw;
	}

	private ConsumerTemplate createConsumer(CamelContext context) {
		if (consumer == null) {
			synchronized (ReAcquireProcessor.class) {
				if (consumer == null) {
					consumer = context.createConsumerTemplate();
				}
			}
		}
		return consumer;
	}

	public String toString(Element e) {
		org.jdom.output.XMLOutputter out = new XMLOutputter();
		return out.outputString(e);
	}

}
