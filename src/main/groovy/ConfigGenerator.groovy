import groovy.xml.XmlUtil
import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

class ConfigGenerator {
    static void main(String[] args) {
        def xml = generate(new GroovyShell().parse(new File(dslFileName(args))))
        def xmlString = XmlUtil.serialize(xml)

        if(requiresValidation(args)){
            validate(xmlString)
        }

        new File(xmlOutputFileName(args)).write(xmlString)
    }

    def static dslFileName(args) {
        args[0]
    }

    def static xmlOutputFileName(args) {
        args[1]
    }

    def static boolean requiresValidation(args) {
        Boolean.parseBoolean(args[2])
    }

    private static void validate(String xmlString) {
        def factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        def schema = factory.newSchema(new StreamSource(
                new StringReader(this.getClass().getResource('/genesys8.0.xsd').text)))
        def validator = schema.newValidator()
        validator.validate(new StreamSource(
                new StringReader(xmlString)))
    }

    static Node generate(Script dslScript) {

        def node = new XmlMixin().readNode("data")

        dslScript.metaClass = createEMC(dslScript.class, {
            ExpandoMetaClass emc ->

                emc.configuration = {
                    Closure cl ->

                        cl.delegate = new DynamicDelegate(node)
                        cl.resolveStrategy = Closure.DELEGATE_FIRST

                        cl()
                }
        })
        return dslScript.run()
    }

    static ExpandoMetaClass createEMC(Class scriptClass, Closure cl) {
        ExpandoMetaClass emc = new ExpandoMetaClass(scriptClass, false)

        cl(emc)

        emc.initialize()
        return emc
    }
}