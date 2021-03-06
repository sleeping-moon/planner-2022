
package soap;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ws/soap/case", targetNamespace = "http://soap.webservice/", wsdlLocation = "http://localhost:8080/ws/soap/case?wsdl")
public class Ws_002fSoap_002fCase
    extends Service
{

    private final static URL WS_002FSOAP_002FCASE_WSDL_LOCATION;
    private final static WebServiceException WS_002FSOAP_002FCASE_EXCEPTION;
    private final static QName WS_002FSOAP_002FCASE_QNAME = new QName("http://soap.webservice/", "ws/soap/case");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/ws/soap/case?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WS_002FSOAP_002FCASE_WSDL_LOCATION = url;
        WS_002FSOAP_002FCASE_EXCEPTION = e;
    }

    public Ws_002fSoap_002fCase() {
        super(__getWsdlLocation(), WS_002FSOAP_002FCASE_QNAME);
    }

    public Ws_002fSoap_002fCase(WebServiceFeature... features) {
        super(__getWsdlLocation(), WS_002FSOAP_002FCASE_QNAME, features);
    }

    public Ws_002fSoap_002fCase(URL wsdlLocation) {
        super(wsdlLocation, WS_002FSOAP_002FCASE_QNAME);
    }

    public Ws_002fSoap_002fCase(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WS_002FSOAP_002FCASE_QNAME, features);
    }

    public Ws_002fSoap_002fCase(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Ws_002fSoap_002fCase(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CaseSoap
     */
    @WebEndpoint(name = "CaseSoapPort")
    public CaseSoap getCaseSoapPort() {
        return super.getPort(new QName("http://soap.webservice/", "CaseSoapPort"), CaseSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CaseSoap
     */
    @WebEndpoint(name = "CaseSoapPort")
    public CaseSoap getCaseSoapPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://soap.webservice/", "CaseSoapPort"), CaseSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WS_002FSOAP_002FCASE_EXCEPTION!= null) {
            throw WS_002FSOAP_002FCASE_EXCEPTION;
        }
        return WS_002FSOAP_002FCASE_WSDL_LOCATION;
    }

}
