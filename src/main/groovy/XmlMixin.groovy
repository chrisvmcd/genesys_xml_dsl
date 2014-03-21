class XmlMixin {

    Node readNode(String nodeName) {
        new XmlParser().parseText(this.getClass().getResource('/' + nodeName + '.xml').text)
    }
}
