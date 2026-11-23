package ua.edu.dut.automaton;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestWordAutomatonTest {
    private final TestWordAutomaton automaton = new TestWordAutomaton();

    @ParameterizedTest(name = "input [{0}] ends in state {1}")
    @MethodSource("inputCases")
    void processesWholeStrings(String input, AutomatonState expectedState) {
        assertEquals(expectedState, automaton.process(input));
    }

    static Stream<Arguments> inputCases() {
        return Stream.of(
                Arguments.of("", AutomatonState.START),
                Arguments.of("abc", AutomatonState.START),
                Arguments.of("T", AutomatonState.ONE),
                Arguments.of("TE", AutomatonState.TWO),
                Arguments.of("TES", AutomatonState.THREE),
                Arguments.of("abcTES", AutomatonState.THREE),
                Arguments.of("TEST", AutomatonState.FINAL),
                Arguments.of("abcTESTabc", AutomatonState.FINAL),
                Arguments.of("test", AutomatonState.START),
                Arguments.of("TEX", AutomatonState.START),
                Arguments.of("TET", AutomatonState.ONE),
                Arguments.of("TTES", AutomatonState.THREE),
                Arguments.of("TTEST", AutomatonState.FINAL),
                Arguments.of("TETEST", AutomatonState.FINAL),
                Arguments.of("TESTanything", AutomatonState.FINAL),
                Arguments.of("TÉST", AutomatonState.ONE)
        );
    }

    @ParameterizedTest(name = "transition {0} + [{1}] -> {2}")
    @MethodSource("transitionCases")
    void appliesEveryTransition(AutomatonState state, char symbol, AutomatonState expectedState) {
        assertEquals(expectedState, TestWordAutomaton.transition(state, symbol));
    }

    static Stream<Arguments> transitionCases() {
        return Stream.of(
                Arguments.of(AutomatonState.START, 'T', AutomatonState.ONE),
                Arguments.of(AutomatonState.START, 'x', AutomatonState.START),
                Arguments.of(AutomatonState.ONE, 'E', AutomatonState.TWO),
                Arguments.of(AutomatonState.ONE, 'T', AutomatonState.ONE),
                Arguments.of(AutomatonState.ONE, 'x', AutomatonState.START),
                Arguments.of(AutomatonState.TWO, 'S', AutomatonState.THREE),
                Arguments.of(AutomatonState.TWO, 'T', AutomatonState.ONE),
                Arguments.of(AutomatonState.TWO, 'x', AutomatonState.START),
                Arguments.of(AutomatonState.THREE, 'T', AutomatonState.FINAL),
                Arguments.of(AutomatonState.THREE, 'x', AutomatonState.START),
                Arguments.of(AutomatonState.FINAL, 'x', AutomatonState.FINAL)
        );
    }

    @Test
    void rejectsNullInput() {
        assertThrows(IllegalArgumentException.class, () -> automaton.process(null));
    }
}
