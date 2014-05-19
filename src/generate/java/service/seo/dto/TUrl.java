//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2014.05.14 à 10:32:20 PM CEST 
//


package service.seo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Container for the data needed to describe a document to crawl.
 *       
 * 
 * <p>Classe Java pour tUrl complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="tUrl">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="loc" type="{http://www.sitemaps.org/schemas/sitemap/0.9}tLoc"/>
 *         &lt;element name="lastmod" type="{http://www.sitemaps.org/schemas/sitemap/0.9}tLastmod" minOccurs="0"/>
 *         &lt;element name="changefreq" type="{http://www.sitemaps.org/schemas/sitemap/0.9}tChangeFreq" minOccurs="0"/>
 *         &lt;element name="priority" type="{http://www.sitemaps.org/schemas/sitemap/0.9}tPriority" minOccurs="0"/>
 *         &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tUrl", propOrder = {
    "loc",
    "lastmod",
    "changefreq",
    "priority",
    "any"
})
public class TUrl {

    @XmlElement(required = true)
    protected String loc;
    protected String lastmod;
    protected TChangeFreq changefreq;
    protected BigDecimal priority;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Obtient la valeur de la propriété loc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoc() {
        return loc;
    }

    /**
     * Définit la valeur de la propriété loc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoc(String value) {
        this.loc = value;
    }

    /**
     * Obtient la valeur de la propriété lastmod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastmod() {
        return lastmod;
    }

    /**
     * Définit la valeur de la propriété lastmod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastmod(String value) {
        this.lastmod = value;
    }

    /**
     * Obtient la valeur de la propriété changefreq.
     * 
     * @return
     *     possible object is
     *     {@link TChangeFreq }
     *     
     */
    public TChangeFreq getChangefreq() {
        return changefreq;
    }

    /**
     * Définit la valeur de la propriété changefreq.
     * 
     * @param value
     *     allowed object is
     *     {@link TChangeFreq }
     *     
     */
    public void setChangefreq(TChangeFreq value) {
        this.changefreq = value;
    }

    /**
     * Obtient la valeur de la propriété priority.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPriority() {
        return priority;
    }

    /**
     * Définit la valeur de la propriété priority.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPriority(BigDecimal value) {
        this.priority = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

}
