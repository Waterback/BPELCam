
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
 *         &lt;element name="targetVersion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="customerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addressline" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="yearswithdrivinglicense" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element ref="{http://bpel.innoq.com/insurance/v3/types}car"/>
 *         &lt;element ref="{http://bpel.innoq.com/insurance/v3/types}oldInsurance"/>
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
    "targetVersion",
    "customerName",
    "addressline",
    "yearswithdrivinglicense",
    "car",
    "oldInsurance"
})
@XmlRootElement(name = "CarInsuranceFindProcess")
public class CarInsuranceFindProcess {

    protected int targetVersion;
    @XmlElement(required = true)
    protected String customerName;
    @XmlElement(required = true)
    protected String addressline;
    protected int yearswithdrivinglicense;
    @XmlElement(required = true)
    protected Car car;
    @XmlElement(required = true)
    protected OldInsurance oldInsurance;

    /**
     * Gets the value of the targetVersion property.
     * 
     */
    public int getTargetVersion() {
        return targetVersion;
    }

    /**
     * Sets the value of the targetVersion property.
     * 
     */
    public void setTargetVersion(int value) {
        this.targetVersion = value;
    }

    /**
     * Gets the value of the customerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the value of the customerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerName(String value) {
        this.customerName = value;
    }

    /**
     * Gets the value of the addressline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressline() {
        return addressline;
    }

    /**
     * Sets the value of the addressline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressline(String value) {
        this.addressline = value;
    }

    /**
     * Gets the value of the yearswithdrivinglicense property.
     * 
     */
    public int getYearswithdrivinglicense() {
        return yearswithdrivinglicense;
    }

    /**
     * Sets the value of the yearswithdrivinglicense property.
     * 
     */
    public void setYearswithdrivinglicense(int value) {
        this.yearswithdrivinglicense = value;
    }

    /**
     * Gets the value of the car property.
     * 
     * @return
     *     possible object is
     *     {@link Car }
     *     
     */
    public Car getCar() {
        return car;
    }

    /**
     * Sets the value of the car property.
     * 
     * @param value
     *     allowed object is
     *     {@link Car }
     *     
     */
    public void setCar(Car value) {
        this.car = value;
    }

    /**
     * Gets the value of the oldInsurance property.
     * 
     * @return
     *     possible object is
     *     {@link OldInsurance }
     *     
     */
    public OldInsurance getOldInsurance() {
        return oldInsurance;
    }

    /**
     * Sets the value of the oldInsurance property.
     * 
     * @param value
     *     allowed object is
     *     {@link OldInsurance }
     *     
     */
    public void setOldInsurance(OldInsurance value) {
        this.oldInsurance = value;
    }

}
