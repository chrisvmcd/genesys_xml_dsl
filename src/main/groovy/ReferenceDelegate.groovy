@Mixin(XmlMixin)
class ReferenceDelegate {
    private Node reference;

    ReferenceDelegate(parent) {
        reference = readNode("reference")
        parent.append(reference)
    }

    void folder(attributes) {
        def folder = readNode("folder")
        mapAttributesToNode(attributes, folder)
        reference.append(folder)
    }
}
