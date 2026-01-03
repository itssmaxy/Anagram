package integration

import io.AnagramWriter
import io.FileReader
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import service.AnagramService
import java.io.File
import java.nio.file.Path
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AnagramIntegrationTest {

    private val anagramService = AnagramService()

    @TempDir
    lateinit var tempDir: Path

    @Test
    fun `full flow with whitespace separated words`() {
        // Arrange
        val inputFile = File(tempDir.toFile(), "input.txt")
        inputFile.writeText("listen silent enlist cat act tac dog")
        val outputFile = File(tempDir.toFile(), "output.txt")

        // Act
        val words = FileReader.readWords(inputFile.absolutePath)
        val anagrams = anagramService.findAnagrams(words)
        AnagramWriter.writeToFile(anagrams, outputFile.absolutePath)

        // Assert
        val result = outputFile.readText().lines()
        assertEquals(2, result.size)
        assertTrue(result[0].contains("listen"))
        assertTrue(result[0].contains("silent"))
        assertTrue(result[0].contains("enlist"))
        assertTrue(result[1].contains("cat"))
        assertTrue(result[1].contains("act"))
        assertTrue(result[1].contains("tac"))
    }

    @Test
    fun `full flow with newline separated words`() {
        // Arrange
        val inputFile = File(tempDir.toFile(), "input_newline.txt")
        inputFile.writeText("rate\ntear\ntare\nearth\nheart\nhater")
        val outputFile = File(tempDir.toFile(), "output_newline.txt")

        // Act
        val words = FileReader.readWords(inputFile.absolutePath)
        val anagrams = anagramService.findAnagrams(words)
        AnagramWriter.writeToFile(anagrams, outputFile.absolutePath)

        // Assert
        val result = outputFile.readText().lines()
        assertEquals(2, result.size)
    }

    @Test
    fun `full flow with no anagrams produces empty output`() {
        // Arrange
        val inputFile = File(tempDir.toFile(), "no_anagrams.txt")
        inputFile.writeText("apple banana cherry")
        val outputFile = File(tempDir.toFile(), "empty_output.txt")

        // Act
        val words = FileReader.readWords(inputFile.absolutePath)
        val anagrams = anagramService.findAnagrams(words)
        AnagramWriter.writeToFile(anagrams, outputFile.absolutePath)

        // Assert
        assertTrue(anagrams.isEmpty())
        assertTrue(outputFile.readText().isEmpty())
    }

    @Test
    fun `full flow matches expected output file`() {
        // Arrange - use test resources
        val inputPath = javaClass.classLoader.getResource("test_words.txt")?.path
            ?: throw IllegalStateException("test_words.txt not found")
        val expectedPath = javaClass.classLoader.getResource("expected/test_words_results.txt")?.path
            ?: throw IllegalStateException("expected/test_words_results.txt not found")

        val outputFile = File(tempDir.toFile(), "actual_output.txt")

        // Act
        val words = FileReader.readWords(inputPath)
        val anagrams = anagramService.findAnagrams(words)
        AnagramWriter.writeToFile(anagrams, outputFile.absolutePath)

        // Assert
        val expectedLines = File(expectedPath).readLines().sorted()
        val actualLines = outputFile.readLines().sorted()
        assertEquals(expectedLines.size, actualLines.size)
    }

    @Test
    fun `full flow with mixed whitespace and newlines`() {
        // Arrange
        val inputFile = File(tempDir.toFile(), "mixed.txt")
        inputFile.writeText("listen silent\nenlist\ncat act\ntac")
        val outputFile = File(tempDir.toFile(), "mixed_output.txt")

        // Act
        val words = FileReader.readWords(inputFile.absolutePath)
        val anagrams = anagramService.findAnagrams(words)
        AnagramWriter.writeToFile(anagrams, outputFile.absolutePath)

        // Assert
        val result = outputFile.readText().lines()
        assertEquals(2, result.size)
    }

    @Test
    fun `anagram count is correct`() {
        // Arrange
        val inputFile = File(tempDir.toFile(), "count_test.txt")
        inputFile.writeText("listen silent rate tear tare stop pots unique")

        // Act
        val words = FileReader.readWords(inputFile.absolutePath)
        val anagrams = anagramService.findAnagrams(words)

        // Assert
        assertEquals(3, anagrams.size) // 3 anagram groups
        assertEquals(2, anagrams.find { it.anagrams.contains("listen") }?.anagrams?.size) // listen, silent
        assertEquals(3, anagrams.find { it.anagrams.contains("rate") }?.anagrams?.size) // rate, tear, tare
        assertEquals(2, anagrams.find { it.anagrams.contains("stop") }?.anagrams?.size) // stop, pots
    }
}

