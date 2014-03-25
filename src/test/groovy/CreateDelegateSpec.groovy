import spock.lang.Specification

@Mixin(XmlTestHelper)
class CreateDelegateSpec extends Specification {
    def 'should add create node to root with application section'() {

        setup:
        def root = new XmlParser().parseText("<xml></xml>")
        def subject = new CreateDelegate(root)
        def expected = load("application_example")

        when:
        subject.application([name: "Dave"])

        then:
        compareXml(expected, root)
    }


    def 'should add create node with application options'() {

        setup:
        def root = new XmlParser().parseText("<xml></xml>")
        def subject = new CreateDelegate(root)
        def expected = load("application_example_with_options")

        when:
        subject.application([name: "Dave"], {options "test.mode": "false"})

        then:
        compareXml(expected, root)
    }
}
