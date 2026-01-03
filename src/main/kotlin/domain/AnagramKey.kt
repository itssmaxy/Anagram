package domain

data class AnagramKey(
    val sortedWord: String
)

fun String.toAnagramKey(): AnagramKey {
    val frequency = IntArray(size=29) //Only the alphabet
    for (char in this.lowercase()) {
        val index = when (char) {
            in 'a'..'z' -> char - 'a'
            'æ' -> 26
            'ø' -> 27
            'å' -> 28
            else -> continue
        }
        frequency[index]++
    }
    return AnagramKey(frequency.joinToString(""))
}