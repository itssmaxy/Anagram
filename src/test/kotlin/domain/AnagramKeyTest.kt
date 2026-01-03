package domain

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class AnagramKeyTest {

    @Test
    fun `toAnagramKey creates frequency-based key`() {
        val key = "hello".toAnagramKey()

        // Frequency array: a-z, æ, ø, å (29 positions)
        // e=1 (index 4), h=1 (index 7), l=2 (index 11), o=1 (index 14)
        val expected = "00001001000200100000000000000"
        assertEquals(expected, key.sortedWord)
    }

    @Test
    fun `anagrams produce the same key`() {
        val key1 = "listen".toAnagramKey()
        val key2 = "silent".toAnagramKey()

        assertEquals(key1, key2)
    }

    @Test
    fun `non-anagrams produce different keys`() {
        val key1 = "hello".toAnagramKey()
        val key2 = "world".toAnagramKey()

        assertNotEquals(key1, key2)
    }

    @Test
    fun `empty string produces zero frequency key`() {
        val key = "".toAnagramKey()

        assertEquals("00000000000000000000000000000", key.sortedWord)
    }

    @Test
    fun `single character produces correct key`() {
        val key = "a".toAnagramKey()

        // 'a' is at index 0
        assertEquals("10000000000000000000000000000", key.sortedWord)
    }

    @Test
    fun `repeated characters are counted in frequency`() {
        val key = "aaa".toAnagramKey()

        // 'a' appears 3 times at index 0
        assertEquals("30000000000000000000000000000", key.sortedWord)
    }

    @Test
    fun `case insensitive - uppercase and lowercase produce same key`() {
        val key1 = "Listen".toAnagramKey()
        val key2 = "LISTEN".toAnagramKey()

        assertEquals(key1, key2)
    }

    @Test
    fun `special nordic characters are handled`() {
        val key1 = "æøå".toAnagramKey()

        // æ at index 26, ø at index 27, å at index 28
        assertEquals("00000000000000000000000000111", key1.sortedWord)
    }

    @Test
    fun `non-alphabetic characters are ignored`() {
        val key1 = "abc123!@#".toAnagramKey()
        val key2 = "abc".toAnagramKey()

        assertEquals(key1, key2)
    }
}

