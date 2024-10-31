package com.yoxaron.hangman;

public class Gallows {

    private final static String STATE_0 = """
            ______
            |/   |
            |
            |
            |
            |
            |
            """;

    private final static String STATE_1 = """
            ______
            |/   |
            |  (._.)
            |
            |
            |
            |
            """;

    private final static String STATE_2 = """
            ______
            |/   |
            |  (._.)
            |    |
            |    |
            |
            |
            """;

    private final static String STATE_3 = """
            ______
            |/   |
            |  (._.)
            |   /|
            |    |
            |
            |
            """;

    private final static String STATE_4 = """
            ______
            |/   |
            |  (._.)
            |   /|\\
            |    |
            |
            |
            """;

    private final static String STATE_5 = """
            ______
            |/   |
            |  (._.)
            |   /|\\
            |    |
            |   /
            |
            """;

    private final static String STATE_6 = """
            ______
            |/   |
            |  (x_x)
            |   /|\\
            |    |
            |   / \\
            |
            """;

    public  static void printGallows(int numberOfMistakes) {
        String gallows = switch (numberOfMistakes) {
            case 0 -> STATE_0;
            case 1 -> STATE_1;
            case 2 -> STATE_2;
            case 3 -> STATE_3;
            case 4 -> STATE_4;
            case 5 -> STATE_5;
            case 6 -> STATE_6;
            default -> throw new IllegalStateException("Unexpected value: " + numberOfMistakes);
        };
        System.out.println(gallows);
    }
}
