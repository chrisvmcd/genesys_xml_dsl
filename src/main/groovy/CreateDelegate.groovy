@Mixin(XmlMixin)
class CreateDelegate {

    private Node create;

    CreateDelegate(parent) {
        this.create = readNode("create")
        parent.append(create)
    }

    void application(Map map) {
        create.append(createApplicationNode(map))
    }

    void application(Map map, Closure cl) {
        def application = createApplicationNode(map)

        cl.delegate = new ApplicationDelegate(application)
        cl.resolveStrategy = Closure.DELEGATE_FIRST

        cl()

        create.append(application)
    }

    private Node createApplicationNode(Map map) {
        def application = readNode('application')
        mapAttributesToNode(map, application)
        application
    }
}
