package io

import domain.AnagramGroup
import java.io.File

object AnagramWriter {

    fun writeToConsole(anagramResult: List<AnagramGroup>) {
        anagramResult.forEach {
            group -> println(group.anagrams.joinToString(" "))
        }
    }

    fun writeToFile(anagramResult: List<AnagramGroup>, path: String) {
        val fileContent = anagramResult.joinToString("\n") { group ->
            group.anagrams.joinToString(" ")
        }
        File(path).writeText(fileContent)
    }

    fun askToSaveToFile(): Boolean {
        print("\nDo you want to save results to a file instead of console? (y/n, default: n): ")
        val input = readlnOrNull()?.trim()?.lowercase()
        return input == "y" || input == "yes"
    }

    fun getOutputFilePath(inputFilePath: String): String {
        val inputFile = File(inputFilePath)
        val inputFileName = inputFile.nameWithoutExtension
        val outputPath = "src/main/resources/results/${inputFileName}_results.txt"

        // Create parent directories if they don't exist
        val outputFile = File(outputPath)
        outputFile.parentFile?.mkdirs()

        return outputPath
    }
}