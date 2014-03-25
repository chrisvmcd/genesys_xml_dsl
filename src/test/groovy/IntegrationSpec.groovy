import spock.lang.Specification

@Mixin(XmlTestHelper)
class IntegrationSpec extends Specification {

    def 'should construct basic xml'() {
        setup:
        def dsl = new GroovyShell().parse(this.getClass().
                        getResource('integration_example.groovy').text)

        def expected = load("full_create_application_integration")

        def subject = new ConfigGenerator()

        when:
        def actual = subject.generate(dsl)

        then:
        compareXml(expected, actual)
    }
}