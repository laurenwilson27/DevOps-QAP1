package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class SuggestionTest {
    private final SuggestionEngine testEngine = new SuggestionEngine();

    @Test
    // Simple test for when the letters are right, but in the wrong order
    public void testWrongLetterOrder() throws Exception {
        testEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));

        Assertions.assertTrue(testEngine.generateSuggestions("mnoday").contains("monday"));
    }

    @Test
    // Test if the engine handles a mistake with multiple obvious suggestions
    public void testMultipleSuggestions() throws Exception {
        testEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));

        String suggestions = testEngine.generateSuggestions("hellp");
        Assertions.assertTrue(suggestions.contains("hello"));
        Assertions.assertTrue(suggestions.contains("help"));
    }

    @Test
    // The suggestion engine should return no more than 10 words - test this
    public void testLimit10() throws Exception {
        testEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));

        Assertions.assertTrue(testEngine.generateSuggestions("frapple").split("\n").length <= 10);
    }

    @Test
    // This one's just silly - used to get this word wrong a lot :)
    public void testSmorgasbord() throws Exception {
        testEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));

        // Expected result is only two words: smorgasbord and smorgasbords
        Assertions.assertEquals(2, testEngine.generateSuggestions("smorgasborg").split("\n").length);
    }

    @Test
    // Check if a word exists in the SuggestionEngine after it has loaded the data from words.txt
    public void testWordLookup() throws Exception {
        testEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));

        // getWordSuggestionDB() returns a Map where each key is a known word
        Assertions.assertTrue(testEngine.getWordSuggestionDB().containsKey("dragon"));
    }
}
