public class IncrementModifier implements SequenceModifier {
    private int increment;
    private int start;
    private int step;

    public IncrementModifier() {
        this.increment = 1;
        this.start = 0;
        this.step = 1;
    }

    public IncrementModifier(int increment) {
        this();
        this.increment = increment;
    }

    public IncrementModifier(int increment, int start, int step) {
        this.increment = increment;
        this.start = start;
        this.step = step;
    }

    @Override
    public void modify(Sequence sequence) {
        int[] numbers = sequence.getNumbers();
        for(int i = start; i < numbers.length; i += step) {
            numbers[i] += increment;
        }
    }
}
