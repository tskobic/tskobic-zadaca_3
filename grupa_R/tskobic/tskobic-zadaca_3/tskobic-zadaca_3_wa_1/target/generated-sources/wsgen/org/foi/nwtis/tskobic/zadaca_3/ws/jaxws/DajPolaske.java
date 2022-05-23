
package org.foi.nwtis.tskobic.zadaca_3.ws.jaxws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "dajPolaske", namespace = "http://ws.zadaca_3.tskobic.nwtis.foi.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dajPolaske", namespace = "http://ws.zadaca_3.tskobic.nwtis.foi.org/", propOrder = {
    "icao",
    "dan"
})
public class DajPolaske {

    @XmlElement(name = "icao", namespace = "")
    private String icao;
    @XmlElement(name = "dan", namespace = "")
    private String dan;

    /**
     * 
     * @return
     *     returns String
     */
    public String getIcao() {
        return this.icao;
    }

    /**
     * 
     * @param icao
     *     the value for the icao property
     */
    public void setIcao(String icao) {
        this.icao = icao;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getDan() {
        return this.dan;
    }

    /**
     * 
     * @param dan
     *     the value for the dan property
     */
    public void setDan(String dan) {
        this.dan = dan;
    }

}
