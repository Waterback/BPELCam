package com.innoq.bpel.order.v1.sv1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.3.9
 * 2012-02-15T00:26:32.023+01:00
 * Generated source version: 2.3.9
 * 
 */
@WebService(targetNamespace = "http://bpel.innoq.com/order/v1/sv1", name = "OrderEndpoint")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface OrderEndpoint {

    @WebResult(name = "orderConfirmation", targetNamespace = "http://bpel.innoq.com/order/v1/sv1", partName = "resultCode")
    @WebMethod(action = "http://bpel.innoq.com/Order")
    public OrderConfirmation order(
        @WebParam(partName = "parameters", name = "orderInformation", targetNamespace = "http://bpel.innoq.com/order/v1/sv1")
        OrderInformation parameters
    );
}