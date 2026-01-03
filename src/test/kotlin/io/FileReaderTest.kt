package io

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FileReaderTest {

    @TempDir
    lateinit var tempDir: Path

    @Test
    fun `readFile reads entire file content`() {
        val file = File(tempDir.toFile(), "test.txt")
        file.writeText("hello world")

        val content = FileReader.readFile(file.absolutePath)

        assertEquals("hello world", content)
    }

    @Test
    fun `readWords splits file into word list`() {
        val file = File(tempDir.toFile(), "words.txt")
        file.writeText("hello\nworld\ntest")

        val words = FileReader.readWords(file.absolutePath)

        assertEquals(3, words.size)
        assertEquals("hello", words[0])
        assertEquals("world", words[1])
        assertEquals("test", words[2])
    }

    @Test
    fun `readWords filters empty lines`() {
        val file = File(tempDir.toFile(), "words.txt")
        file.writeText("hello\n\nworld")

        val words = FileReader.readWords(file.absolutePath)

        assertEquals(2, words.size)
    }

    @Test
    fun `readWords handles single word`() {
        val file = File(tempDir.toFile(), "single.txt")
        file.writeText("hello")

        val words = FileReader.readWords(file.absolutePath)

        assertEquals(1, words.size)
        assertEquals("hello", words[0])
    }

    @Test
    fun `readWords returns empty list for empty file`() {
        val file = File(tempDir.toFile(), "empty.txt")
        file.writeText("")

        val words = FileReader.readWords(file.absolutePath)

        assertTrue(words.isEmpty())
    }

    @Test
    fun `readWords handles whitespace between words`() {
        val file = File(tempDir.toFile(), "spaces.txt")
        file.writeText("hello   world")

        val words = FileReader.readWords(file.absolutePath)

        assertEquals(2, words.size)
        assertEquals("hello", words[0])
        assertEquals("world", words[1])
    }
}

