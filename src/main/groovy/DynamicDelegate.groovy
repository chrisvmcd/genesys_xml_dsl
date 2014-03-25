@Mixin(XmlMixin)
class DynamicDelegate {
    private Node reference;

    DynamicDelegate(parent, nodeName) {
        reference = readNode(nodeName)
        parent.append(reference)
    }

    def methodMissing(String name, args) {
        def folder = readNode(name)
        mapAttributesToNode(args[0], folder)
        reference.append(folder)
    }
}
