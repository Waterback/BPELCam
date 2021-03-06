package org.example.external;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.3.9
 * 2012-03-22T23:47:26.634+01:00
 * Generated source version: 2.3.9
 * 
 */
@WebService(targetNamespace = "http://www.example.org/external/", name = "Authority")
@XmlSeeAlso({ObjectFactory.class})
public interface Authority {

    @RequestWrapper(localName = "makeApplication", targetNamespace = "http://www.example.org/external/", className = "org.example.external.MakeApplication")
    @WebMethod(action = "http://www.example.org/external/makeApplication")
    @ResponseWrapper(localName = "makeApplicationResponse", targetNamespace = "http://www.example.org/external/", className = "org.example.external.MakeApplicationResponse")
    public void makeApplication(
        @WebParam(name = "processId", targetNamespace = "")
        java.lang.String processId,
        @WebParam(name = "someData", targetNamespace = "")
        java.lang.String someData,
        @WebParam(name = "document", targetNamespace = "")
        byte[] document
    );
}
