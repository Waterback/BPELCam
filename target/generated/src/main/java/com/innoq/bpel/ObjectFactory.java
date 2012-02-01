
package com.innoq.bpel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.innoq.bpel package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ResultCode_QNAME = new QName("http://bpel.innoq.com", "resultCode");
    private final static QName _Version_QNAME = new QName("http://bpel.innoq.com", "version");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.innoq.bpel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OrderInformation2 }
     * 
     */
    public OrderInformation2 createOrderInformation2() {
        return new OrderInformation2();
    }

    /**
     * Create an instance of {@link OrderInformation1 }
     * 
     */
    public OrderInformation1 createOrderInformation1() {
        return new OrderInformation1();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bpel.innoq.com", name = "resultCode")
    public JAXBElement<String> createResultCode(String value) {
        return new JAXBElement<String>(_ResultCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bpel.innoq.com", name = "version")
    public JAXBElement<Integer> createVersion(Integer value) {
        return new JAXBElement<Integer>(_Version_QNAME, Integer.class, null, value);
    }

}
