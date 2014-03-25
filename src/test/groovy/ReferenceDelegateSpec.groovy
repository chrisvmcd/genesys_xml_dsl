import spock.lang.Specification

@Mixin(XmlTestHelper)
class ReferenceDelegateSpec extends Specification {
    def 'should add create node to root with application section'() {

        setup:
        def root = new XmlParser().parseText("<xml></xml>")
        def subject = new ReferenceDelegate(root)
        def expected = load("reference_example")

        when:
        subject.folder([id: "AVSFolder", DBID:2707])

        then:
        compareXml(expected, root)
    }
}
