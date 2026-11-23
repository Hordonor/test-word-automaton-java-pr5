package ua.edu.dut.automaton;

import java.util.Objects;

/** DFA that reports whether the input contains the case-sensitive substring "TEST". */
public final class TestWordAutomaton {
    public AutomatonState process(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Вхідний рядок не може бути null.");
        }

        AutomatonState state = AutomatonState.START;
        for (int index = 0; index < input.length(); index++) {
            state = transition(state, input.charAt(index));
        }
        return state;
    }

    public static AutomatonState transition(AutomatonState state, char symbol) {
        Objects.requireNonNull(state, "Стан автомата не може бути null.");

        return switch (state) {
            case START -> symbol == 'T' ? AutomatonState.ONE : AutomatonState.START;
            // A T mismatch is also the beginning of a new possible TEST.
            case ONE -> symbol == 'E' ? AutomatonState.TWO
                    : symbol == 'T' ? AutomatonState.ONE : AutomatonState.START;
            // The suffix T of "TET" is the longest prefix of TEST.
            case TWO -> symbol == 'S' ? AutomatonState.THREE
                    : symbol == 'T' ? AutomatonState.ONE : AutomatonState.START;
            case THREE -> symbol == 'T' ? AutomatonState.FINAL : AutomatonState.START;
            case FINAL -> AutomatonState.FINAL;
        };
    }
}
