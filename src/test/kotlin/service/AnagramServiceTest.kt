package service

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AnagramServiceTest {

    private val anagramService = AnagramService()

    @Test
    fun `findAnagrams returns empty list when no anagrams exist`() {
        val words = listOf("cat", "dog", "bird")
        val result = anagramService.findAnagrams(words)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `findAnagrams finds simple anagram pair`() {
        val words = listOf("listen", "silent")
        val result = anagramService.findAnagrams(words)

        assertEquals(1, result.size)
        assertEquals(2, result[0].anagrams.size)
        assertTrue(result[0].anagrams.contains("listen"))
        assertTrue(result[0].anagrams.contains("silent"))
    }

    @Test
    fun `findAnagrams groups multiple anagrams together`() {
        val words = listOf("listen", "silent", "enlist")
        val result = anagramService.findAnagrams(words)

        assertEquals(1, result.size)
        assertEquals(3, result[0].anagrams.size)
    }

    @Test
    fun `findAnagrams handles multiple anagram groups`() {
        val words = listOf("listen", "silent", "rate", "tear", "tare")
        val result = anagramService.findAnagrams(words)

        assertEquals(2, result.size)
    }

    @Test
    fun `findAnagrams excludes words without anagrams`() {
        val words = listOf("listen", "silent", "unique")
        val result = anagramService.findAnagrams(words)

        assertEquals(1, result.size)
        assertTrue(result[0].anagrams.none { it == "unique" })
    }

    @Test
    fun `findAnagrams returns empty list for empty input`() {
        val words = emptyList<String>()
        val result = anagramService.findAnagrams(words)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `findAnagrams handles single word`() {
        val words = listOf("hello")
        val result = anagramService.findAnagrams(words)

        assertTrue(result.isEmpty())
    }
}

