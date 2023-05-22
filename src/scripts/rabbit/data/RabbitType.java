package scripts.rabbit.data;

public enum RabbitType {
    Common(0),
    Albino(1);

    private final int value;
    private RabbitType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
