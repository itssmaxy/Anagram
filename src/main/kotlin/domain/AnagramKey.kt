package domain

data class AnagramKey(
    val sortedWord: String
)

fun String.toAnagramKey(): AnagramKey {
    val sortedWord = this.toCharArray().sorted().joinToString("")
    return AnagramKey(sortedWord)
}