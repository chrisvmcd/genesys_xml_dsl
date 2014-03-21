import groovy.xml.XmlUtil

class XmlAssertions {

    boolean compareXml(expected, actual) {
        if (XmlUtil.serialize(expected) == XmlUtil.serialize(actual)) {
            return true
        } else {
            println "EXPECTED:"
            println XmlUtil.serialize(expected)
            println "ACTUAL:"
            println XmlUtil.serialize(actual)
            return false
        }
    }
}
