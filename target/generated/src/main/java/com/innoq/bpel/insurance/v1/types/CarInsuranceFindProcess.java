
package com.innoq.bpel.insurance.v1.types;

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
 *         &lt;element name="oldInsuranceCSV" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="yearswithdrivinglicense" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element ref="{http://bpel.innoq.com/insurance/v1/types}car"/>
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
    "oldInsuranceCSV",
    "customerName",
    "age",
    "yearswithdrivinglicense",
    "car"
})
@XmlRootElement(name = "CarInsuranceFindProcess")
public class CarInsuranceFindProcess {

    protected int targetVersion;
    @XmlElement(required = true)
    protected String oldInsuranceCSV;
    @XmlElement(required = true)
    protected String customerName;
    protected int age;
    protected int yearswithdrivinglicense;
    @XmlElement(required = true)
    protected Car car;

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
     * Gets the value of the oldInsuranceCSV property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldInsuranceCSV() {
        return oldInsuranceCSV;
    }

    /**
     * Sets the value of the oldInsuranceCSV property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldInsuranceCSV(String value) {
        this.oldInsuranceCSV = value;
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
     * Gets the value of the age property.
     * 
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the value of the age property.
     * 
     */
    public void setAge(int value) {
        this.age = value;
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

}
