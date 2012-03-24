
package org.example.internal;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.internal package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.internal
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MakeApplicationResponse }
     * 
     */
    public MakeApplicationResponse createMakeApplicationResponse() {
        return new MakeApplicationResponse();
    }

    /**
     * Create an instance of {@link MakeApplication }
     * 
     */
    public MakeApplication createMakeApplication() {
        return new MakeApplication();
    }

    /**
     * Create an instance of {@link SendSignedDocument }
     * 
     */
    public SendSignedDocument createSendSignedDocument() {
        return new SendSignedDocument();
    }

    /**
     * Create an instance of {@link SendSignedDocumentResponse }
     * 
     */
    public SendSignedDocumentResponse createSendSignedDocumentResponse() {
        return new SendSignedDocumentResponse();
    }

}
