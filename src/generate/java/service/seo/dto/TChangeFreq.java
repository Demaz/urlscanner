//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2014.05.14 à 10:32:20 PM CEST 
//


package service.seo.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour tChangeFreq.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="tChangeFreq">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="always"/>
 *     &lt;enumeration value="hourly"/>
 *     &lt;enumeration value="daily"/>
 *     &lt;enumeration value="weekly"/>
 *     &lt;enumeration value="monthly"/>
 *     &lt;enumeration value="yearly"/>
 *     &lt;enumeration value="never"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tChangeFreq")
@XmlEnum
public enum TChangeFreq {

    @XmlEnumValue("always")
    ALWAYS("always"),
    @XmlEnumValue("hourly")
    HOURLY("hourly"),
    @XmlEnumValue("daily")
    DAILY("daily"),
    @XmlEnumValue("weekly")
    WEEKLY("weekly"),
    @XmlEnumValue("monthly")
    MONTHLY("monthly"),
    @XmlEnumValue("yearly")
    YEARLY("yearly"),
    @XmlEnumValue("never")
    NEVER("never");
    private final String value;

    TChangeFreq(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TChangeFreq fromValue(String v) {
        for (TChangeFreq c: TChangeFreq.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
