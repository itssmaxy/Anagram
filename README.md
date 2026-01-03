# Anagram Finder

A Kotlin-based command-line application that finds anagram groups from text files.

## Features

- Efficient O(n) anagram detection using character frequency counting
- Support for English alphabet plus Nordic characters (æ, ø, å)
- Case-insensitive matching
- Interactive file selection
- Output to console or file

## Requirements

- Java 24 or higher
- Kotlin 2.2.21 (included via Gradle wrapper)
- Input files stored under resources

## Building and Running

### Run the application
```bash
./gradlew run
```

### Run tests
```bash
./gradlew test
```

### Clean build artifacts
```bash
./gradlew clean
```

## Project Structure

```
src/
├── main/kotlin/
│   ├── Main.kt                    # Application entry point
│   ├── domain/
│   │   ├── AnagramGroup.kt        # Data model for anagram groups
│   │   └── AnagramKey.kt          # Frequency-based anagram key generator
│   ├── io/
│   │   ├── AnagramWriter.kt       # Output handling (console/file)
│   │   └── FileReader.kt          # Input file processing
│   ├── service/
│   │   └── AnagramService.kt      # Core anagram finding logic
│   └── resources/                 # Sample input files
└── test/kotlin/                   # Unit and integration tests
```

## Algorithm

The application uses a character frequency counting approach for anagram detection:
- Time complexity: O(n) where n is the word length
- Space complexity: O(1) using fixed-size frequency array (29 characters)
- More efficient than traditional O(n log n) sorting-based approaches

## Testing

The project includes comprehensive tests:
- Unit tests for each component
- Integration tests for full workflow
- 37 tests covering edge cases and normal operation
