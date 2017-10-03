public class SquareModifier implements SequenceModifier {

    int start;
    int step;

    public SquareModifier() {
        this.start = 0;
        this.step = 1;
    }

    public SquareModifier(int start, int step) {
        this.start = start;
        this.step = step;
    }

    @Override
    public void modify(Sequence sequence) {
        int[] numbers = sequence.getNumbers();
        for(int i = start; i < numbers.length; i += step) {
            numbers[i] *= numbers[i];
        }
    }
}
