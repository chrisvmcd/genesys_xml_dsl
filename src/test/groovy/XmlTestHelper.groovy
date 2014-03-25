import groovy.xml.XmlUtil
import org.custommonkey.xmlunit.*

class XmlTestHelper {

    boolean compareXml(expected, actual) {

        setUpXmlUnitTolerances()

        def expectedXml = XmlUtil.serialize(expected)
        def actualXml = XmlUtil.serialize(actual)

        debug(expectedXml, actualXml)

        def difference = XMLUnit.compareXML(expectedXml, actualXml)
        if (difference.identical()) {
            return true
        } else {
            println difference.toString()
            return false
        }
    }

    Node load(document) {
        new XmlParser().parseText(this.getClass().
                getResource(document + '.xml').text)
    }

    private void debug(expected, actual) {
        println "EXPECTED:"
        println expected
        println "ACTUAL:"
        println actual
    }

    private void setUpXmlUnitTolerances() {
        XMLUnit.setIgnoreWhitespace(true)
        XMLUnit.setIgnoreComments(true)
        XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true)
        XMLUnit.setNormalizeWhitespace(true)
    }
}
