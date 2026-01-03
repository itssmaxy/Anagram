package domain

data class AnagramGroup(
    val key: AnagramKey,
    val anagrams: List<String>
)
