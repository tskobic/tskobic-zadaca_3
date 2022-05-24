
package org.foi.nwtis.tskobic.zadaca_3.ws.jaxws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.foi.nwtis.rest.podaci.Lokacija;

@XmlRootElement(name = "dajNajbliziAerodrom", namespace = "http://ws.zadaca_3.tskobic.nwtis.foi.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dajNajbliziAerodrom", namespace = "http://ws.zadaca_3.tskobic.nwtis.foi.org/", propOrder = {
    "lokacija",
    "vrsta"
})
public class DajNajbliziAerodrom {

    @XmlElement(name = "lokacija", namespace = "")
    private Lokacija lokacija;
    @XmlElement(name = "vrsta", namespace = "")
    private boolean vrsta;

    /**
     * 
     * @return
     *     returns Lokacija
     */
    public Lokacija getLokacija() {
        return this.lokacija;
    }

    /**
     * 
     * @param lokacija
     *     the value for the lokacija property
     */
    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    /**
     * 
     * @return
     *     returns boolean
     */
    public boolean isVrsta() {
        return this.vrsta;
    }

    /**
     * 
     * @param vrsta
     *     the value for the vrsta property
     */
    public void setVrsta(boolean vrsta) {
        this.vrsta = vrsta;
    }

}
