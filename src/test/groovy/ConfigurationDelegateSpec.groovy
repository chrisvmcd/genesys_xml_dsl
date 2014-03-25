import spock.lang.Specification

@Mixin(XmlTestHelper)
class ConfigurationDelegateSpec extends Specification {
    def 'should construct root node with create entry'() {
        setup:
        def subject = new ConfigurationDelegate()

        when:
        def actual = subject.create {}
        def expected = getExpected()

        then:
        compareXml(expected, actual)
    }

    def 'should construct root node with references entry'() {
        setup:
        def subject = new ConfigurationDelegate()

        when:
        def actual = subject.reference {}
        def expected = load("data_reference_example")

        then:
        compareXml(expected, actual)
    }

    private Node getExpected() {
        new XmlParser().parseText("<CfgData xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://www.genesyslab.com/cs hta/cfg_update.xsd\">\n" +
                "<CfgCreate></CfgCreate></CfgData>")
    }

    private Node getReferenceExpected() {
        new XmlParser().parseText("<CfgData xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://www.genesyslab.com/cs hta/cfg_update.xsd\">\n" +
                "<CfgReference></CfgReference></CfgData>")
    }
}
