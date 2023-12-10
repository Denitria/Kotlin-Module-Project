fun checkScanValue(value: String): Boolean {
    try {
        value.toInt()
    } catch(e: NumberFormatException) {
        return false
    }

    return true
}