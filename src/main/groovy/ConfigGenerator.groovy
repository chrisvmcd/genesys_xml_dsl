import groovy.xml.XmlUtil

class ConfigGenerator {
    static void main(String[] args) {
        def xml = generate(new GroovyShell().parse(new File(args[0])))
        new File(args[1]).write(XmlUtil.serialize(xml))
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