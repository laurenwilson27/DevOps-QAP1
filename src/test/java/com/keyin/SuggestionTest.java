package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Paths;

public class SuggestionTest {
    private final SuggestionEngine testEngine = new SuggestionEngine();

    @Test
    public void testGenerateSuggestions() throws Exception {
       testEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));

        Assertions.assertTrue(testEngine.generateSuggestions("hellw").contains("hello"));
    }
}
