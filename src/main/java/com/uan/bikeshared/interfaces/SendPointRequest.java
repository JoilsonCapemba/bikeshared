//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.06.12 at 12:42:19 PM WAT 
//


package com.uan.bikeshared.interfaces;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="userFromId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="wifiCodigUserReceiver" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="balance" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userFromId",
    "wifiCodigUserReceiver",
    "balance",
    "userName"
})
@XmlRootElement(name = "sendPointRequest")
public class SendPointRequest {

    protected long userFromId;
    @XmlElement(required = true)
    protected String wifiCodigUserReceiver;
    protected int balance;
    @XmlElement(required = true)
    protected String userName;

    /**
     * Gets the value of the userFromId property.
     * 
     */
    public long getUserFromId() {
        return userFromId;
    }

    /**
     * Sets the value of the userFromId property.
     * 
     */
    public void setUserFromId(long value) {
        this.userFromId = value;
    }

    /**
     * Gets the value of the wifiCodigUserReceiver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWifiCodigUserReceiver() {
        return wifiCodigUserReceiver;
    }

    /**
     * Sets the value of the wifiCodigUserReceiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWifiCodigUserReceiver(String value) {
        this.wifiCodigUserReceiver = value;
    }

    /**
     * Gets the value of the balance property.
     * 
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Sets the value of the balance property.
     * 
     */
    public void setBalance(int value) {
        this.balance = value;
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
