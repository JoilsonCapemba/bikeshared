//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.08.03 at 07:22:15 AM WAT 
//


package com.uan.interfaces;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="capacity" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="availableBikes" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="availableDocks" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="deliveryBonus" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="dockItem" type="{http://interfaces.uan.com}DockType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StationType", propOrder = {
    "id",
    "name",
    "latitude",
    "longitude",
    "capacity",
    "availableBikes",
    "availableDocks",
    "deliveryBonus",
    "dockItem"
})
public class StationType {

    protected long id;
    @XmlElement(required = true)
    protected String name;
    protected double latitude;
    protected double longitude;
    protected int capacity;
    protected int availableBikes;
    protected int availableDocks;
    protected int deliveryBonus;
    @XmlElement(required = true)
    protected List<DockType> dockItem;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the latitude property.
     * 
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     */
    public void setLatitude(double value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     */
    public void setLongitude(double value) {
        this.longitude = value;
    }

    /**
     * Gets the value of the capacity property.
     * 
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the value of the capacity property.
     * 
     */
    public void setCapacity(int value) {
        this.capacity = value;
    }

    /**
     * Gets the value of the availableBikes property.
     * 
     */
    public int getAvailableBikes() {
        return availableBikes;
    }

    /**
     * Sets the value of the availableBikes property.
     * 
     */
    public void setAvailableBikes(int value) {
        this.availableBikes = value;
    }

    /**
     * Gets the value of the availableDocks property.
     * 
     */
    public int getAvailableDocks() {
        return availableDocks;
    }

    /**
     * Sets the value of the availableDocks property.
     * 
     */
    public void setAvailableDocks(int value) {
        this.availableDocks = value;
    }

    /**
     * Gets the value of the deliveryBonus property.
     * 
     */
    public int getDeliveryBonus() {
        return deliveryBonus;
    }

    /**
     * Sets the value of the deliveryBonus property.
     * 
     */
    public void setDeliveryBonus(int value) {
        this.deliveryBonus = value;
    }

    /**
     * Gets the value of the dockItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the dockItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDockItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DockType }
     * 
     * 
     */
    public List<DockType> getDockItem() {
        if (dockItem == null) {
            dockItem = new ArrayList<DockType>();
        }
        return this.dockItem;
    }

}
