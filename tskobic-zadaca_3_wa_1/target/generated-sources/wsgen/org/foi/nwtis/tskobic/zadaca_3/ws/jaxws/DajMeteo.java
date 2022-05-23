
package org.foi.nwtis.tskobic.zadaca_3.ws.jaxws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "dajMeteo", namespace = "http://ws.zadaca_3.tskobic.nwtis.foi.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dajMeteo", namespace = "http://ws.zadaca_3.tskobic.nwtis.foi.org/")
public class DajMeteo {

    @XmlElement(name = "icao", namespace = "")
    private String icao;

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

}
