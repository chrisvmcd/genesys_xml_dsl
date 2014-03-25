@Mixin(XmlMixin)
class ConfigurationDelegate {
    private root

    ConfigurationDelegate() {
        this.root = readNode("data")
    }

    Node create(Closure cl) {
        cl.delegate = new CreateDelegate(this.root)
        cl.resolveStrategy = Closure.DELEGATE_FIRST

        cl()
        return this.root
    }

    Node methodMissing(String name, args) {
        def closure = args[0]

        closure.delegate = new DynamicDelegate(this.root, name)
        closure.resolveStrategy = Closure.DELEGATE_FIRST

        closure()
        return this.root
    }
}