public class SequenceGenerator {

    public SequenceGenerator() {

    }

    public Sequence generateEmpty(int length) {
        return new Sequence(length);
    }

    public Sequence generate(int length) {
        int[] sequence = new int[length];
        for(int i = 0; i < length; i++) {
            sequence[i] = i;
        }
        return new Sequence(sequence);
    }
}
