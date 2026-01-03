

import service.AnagramService
import io.FileReader
import io.AnagramWriter


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {


    val anagramService = AnagramService()
    var filePath = "src/main/resources/eventyr.txt"
    val words = FileReader.readWords(filePath)
    val anagrams = anagramService.findAnagrams(words)


    AnagramWriter.writeToConsole(anagrams)

}
