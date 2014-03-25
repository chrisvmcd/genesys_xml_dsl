class XmlMixin {

    Node readNode(nodeName) {
        new XmlParser().parseText(this.getClass().getResource('/' + nodeName + '.xml').text)
    }

    void mapAttributesToNode(map, node) {
        for (entry in map) {
            node.attributes().putAt(entry.key, map[entry.key])
        }
    }
}
