import java.io.File
import java.nio.file.Files
import java.nio.file.Path

fun File.moveTo(dir: File): Path? {
    return try {
        Files.move(this.toPath(), dir.toPath())
    } catch (e: Exception) {
        println("Failed move: '$this' --> '${dir.absolutePath}', reason: $e")
        null // failed
    }
} // , StandardCopyOption.REPLACE_EXISTING

fun File.moveToDir(dir: File): Path? {
    check(dir.isDirectory) { "$dir is not a directory" }
    return try {
        val targetFile = File(dir, this.name)
        Files.move(this.toPath(), targetFile.toPath())
    } catch (e: Exception) {
        println("Failed move: '$this' --> '${dir.absolutePath}', reason: $e")
        null // failed
    }
} // , StandardCopyOption.REPLACE_EXISTING
