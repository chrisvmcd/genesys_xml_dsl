import spock.lang.Specification

@Mixin(XmlTestHelper)
class ApplicationDelegateSpec extends Specification {
    def 'should add create node to root'() {

        setup:
        def root = new XmlParser().parseText("<xml></xml>")
        def subject = new ApplicationDelegate(root)
        def expected = getExpected()

        when:
        subject.options(["clws.url":"Dave","logging.level":"DEBUG"])

        then:
        compareXml(expected, root)
    }

    private Node getExpected() {
        new XmlParser().parseText("<xml><options>\n" +
                "        <list_pair key=\"properties\">\n" +
                "            <str_pair key=\"clws.url\" value=\"Dave\"/>\n" +
                "            <str_pair key=\"logging.level\" value=\"DEBUG\"/>\n" +
                "        </list_pair>\n" +
                "    </options></xml>")
    }
}
