
package br.com.fiap.ws.financeiro.cobrar.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.fiap.ws.financeiro.cobrar.client package. 
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

    private final static QName _Cobrar_QNAME = new QName("http://finCliente.com.br/", "cobrar");
    private final static QName _CobrarResponse_QNAME = new QName("http://finCliente.com.br/", "cobrarResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.fiap.ws.financeiro.cobrar.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Cobrar }
     * 
     */
    public Cobrar createCobrar() {
        return new Cobrar();
    }

    /**
     * Create an instance of {@link CobrarResponse }
     * 
     */
    public CobrarResponse createCobrarResponse() {
        return new CobrarResponse();
    }

    /**
     * Create an instance of {@link Cobranca }
     * 
     */
    public Cobranca createCobranca() {
        return new Cobranca();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cobrar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://finCliente.com.br/", name = "cobrar")
    public JAXBElement<Cobrar> createCobrar(Cobrar value) {
        return new JAXBElement<Cobrar>(_Cobrar_QNAME, Cobrar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CobrarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://finCliente.com.br/", name = "cobrarResponse")
    public JAXBElement<CobrarResponse> createCobrarResponse(CobrarResponse value) {
        return new JAXBElement<CobrarResponse>(_CobrarResponse_QNAME, CobrarResponse.class, null, value);
    }

}
