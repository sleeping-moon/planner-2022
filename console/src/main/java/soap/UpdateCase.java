
package soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateCase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateCase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mycase" type="{http://soap.webservice/}case" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateCase", propOrder = {
    "mycase"
})
public class UpdateCase {

    protected Case mycase;

    /**
     * Gets the value of the mycase property.
     * 
     * @return
     *     possible object is
     *     {@link Case }
     *     
     */
    public Case getMycase() {
        return mycase;
    }

    /**
     * Sets the value of the mycase property.
     * 
     * @param value
     *     allowed object is
     *     {@link Case }
     *     
     */
    public void setMycase(Case value) {
        this.mycase = value;
    }

}
