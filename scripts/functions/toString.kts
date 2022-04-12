
fun String?.toStringQuoted() = this?.let { "'$this'" } ?: "null"