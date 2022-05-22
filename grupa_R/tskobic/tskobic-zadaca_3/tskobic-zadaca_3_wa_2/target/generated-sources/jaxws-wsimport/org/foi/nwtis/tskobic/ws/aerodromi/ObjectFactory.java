
package org.foi.nwtis.tskobic.ws.aerodromi;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.foi.nwtis.tskobic.ws.aerodromi package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DajSveAerodrome_QNAME = new QName("http://ws.zadaca_3.tskobic.nwtis.foi.org/", "dajSveAerodrome");
    private final static QName _DajSveAerodromeResponse_QNAME = new QName("http://ws.zadaca_3.tskobic.nwtis.foi.org/", "dajSveAerodromeResponse");
    private final static QName _DodajAerodromPreuzimanje_QNAME = new QName("http://ws.zadaca_3.tskobic.nwtis.foi.org/", "dodajAerodromPreuzimanje");
    private final static QName _DodajAerodromPreuzimanjeResponse_QNAME = new QName("http://ws.zadaca_3.tskobic.nwtis.foi.org/", "dodajAerodromPreuzimanjeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.foi.nwtis.tskobic.ws.aerodromi
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DajSveAerodrome }
     * 
     */
    public DajSveAerodrome createDajSveAerodrome() {
        return new DajSveAerodrome();
    }

    /**
     * Create an instance of {@link DajSveAerodromeResponse }
     * 
     */
    public DajSveAerodromeResponse createDajSveAerodromeResponse() {
        return new DajSveAerodromeResponse();
    }

    /**
     * Create an instance of {@link DodajAerodromPreuzimanje }
     * 
     */
    public DodajAerodromPreuzimanje createDodajAerodromPreuzimanje() {
        return new DodajAerodromPreuzimanje();
    }

    /**
     * Create an instance of {@link DodajAerodromPreuzimanjeResponse }
     * 
     */
    public DodajAerodromPreuzimanjeResponse createDodajAerodromPreuzimanjeResponse() {
        return new DodajAerodromPreuzimanjeResponse();
    }

    /**
     * Create an instance of {@link Aerodrom }
     * 
     */
    public Aerodrom createAerodrom() {
        return new Aerodrom();
    }

    /**
     * Create an instance of {@link Lokacija }
     * 
     */
    public Lokacija createLokacija() {
        return new Lokacija();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DajSveAerodrome }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DajSveAerodrome }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.zadaca_3.tskobic.nwtis.foi.org/", name = "dajSveAerodrome")
    public JAXBElement<DajSveAerodrome> createDajSveAerodrome(DajSveAerodrome value) {
        return new JAXBElement<DajSveAerodrome>(_DajSveAerodrome_QNAME, DajSveAerodrome.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DajSveAerodromeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DajSveAerodromeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.zadaca_3.tskobic.nwtis.foi.org/", name = "dajSveAerodromeResponse")
    public JAXBElement<DajSveAerodromeResponse> createDajSveAerodromeResponse(DajSveAerodromeResponse value) {
        return new JAXBElement<DajSveAerodromeResponse>(_DajSveAerodromeResponse_QNAME, DajSveAerodromeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DodajAerodromPreuzimanje }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DodajAerodromPreuzimanje }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.zadaca_3.tskobic.nwtis.foi.org/", name = "dodajAerodromPreuzimanje")
    public JAXBElement<DodajAerodromPreuzimanje> createDodajAerodromPreuzimanje(DodajAerodromPreuzimanje value) {
        return new JAXBElement<DodajAerodromPreuzimanje>(_DodajAerodromPreuzimanje_QNAME, DodajAerodromPreuzimanje.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DodajAerodromPreuzimanjeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DodajAerodromPreuzimanjeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.zadaca_3.tskobic.nwtis.foi.org/", name = "dodajAerodromPreuzimanjeResponse")
    public JAXBElement<DodajAerodromPreuzimanjeResponse> createDodajAerodromPreuzimanjeResponse(DodajAerodromPreuzimanjeResponse value) {
        return new JAXBElement<DodajAerodromPreuzimanjeResponse>(_DodajAerodromPreuzimanjeResponse_QNAME, DodajAerodromPreuzimanjeResponse.class, null, value);
    }

}
