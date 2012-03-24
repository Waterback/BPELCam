
package com.innoq.bpel.insurance.v3.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fabricants" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="horsepower" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fabricants",
    "model",
    "horsepower"
})
@XmlRootElement(name = "car")
public class Car {

    @XmlElement(required = true)
    protected String fabricants;
    @XmlElement(required = true)
    protected String model;
    @XmlElement(required = true)
    protected String horsepower;

    /**
     * Gets the value of the fabricants property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFabricants() {
        return fabricants;
    }

    /**
     * Sets the value of the fabricants property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFabricants(String value) {
        this.fabricants = value;
    }

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * Gets the value of the horsepower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorsepower() {
        return horsepower;
    }

    /**
     * Sets the value of the horsepower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorsepower(String value) {
        this.horsepower = value;
    }

}
