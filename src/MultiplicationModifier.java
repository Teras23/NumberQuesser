public class MultiplicationModifier extends SequenceModifier {
    private int multiplication;

    public MultiplicationModifier() {
        super();
        this.multiplication = 2;
    }

    public MultiplicationModifier(int multiplication) {
        super();
        this.multiplication = multiplication;
    }

    @Override
    public void modify(Sequence sequence) {
        int[] numbers = sequence.getNumbers();
        for(int i = start; i < numbers.length; i += step) {
            numbers[i] *= multiplication;
        }
    }
}
