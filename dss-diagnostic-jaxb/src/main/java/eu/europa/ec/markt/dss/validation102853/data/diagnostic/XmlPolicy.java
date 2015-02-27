//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.17 at 03:26:09 PM CET 
//


package eu.europa.ec.markt.dss.validation102853.data.diagnostic;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Notice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Identified" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ProcessingError" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DigestAlgorithmsEqual" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "url",
    "notice",
    "identified",
    "status",
    "processingError",
    "digestAlgorithmsEqual"
})
public class XmlPolicy {

    @XmlElement(name = "Id", required = true)
    protected String id;
    @XmlElement(name = "Url", required = true)
    protected String url;
    @XmlElement(name = "Notice", required = true)
    protected String notice;
    @XmlElement(name = "Identified")
    protected boolean identified;
    @XmlElement(name = "Status")
    protected boolean status;
    @XmlElement(name = "ProcessingError", required = true)
    protected String processingError;
    @XmlElement(name = "DigestAlgorithmsEqual")
    protected boolean digestAlgorithmsEqual;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the notice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotice() {
        return notice;
    }

    /**
     * Sets the value of the notice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotice(String value) {
        this.notice = value;
    }

    /**
     * Gets the value of the identified property.
     * 
     */
    public boolean isIdentified() {
        return identified;
    }

    /**
     * Sets the value of the identified property.
     * 
     */
    public void setIdentified(boolean value) {
        this.identified = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(boolean value) {
        this.status = value;
    }

    /**
     * Gets the value of the processingError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingError() {
        return processingError;
    }

    /**
     * Sets the value of the processingError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingError(String value) {
        this.processingError = value;
    }

    /**
     * Gets the value of the digestAlgorithmsEqual property.
     * 
     */
    public boolean isDigestAlgorithmsEqual() {
        return digestAlgorithmsEqual;
    }

    /**
     * Sets the value of the digestAlgorithmsEqual property.
     * 
     */
    public void setDigestAlgorithmsEqual(boolean value) {
        this.digestAlgorithmsEqual = value;
    }

}
