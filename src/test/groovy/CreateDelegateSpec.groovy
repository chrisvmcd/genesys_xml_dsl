import spock.lang.Specification

@Mixin(XmlAssertions)
class CreateDelegateSpec extends Specification {
    def 'should add create node to root with application section'() {

        setup:
        def root = new XmlParser().parseText("<xml></xml>")
        def subject = new CreateDelegate(root)
        def expected = getExpected()

        when:
        subject.application([name: "Dave"])

        then:
        compareXml(expected, root)
    }


    def 'should add create node with application options'() {

        setup:
        def root = new XmlParser().parseText("<xml></xml>")
        def subject = new CreateDelegate(root)
        def expected = getExpectedWithOptions()

        when:
        subject.application([name: "Dave"], {options "test.mode": "false"})

        then:
        compareXml(expected, root)
    }

    private Node getExpected() {
        new XmlParser().parseText("<xml><CfgCreate><CfgApplication id=\"CfgApplication1174\" name=\"Dave\" type=\"22\"\n" +
                "                version=\"8.1\" state=\"1\"\n" +
                "                appPrototypeDBID=\"CfgAppPrototype202\"\n" +
                "                ownerDBID=\"CfgProviderTenant1\" folderDBID=\"CfgFolder2787\">\n" +
                "    <tenantDBIDs>\n" +
                "        <CfgAppTenant linkDBID=\"CfgTenant101\"/>\n" +
                "    </tenantDBIDs>\n" +
                "</CfgApplication></CfgCreate></xml>")
    }

    private Node getExpectedWithOptions() {
        new XmlParser().parseText("<xml><CfgCreate><CfgApplication id=\"CfgApplication1174\" name=\"Dave\" type=\"22\"\n" +
                "                version=\"8.1\" state=\"1\"\n" +
                "                appPrototypeDBID=\"CfgAppPrototype202\"\n" +
                "                ownerDBID=\"CfgProviderTenant1\" folderDBID=\"CfgFolder2787\">\n" +
                "    <tenantDBIDs>\n" +
                "        <CfgAppTenant linkDBID=\"CfgTenant101\"/>\n" +
                "    </tenantDBIDs>\n" +
                "    <options>\n" +
                "       <list_pair key=\"properties\">\n" +
                "            <str_pair key=\"test.mode\" value=\"false\"/>\n" +
                "        </list_pair>\n" +
                "    </options>"+
                "</CfgApplication></CfgCreate></xml>")
    }
}
