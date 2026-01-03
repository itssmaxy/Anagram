package service

import domain.AnagramGroup
import domain.toAnagramKey

class AnagramService {


    fun findAnagrams(words: List<String>): List<AnagramGroup> {

        val anagrams = words
            .groupBy { it.toAnagramKey() }
            .filter { it.value.size > 1 }
            .map { AnagramGroup(it.key, it.value) }

        return anagrams
    }



}