@Mixin(XmlMixin)
class DynamicDelegate {
    private Node reference;

    DynamicDelegate(parent, nodeName) {
        reference = readNode(nodeName)
        parent.append(reference)
    }

    DynamicDelegate(node) {
        this.reference = node
    }

    def methodMissing(String name, args) {

        def attributeMap = getAttributeMap(args)
        def closure = getClosure(args)
        def node = createNode(name)

        mapAttributesToNode(attributeMap, node)

        if (closure != null) {

            closure.delegate = new DynamicDelegate(node)
            closure.resolveStrategy = Closure.DELEGATE_FIRST

            closure()
        }
        reference
    }

    private Object getClosure(args) {

        if (args[0] instanceof Closure) {
            return args[0]
        } else if(args.length == 2){
            return args[1]
        }
    }

    private getAttributeMap(args) {
        if (args[0] instanceof Map) {
            return args[0]
        }
    }

    def propertyMissing(String name) {
        println("propertyMissing - " + name)
        createNode(name)
    }

    private createNode(String name) {
        def node = readNode(name)
        reference.append(node)
        node
    }
}
