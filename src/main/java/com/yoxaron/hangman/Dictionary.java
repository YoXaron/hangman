package com.yoxaron.hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Dictionary {

    private static final String FILE = "dictionary.txt";
    private static final Random random = new Random();

    private final List<String> words;

    public Dictionary() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            words = reader.lines().collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }

    public String getWordMask(List<Character> letters, Set<Character> guessedLetters) {
        StringBuilder sb = new StringBuilder();
        for (char c : letters) {
            if (guessedLetters.contains(c)) {
                sb.append(c);
            } else {
                sb.append("_");
            }
        }
        return sb.toString();
    }
}
