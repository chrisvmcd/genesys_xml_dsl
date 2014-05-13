
class FileMixin {

    def readXmlFile(fileName) {
        this.getClass().getResource('/fragments/' + fileName + '.xml')
    }

    def readPropertiesFile(fileName) {
        this.getClass().getResource('/properties/' + fileName + '.properties')
    }
}
