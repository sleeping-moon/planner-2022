
package soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for case complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="case">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="preparationTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="caseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="caseDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="caseDueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="caseNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="caseTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "case", propOrder = {
    "preparationTime",
    "userId",
    "caseDate",
    "caseDescription",
    "caseDueDate",
    "caseNumber",
    "caseTitle"
})
public class Case {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar preparationTime;
    protected int userId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar caseDate;
    protected String caseDescription;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar caseDueDate;
    protected int caseNumber;
    protected String caseTitle;

    /**
     * Gets the value of the preparationTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPreparationTime() {
        return preparationTime;
    }

    /**
     * Sets the value of the preparationTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPreparationTime(XMLGregorianCalendar value) {
        this.preparationTime = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(int value) {
        this.userId = value;
    }

    /**
     * Gets the value of the caseDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCaseDate() {
        return caseDate;
    }

    /**
     * Sets the value of the caseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCaseDate(XMLGregorianCalendar value) {
        this.caseDate = value;
    }

    /**
     * Gets the value of the caseDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaseDescription() {
        return caseDescription;
    }

    /**
     * Sets the value of the caseDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaseDescription(String value) {
        this.caseDescription = value;
    }

    /**
     * Gets the value of the caseDueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCaseDueDate() {
        return caseDueDate;
    }

    /**
     * Sets the value of the caseDueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCaseDueDate(XMLGregorianCalendar value) {
        this.caseDueDate = value;
    }

    /**
     * Gets the value of the caseNumber property.
     * 
     */
    public int getCaseNumber() {
        return caseNumber;
    }

    /**
     * Sets the value of the caseNumber property.
     * 
     */
    public void setCaseNumber(int value) {
        this.caseNumber = value;
    }

    /**
     * Gets the value of the caseTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaseTitle() {
        return caseTitle;
    }

    /**
     * Sets the value of the caseTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaseTitle(String value) {
        this.caseTitle = value;
    }

}
