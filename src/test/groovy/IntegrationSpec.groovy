import spock.lang.Specification

@Mixin(XmlAssertions)
class IntegrationSpec extends Specification {

    def 'should construct basic xml'() {
        setup:
        def dsl = new GroovyShell().parse(
                'configuration {\n' +
                        '    create{\n' +
                        '        application name:"Gromit"\n' +
                        '    }\n' +
                        '\n' +
                        '}'
        )

        def expected = getExpected()

        def subject = new ConfigGenerator()

        when:
        def actual = subject.generate(dsl)

        then:
        compareXml(expected, actual)
    }

    private Node getExpected() {
        new XmlParser().parseText('<?xml version="1.0" encoding="utf-8"?>\n' +
                '<CfgData xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"\n' +
                '         xsi:schemaLocation="http://www.genesyslab.com/cs hta/cfg_update.xsd">\n' +
                '    <CfgCreate>\n' +
                '        <CfgApplication id="CfgApplication1174" name="Gromit"\n' +
                '                        type="22" version="8.1"\n' +
                '                        state="1" appPrototypeDBID="CfgAppPrototype202"\n' +
                '                        ownerDBID="CfgProviderTenant1" folderDBID="CfgFolder2787">\n' +
                '            <tenantDBIDs>\n' +
                '                <CfgAppTenant linkDBID="CfgTenant101"/>\n' +
                '            </tenantDBIDs>\n' +
                '            <options>\n' +
                '                <list_pair key="properties">\n' +
                '                    <str_pair key="clws.url" value="https://test.com"/>\n' +
                '                    <str_pair key="logging.level" value="DEBUG"/>\n' +
                '                </list_pair>\n' +
                '            </options>\n' +
                '        </CfgApplication>\n' +
                '    </CfgCreate>\n' +
                '</CfgData>')
    }
}