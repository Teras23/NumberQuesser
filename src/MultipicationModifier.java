public class MultipicationModifier extends SequenceModifier {
    private int multiplication;

    public MultipicationModifier() {
        super();
        this.multiplication = 2;
    }

    public MultipicationModifier(int multiplication) {
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
