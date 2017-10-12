public class SquareModifier extends SequenceModifier {

    public SquareModifier() {
        super();
    }

    public SquareModifier(int start, int step) {
        super(start, step);
    }

    @Override
    public void modify(Sequence sequence) {
        int[] numbers = sequence.getNumbers();
        for(int i = start; i < numbers.length; i += step) {
            numbers[i] *= numbers[i];
        }
    }
}
