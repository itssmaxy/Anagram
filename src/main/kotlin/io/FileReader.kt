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

    fun selectFileFromResource(): String {
        val resourcesDir = File("src/main/resources")
        val files = resourcesDir.listFiles { file -> file.isFile && file.extension == "txt" }
            ?.sortedBy { it.name } ?: emptyList()

        if (files.isEmpty()) {
            println("No text files found in resources directory.")
            return ""
        }

        println("Available files:")
        files.forEachIndexed { index, file ->
            println("  ${index + 1}. ${file.name}")
        }

        print("\nSelect file number (default: 1): ")
        val input = readlnOrNull()?.trim()

        val selectedIndex = if (input.isNullOrEmpty()) {
            0
        } else {
            input.toIntOrNull()?.minus(1) ?: 0
        }

        return if (selectedIndex in files.indices) {
            files[selectedIndex].absolutePath
        } else {
            println("Invalid selection")
            ""
        }
    }
}