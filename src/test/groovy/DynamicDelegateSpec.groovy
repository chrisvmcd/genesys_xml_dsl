import spock.lang.Specification

@Mixin(XmlTestHelper)
class DynamicDelegateSpec extends Specification {
    def 'should add create node to root with application section'() {

        setup:
        def root = new XmlParser().parseText("<xml></xml>")
        def subject = new DynamicDelegate(root, "reference")
        def expected = load("reference_example")

        when:
        subject.folder([id: "AVSFolder", DBID:2707])

        then:
        compareXml(expected, root)
    }

    def 'should throw an exception when xml fragment not found'() {

        def subject = new DynamicDelegate(new XmlParser().parseText("<xml></xml>"), "reference")

        when:
        subject.blah([id: "AVSFolder", DBID:2707])

        then:
        thrown(IllegalStateException)
    }
}