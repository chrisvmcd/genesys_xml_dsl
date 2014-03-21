@Mixin(XmlMixin)
class ConfigurationDelegate {
    private root
    ConfigurationDelegate(){
        this.root = readNode("data")
    }

    Node create(Closure cl) {
        cl.delegate = new CreateDelegate(this.root)
        cl.resolveStrategy = Closure.DELEGATE_FIRST

        cl()
        return this.root
    }
}