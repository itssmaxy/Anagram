package domain

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class AnagramKeyTest {

    @Test
    fun `toAnagramKey sorts letters alphabetically`() {
        val key = "hello".toAnagramKey()

        assertEquals("ehllo", key.sortedWord)
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
    fun `empty string produces empty key`() {
        val key = "".toAnagramKey()

        assertEquals("", key.sortedWord)
    }

    @Test
    fun `single character produces same key`() {
        val key = "a".toAnagramKey()

        assertEquals("a", key.sortedWord)
    }

    @Test
    fun `repeated characters are preserved`() {
        val key = "aaa".toAnagramKey()

        assertEquals("aaa", key.sortedWord)
    }

    @Test
    fun `numbers in string are sorted correctly`() {
        val key = "a1b2".toAnagramKey()

        assertEquals("12ab", key.sortedWord)
    }
}

