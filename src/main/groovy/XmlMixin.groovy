class XmlMixin {

    Node readNode(nodeName) {
        def resource = this.getClass().getResource('/fragments/' + nodeName + '.xml')
        if(resource == null)
            throw new IllegalStateException(String.format("Xml snippet %s.xml not found", nodeName));
        new XmlParser().parseText(resource.text)
    }

    void mapAttributesToNode(map, node) {
       println String.format("mapping %s to %s", map.toString(), node)
        for (entry in map) {
            node.attributes().putAt(entry.key, map[entry.key])
        }
    }
}
