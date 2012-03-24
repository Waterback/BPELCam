
package com.innoq.bpel.insurance.v2.types;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.innoq.bpel.insurance.v2.types package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.innoq.bpel.insurance.v2.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Car }
     * 
     */
    public Car createCar() {
        return new Car();
    }

    /**
     * Create an instance of {@link CarInsuranceFindProcess }
     * 
     */
    public CarInsuranceFindProcess createCarInsuranceFindProcess() {
        return new CarInsuranceFindProcess();
    }

    /**
     * Create an instance of {@link ProcessConfirmation }
     * 
     */
    public ProcessConfirmation createProcessConfirmation() {
        return new ProcessConfirmation();
    }

    /**
     * Create an instance of {@link OldInsurance }
     * 
     */
    public OldInsurance createOldInsurance() {
        return new OldInsurance();
    }

}
