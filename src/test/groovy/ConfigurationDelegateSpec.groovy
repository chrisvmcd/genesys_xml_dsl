import spock.lang.Specification

class ConfigurationDelegateSpec extends Specification {
    def 'should constuct root node'() {
        setup:
        def subject = new ConfigurationDelegate()

        when:
        def actual = subject.create {}
        def expected = getExpected()

        then:
        println expected.toString()
        println actual.toString()
        expected.toString() == actual.toString()
    }

    private Node getExpected() {
        new XmlParser().parseText("<CfgData xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://www.genesyslab.com/cs hta/cfg_update.xsd\">\n" +
                "<CfgCreate></CfgCreate></CfgData>")
    }
}
