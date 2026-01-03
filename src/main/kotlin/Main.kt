import service.AnagramService
import io.FileReader
import io.AnagramWriter
import java.io.File

fun main() {
    println("=== Anagram Finder ===\n")

    // Select input file
    val filePath = FileReader.selectFileFromResource()
    if (filePath.isEmpty()) {
        println("No valid file selected. Exiting.")
        return
    }

    val fileName = File(filePath).name
    println("\nProcessing anagrams from: $fileName")
    val anagramService = AnagramService()
    val words = FileReader.readWords(filePath)
    val anagrams = anagramService.findAnagrams(words)

    if (anagrams.isEmpty()) {
        println("No anagrams found.")
        return
    } else {
        println("Anagrams found!")
    }

    if (AnagramWriter.askToSaveToFile()) {
        val outputPath = AnagramWriter.getOutputFilePath(filePath)
        AnagramWriter.writeToFile(anagrams, outputPath)
        println("\nResults saved to: $outputPath")
    }
    else {
        println("\nWill print out results to console:")
        AnagramWriter.writeToConsole(anagrams)
    }

    println("\nDone!")
}

