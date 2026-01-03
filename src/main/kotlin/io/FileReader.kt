package io
import java.io.File


object FileReader {

    fun readFile(path: String): String {
        return File(path).readText()
    }

    fun readWords(filename: String): List<String> {
        return readFile(filename)
            .split(Regex("\\s+"))
            .filter { it.isNotEmpty() }
    }
}