
package soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap package. 
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

    private final static QName _GetAllCase_QNAME = new QName("http://soap.webservice/", "getAllCase");
    private final static QName _GetAllCaseResponse_QNAME = new QName("http://soap.webservice/", "getAllCaseResponse");
    private final static QName _UpdateCase_QNAME = new QName("http://soap.webservice/", "updateCase");
    private final static QName _GetCaseById_QNAME = new QName("http://soap.webservice/", "getCaseById");
    private final static QName _UpdateCaseResponse_QNAME = new QName("http://soap.webservice/", "updateCaseResponse");
    private final static QName _ParseException_QNAME = new QName("http://soap.webservice/", "ParseException");
    private final static QName _GetCaseByIdResponse_QNAME = new QName("http://soap.webservice/", "getCaseByIdResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCaseByIdResponse }
     * 
     */
    public GetCaseByIdResponse createGetCaseByIdResponse() {
        return new GetCaseByIdResponse();
    }

    /**
     * Create an instance of {@link UpdateCaseResponse }
     * 
     */
    public UpdateCaseResponse createUpdateCaseResponse() {
        return new UpdateCaseResponse();
    }

    /**
     * Create an instance of {@link ParseException }
     * 
     */
    public ParseException createParseException() {
        return new ParseException();
    }

    /**
     * Create an instance of {@link GetAllCase }
     * 
     */
    public GetAllCase createGetAllCase() {
        return new GetAllCase();
    }

    /**
     * Create an instance of {@link GetAllCaseResponse }
     * 
     */
    public GetAllCaseResponse createGetAllCaseResponse() {
        return new GetAllCaseResponse();
    }

    /**
     * Create an instance of {@link UpdateCase }
     * 
     */
    public UpdateCase createUpdateCase() {
        return new UpdateCase();
    }

    /**
     * Create an instance of {@link GetCaseById }
     * 
     */
    public GetCaseById createGetCaseById() {
        return new GetCaseById();
    }

    /**
     * Create an instance of {@link Case }
     * 
     */
    public Case createCase() {
        return new Case();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.webservice/", name = "getAllCase")
    public JAXBElement<GetAllCase> createGetAllCase(GetAllCase value) {
        return new JAXBElement<GetAllCase>(_GetAllCase_QNAME, GetAllCase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCaseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.webservice/", name = "getAllCaseResponse")
    public JAXBElement<GetAllCaseResponse> createGetAllCaseResponse(GetAllCaseResponse value) {
        return new JAXBElement<GetAllCaseResponse>(_GetAllCaseResponse_QNAME, GetAllCaseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateCase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.webservice/", name = "updateCase")
    public JAXBElement<UpdateCase> createUpdateCase(UpdateCase value) {
        return new JAXBElement<UpdateCase>(_UpdateCase_QNAME, UpdateCase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCaseById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.webservice/", name = "getCaseById")
    public JAXBElement<GetCaseById> createGetCaseById(GetCaseById value) {
        return new JAXBElement<GetCaseById>(_GetCaseById_QNAME, GetCaseById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateCaseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.webservice/", name = "updateCaseResponse")
    public JAXBElement<UpdateCaseResponse> createUpdateCaseResponse(UpdateCaseResponse value) {
        return new JAXBElement<UpdateCaseResponse>(_UpdateCaseResponse_QNAME, UpdateCaseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.webservice/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCaseByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.webservice/", name = "getCaseByIdResponse")
    public JAXBElement<GetCaseByIdResponse> createGetCaseByIdResponse(GetCaseByIdResponse value) {
        return new JAXBElement<GetCaseByIdResponse>(_GetCaseByIdResponse_QNAME, GetCaseByIdResponse.class, null, value);
    }

}
