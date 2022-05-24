
package org.foi.nwtis.tskobic.ws.aerodromi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dajNajbliziAerodrom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dajNajbliziAerodrom"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lokacija" type="{http://ws.zadaca_3.tskobic.nwtis.foi.org/}lokacija" minOccurs="0"/&gt;
 *         &lt;element name="vrsta" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dajNajbliziAerodrom", propOrder = {
    "lokacija",
    "vrsta"
})
public class DajNajbliziAerodrom {

    protected Lokacija lokacija;
    protected boolean vrsta;

    /**
     * Gets the value of the lokacija property.
     * 
     * @return
     *     possible object is
     *     {@link Lokacija }
     *     
     */
    public Lokacija getLokacija() {
        return lokacija;
    }

    /**
     * Sets the value of the lokacija property.
     * 
     * @param value
     *     allowed object is
     *     {@link Lokacija }
     *     
     */
    public void setLokacija(Lokacija value) {
        this.lokacija = value;
    }

    /**
     * Gets the value of the vrsta property.
     * 
     */
    public boolean isVrsta() {
        return vrsta;
    }

    /**
     * Sets the value of the vrsta property.
     * 
     */
    public void setVrsta(boolean value) {
        this.vrsta = value;
    }

}
