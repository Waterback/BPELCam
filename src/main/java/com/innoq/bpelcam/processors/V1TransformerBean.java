package com.innoq.bpelcam.processors;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.camel.Body;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.filter.ElementFilter;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class V1TransformerBean {

	Namespace ns_v1 = Namespace.getNamespace("c1",
			"http://bpel.innoq.com/insurance/v1/types");
	Namespace ns_v2 = Namespace.getNamespace("c1",
			"http://bpel.innoq.com/insurance/v2/types");

	@SuppressWarnings({ "rawtypes" })
	public String transformV1toV2(@Body String body) throws JDOMException, IOException {

		Element message = parse(body);
		message.addNamespaceDeclaration(ns_v2);
		Iterator it = message.getDescendants(new ElementFilter());
		Map<Element, Element[]> replacables = new HashMap<Element, Element[]>();
		while (it.hasNext()) {
			Element ele = (Element) it.next();
			if (ele.getName().equals("oldInsuranceCSV")) {
				Pair<Element, Element[]> pair = transformInsuranceLine(ele);
				replacables.put(pair.fst, pair.snd);
			} else {
				ele.setNamespace(ns_v2);
			}
		}
		handleReplacables(replacables);
		System.out.println(toString(message));
		return toString(message);
	}

	private Pair<Element, Element[]> transformInsuranceLine(Element line) {
		String linecontent = line.getText();
		Element newElementForOldInsurance = new Element("oldInsurance", ns_v2);
		String[] value = linecontent.split("-");
		for (String s : value) {
			Element se = new Element("insuranceSpecifications", ns_v2);
			se.setText(s);
			newElementForOldInsurance.addContent(se);
		}
		return new Pair<Element, Element[]>(line, new Element[] {newElementForOldInsurance}) ;
	}

	private void handleReplacables(Map<Element, Element[]> replacables) {
		for (Element element : replacables.keySet()) {
			Element p = element.getParentElement();
			p.removeContent(element);
			p.addContent(Arrays.asList(replacables.get(element)));
		}
	}

	public Element parse(String txt) throws JDOMException, IOException {
		org.jdom.input.SAXBuilder b = new SAXBuilder();
		return b.build(new java.io.StringReader(txt)).getRootElement();
	}

	public static String toString(Element e) {
		org.jdom.output.XMLOutputter out = new XMLOutputter();
		return out.outputString(e);
	}

	public static void main(String[] args) throws Exception {
		V1TransformerBean bean = new V1TransformerBean();
		String xml = "<?xml version='1.0' ?> "
				+ "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:typ='http://bpel.innoq.com/insurance/v1/types' >"
				+ "  <soapenv:Header/> "
				+ "   <soapenv:Body> "
				+ "        <typ:CarInsuranceFindProcess>"
				+ "  <typ:targetVersion>2</typ:targetVersion>"
				+ "   <typ:oldInsuranceCSV>HDI-70-80-VK350</typ:oldInsuranceCSV>"
				+ "    <typ:customerName>Ich</typ:customerName>"
				+ "     <typ:age>35</typ:age>"
				+ "      <typ:yearswithdrivinglicense>17</typ:yearswithdrivinglicense>"
				+ "       <typ:car>"
				+ "            <typ:fabricant>Mercedes</typ:fabricant>"
				+ "             <typ:model>B 180</typ:model>"
				+ "              <typ:horsepower>140</typ:horsepower>"
				+ "          </typ:car>"
				+ "       </typ:CarInsuranceFindProcess>"
				+ "   </soapenv:Body>" + "</soapenv:Envelope>   ";
		bean.transformV1toV2(xml);

	}

	public static class Pair<T, E> {
		public Pair(T fst, E snd) {
			this.fst = fst;
			this.snd = snd;
		}

		public T fst;
		public E snd;
	}

}
