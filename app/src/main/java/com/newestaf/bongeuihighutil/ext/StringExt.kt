fun String.lineBreak(splitWord: String): String? {
    var result: String? = null
    val strArr = this.split(splitWord)
    for (str in strArr) {
        if (result == null) {
            result = str
        }
        else {
            result = result + "\n" + str
        }
    }
    return result
}