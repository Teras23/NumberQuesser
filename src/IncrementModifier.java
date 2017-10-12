public class IncrementModifier extends SequenceModifier {
    private int increment;

    public IncrementModifier() {
        super();
        this.increment = 1;
    }

    public IncrementModifier(int increment) {
        super();
        this.increment = increment;
    }

    public IncrementModifier(int increment, int start, int step) {
        super(start, step);
        this.increment = increment;
    }

    @Override
    public void modify(Sequence sequence) {
        int[] numbers = sequence.getNumbers();
        for(int i = start; i < numbers.length; i += step) {
            numbers[i] += increment;
        }
    }
}
