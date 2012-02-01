package com.innoq.bpel;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.2.10
 * Mon Jan 30 23:11:20 CET 2012
 * Generated source version: 2.2.10
 * 
 */
 
@WebService(targetNamespace = "http://bpel.innoq.com", name = "OrderEndpoint")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface OrderEndpoint {

    @WebResult(name = "resultCode", targetNamespace = "http://bpel.innoq.com", partName = "resultCode")
    @WebMethod(action = "http://bpel.innoq.com/Order")
    public java.lang.String order1(
        @WebParam(partName = "parameters", name = "orderInformation1", targetNamespace = "http://bpel.innoq.com")
        OrderInformation1 parameters
    );

    @WebResult(name = "resultCode", targetNamespace = "http://bpel.innoq.com", partName = "resultCode")
    @WebMethod(action = "http://bpel.innoq.com/Order")
    public java.lang.String order2(
        @WebParam(partName = "parameters", name = "orderInformation2", targetNamespace = "http://bpel.innoq.com")
        OrderInformation2 parameters
    );
}
