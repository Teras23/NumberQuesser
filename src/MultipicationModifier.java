public class MultipicationModifier implements SequenceModifier {
    private int multiplication;
    private int step;
    private int start;

    public MultipicationModifier() {
        this.multiplication = 2;
        this.step = 1;
        this.start = 0;
    }

    public MultipicationModifier(int multiplication) {
        this();
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
