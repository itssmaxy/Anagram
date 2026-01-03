package io

import domain.AnagramGroup
import domain.toAnagramKey
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AnagramWriterTest {

    @TempDir
    lateinit var tempDir: Path

    @Test
    fun `writeToFile creates file with anagram groups`() {
        val anagrams = listOf(
            AnagramGroup("listen".toAnagramKey(), listOf("listen", "silent", "enlist")),
            AnagramGroup("cat".toAnagramKey(), listOf("cat", "act", "tac"))
        )
        val outputPath = File(tempDir.toFile(), "output.txt").absolutePath

        AnagramWriter.writeToFile(anagrams, outputPath)

        val content = File(outputPath).readText()
        val lines = content.lines()
        assertEquals(2, lines.size)
        assertEquals("listen silent enlist", lines[0])
        assertEquals("cat act tac", lines[1])
    }

    @Test
    fun `writeToFile creates empty file for empty list`() {
        val anagrams = emptyList<AnagramGroup>()
        val outputPath = File(tempDir.toFile(), "empty_output.txt").absolutePath

        AnagramWriter.writeToFile(anagrams, outputPath)

        val content = File(outputPath).readText()
        assertTrue(content.isEmpty())
    }

    @Test
    fun `writeToFile handles single anagram group`() {
        val anagrams = listOf(
            AnagramGroup("rate".toAnagramKey(), listOf("rate", "tear", "tare"))
        )
        val outputPath = File(tempDir.toFile(), "single.txt").absolutePath

        AnagramWriter.writeToFile(anagrams, outputPath)

        val content = File(outputPath).readText()
        assertEquals("rate tear tare", content)
    }

    @Test
    fun `getOutputFilePath generates correct path`() {
        val inputPath = "/some/path/to/myfile.txt"

        val outputPath = AnagramWriter.getOutputFilePath(inputPath)

        assertEquals("src/main/resources/results/myfile_results.txt", outputPath)
    }

    @Test
    fun `getOutputFilePath handles filename without path`() {
        val inputPath = "words.txt"

        val outputPath = AnagramWriter.getOutputFilePath(inputPath)

        assertEquals("src/main/resources/results/words_results.txt", outputPath)
    }
}

