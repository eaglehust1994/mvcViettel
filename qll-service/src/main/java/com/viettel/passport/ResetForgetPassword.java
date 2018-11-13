
package com.viettel.passport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for resetForgetPassword complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="resetForgetPassword">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secretPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newPass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repeatPass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isConfirmSecretPassword" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resetForgetPassword", propOrder = {
    "userName",
    "secretPassword",
    "newPass",
    "repeatPass",
    "isConfirmSecretPassword"
})
public class ResetForgetPassword {

    protected String userName;
    protected String secretPassword;
    protected String newPass;
    protected String repeatPass;
    protected boolean isConfirmSecretPassword;

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

    /**
     * Gets the value of the secretPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecretPassword() {
        return secretPassword;
    }

    /**
     * Sets the value of the secretPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecretPassword(String value) {
        this.secretPassword = value;
    }

    /**
     * Gets the value of the newPass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewPass() {
        return newPass;
    }

    /**
     * Sets the value of the newPass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewPass(String value) {
        this.newPass = value;
    }

    /**
     * Gets the value of the repeatPass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepeatPass() {
        return repeatPass;
    }

    /**
     * Sets the value of the repeatPass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepeatPass(String value) {
        this.repeatPass = value;
    }

    /**
     * Gets the value of the isConfirmSecretPassword property.
     * 
     */
    public boolean isIsConfirmSecretPassword() {
        return isConfirmSecretPassword;
    }

    /**
     * Sets the value of the isConfirmSecretPassword property.
     * 
     */
    public void setIsConfirmSecretPassword(boolean value) {
        this.isConfirmSecretPassword = value;
    }

}
