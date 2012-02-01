
/*
 * 
 */

package com.innoq.bpel;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.10
 * Mon Jan 30 23:11:20 CET 2012
 * Generated source version: 2.2.10
 * 
 */


@WebServiceClient(name = "OrderEndpointService", 
                  wsdlLocation = "file:/Users/martinh/DEV/CAMELSTUFF/workspaces/camelws/bpelcam/src/main/resources/wsdl/order.wsdl",
                  targetNamespace = "http://bpel.innoq.com") 
public class OrderEndpointService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://bpel.innoq.com", "OrderEndpointService");
    public final static QName OrderService = new QName("http://bpel.innoq.com", "OrderService");
    static {
        URL url = null;
        try {
            url = new URL("file:/Users/martinh/DEV/CAMELSTUFF/workspaces/camelws/bpelcam/src/main/resources/wsdl/order.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:/Users/martinh/DEV/CAMELSTUFF/workspaces/camelws/bpelcam/src/main/resources/wsdl/order.wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public OrderEndpointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public OrderEndpointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OrderEndpointService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     * 
     * @return
     *     returns OrderEndpoint
     */
    @WebEndpoint(name = "OrderService")
    public OrderEndpoint getOrderService() {
        return super.getPort(OrderService, OrderEndpoint.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OrderEndpoint
     */
    @WebEndpoint(name = "OrderService")
    public OrderEndpoint getOrderService(WebServiceFeature... features) {
        return super.getPort(OrderService, OrderEndpoint.class, features);
    }

}