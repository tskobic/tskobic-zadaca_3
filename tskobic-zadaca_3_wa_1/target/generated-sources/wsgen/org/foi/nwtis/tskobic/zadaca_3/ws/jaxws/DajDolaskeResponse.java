
package org.foi.nwtis.tskobic.zadaca_3.ws.jaxws;

import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.foi.nwtis.rest.podaci.AvionLeti;

@XmlRootElement(name = "dajDolaskeResponse", namespace = "http://ws.zadaca_3.tskobic.nwtis.foi.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dajDolaskeResponse", namespace = "http://ws.zadaca_3.tskobic.nwtis.foi.org/")
public class DajDolaskeResponse {

    @XmlElement(name = "return", namespace = "")
    private List<AvionLeti> _return;

    /**
     * 
     * @return
     *     returns List<AvionLeti>
     */
    public List<AvionLeti> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<AvionLeti> _return) {
        this._return = _return;
    }

}
