class ApplicationDelegate {

    private Node parent

    ApplicationDelegate(parent) {
        this.parent = parent
    }

    void options(map) {

        println "made it"
        def options = parent.appendNode("options")
        def list = options.appendNode("list_pair", [key: "properties"])

        for (entry in map) {
            list.appendNode("str_pair", [key: entry.key, value: entry.value], "")
        }
    }
}
