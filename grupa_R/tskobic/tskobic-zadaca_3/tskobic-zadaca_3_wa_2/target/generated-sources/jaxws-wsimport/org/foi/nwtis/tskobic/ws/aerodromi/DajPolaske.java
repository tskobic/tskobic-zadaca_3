
package org.foi.nwtis.tskobic.ws.aerodromi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dajPolaske complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dajPolaske"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="icao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dajPolaske", propOrder = {
    "icao",
    "dan"
})
public class DajPolaske {

    protected String icao;
    protected String dan;

    /**
     * Gets the value of the icao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIcao() {
        return icao;
    }

    /**
     * Sets the value of the icao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIcao(String value) {
        this.icao = value;
    }

    /**
     * Gets the value of the dan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDan() {
        return dan;
    }

    /**
     * Sets the value of the dan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDan(String value) {
        this.dan = value;
    }

}
