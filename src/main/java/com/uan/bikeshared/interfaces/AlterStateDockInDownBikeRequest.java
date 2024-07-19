//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.07.19 at 07:36:33 PM WAT 
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
 *         &lt;element name="stationId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="dockId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="info" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "stationId",
    "dockId",
    "state",
    "info"
})
@XmlRootElement(name = "alterStateDockInDownBikeRequest")
public class AlterStateDockInDownBikeRequest {

    protected long stationId;
    protected long dockId;
    @XmlElement(required = true)
    protected String state;
    @XmlElement(required = true)
    protected String info;

    /**
     * Gets the value of the stationId property.
     * 
     */
    public long getStationId() {
        return stationId;
    }

    /**
     * Sets the value of the stationId property.
     * 
     */
    public void setStationId(long value) {
        this.stationId = value;
    }

    /**
     * Gets the value of the dockId property.
     * 
     */
    public long getDockId() {
        return dockId;
    }

    /**
     * Sets the value of the dockId property.
     * 
     */
    public void setDockId(long value) {
        this.dockId = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the info property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets the value of the info property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfo(String value) {
        this.info = value;
    }

}
