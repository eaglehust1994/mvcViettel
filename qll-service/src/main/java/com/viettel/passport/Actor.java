
package com.viettel.passport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for actor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="actor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ipLan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipWan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actor", propOrder = {
    "ipLan",
    "ipWan",
    "password",
    "userName"
})
public class Actor {

    protected String ipLan;
    protected String ipWan;
    protected String password;
    protected String userName;

    /**
     * Gets the value of the ipLan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpLan() {
        return ipLan;
    }

    /**
     * Sets the value of the ipLan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpLan(String value) {
        this.ipLan = value;
    }

    /**
     * Gets the value of the ipWan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpWan() {
        return ipWan;
    }

    /**
     * Sets the value of the ipWan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpWan(String value) {
        this.ipWan = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

}
