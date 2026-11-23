package ua.edu.dut.automaton;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            System.out.print("Введіть рядок для пошуку слова TEST: ");
            String input = scanner.nextLine();
            AutomatonState result = new TestWordAutomaton().process(input);
            System.out.println("Кінцевий стан автомата: " + result);
            if (result == AutomatonState.FINAL) {
                System.out.println("Слово TEST знайдено.");
            } else {
                System.out.println("Слова TEST у рядку немає.");
            }
        } finally {
            System.out.println("Обробку завершено.");
        }
    }
}
