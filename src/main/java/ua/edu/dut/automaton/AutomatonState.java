package ua.edu.dut.automaton;

public enum AutomatonState {
    START("S"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FINAL("F");

    private final String label;

    AutomatonState(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
