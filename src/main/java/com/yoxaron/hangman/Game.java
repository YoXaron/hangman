package com.yoxaron.hangman;

import java.util.*;

import static com.yoxaron.hangman.Gallows.*;

public class Game {

    private enum GameState {
        PLAYER_WON, PLAYER_LOST, NOT_FINISHED
    }

    private static final int HEALTH_POINTS = 6;
    private static final Scanner scanner = new Scanner(System.in);
    private static final Dictionary dictionary = new Dictionary();

    public static void main(String[] args) {
        do {
            startGameRound();
            System.out.print("Для новой игры нажмите 'Enter'. Для выхода введите 'exit': ");
        } while (!"exit".equalsIgnoreCase(scanner.nextLine()));
    }

    public static void startGameRound() {
        System.out.println("Начало нового раунда!");
        startGameLoop(dictionary.getRandomWord());
    }

    public static void startGameLoop(String word) {
        int numberOfMistakes = 0;
        Set<Character> guessedLetters = new HashSet<>();
        List<Character> letters = toCharList(word);

        do {
            printGallows(numberOfMistakes);
            System.out.printf("Слово: %s\n", dictionary.getWordMask(letters, guessedLetters));
            System.out.printf("Количество жизней: %d\n", (HEALTH_POINTS - numberOfMistakes));

            char inputLetter = getAndValidateInput(guessedLetters);
            guessedLetters.add(inputLetter);
            if (!letters.contains(inputLetter)) {
                numberOfMistakes++;
            }

            GameState gameState = checkGameState(numberOfMistakes, letters, guessedLetters);
            if (gameState != GameState.NOT_FINISHED) {
                handleEndOfGame(gameState, word);
                break;
            }
        } while (numberOfMistakes < HEALTH_POINTS);
    }

    private static List<Character> toCharList(String word) {
        List<Character> charList = new ArrayList<>();
        for (char c : word.toCharArray()) {
            charList.add(c);
        }
        return charList;
    }

    private static void handleEndOfGame(GameState gameState, String word) {
        if (gameState == GameState.PLAYER_WON) {
            System.out.printf("\nПобеда!!! Вы отгадали слово '%s'\n", word);
        } else if (gameState == GameState.PLAYER_LOST) {
            printGallows(HEALTH_POINTS);
            System.out.printf("Не повезло... Было загадано слово '%s'\n", word);
        }
    }

    private static GameState checkGameState(int numberOfMistakes, List<Character> letters, Set<Character> guessedLetters) {
        if (numberOfMistakes < HEALTH_POINTS) {
            if (dictionary.getWordMask(letters, guessedLetters).contains("_")) {
                return GameState.NOT_FINISHED;
            } else {
                return GameState.PLAYER_WON;
            }
        } else {
            return GameState.PLAYER_LOST;
        }
    }

    private static char getAndValidateInput(Set<Character> guessedLetters) {
        System.out.print("Введите букву: ");
        String input = scanner.nextLine();
        while (true) {
            if (input.isBlank() || input.length() > 1) {
                System.out.print("Введите одну букву: ");
                input = scanner.nextLine();
                continue;
            }
            if (Character.UnicodeBlock.of(input.charAt(0)) != Character.UnicodeBlock.CYRILLIC) {
                System.out.print("Введите букву кириллицы: ");
                input = scanner.nextLine();
                continue;
            }
            if (guessedLetters.contains(input.charAt(0))) {
                System.out.print("Такая буква уже была. Введите новое значение: ");
                input = scanner.nextLine();
                continue;
            }
            return input.charAt(0);
        }
    }
}
