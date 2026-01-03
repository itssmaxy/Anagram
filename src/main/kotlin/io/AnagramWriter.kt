package io

import domain.AnagramGroup

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
        java.io.File(path).writeText(fileContent)
    }
}