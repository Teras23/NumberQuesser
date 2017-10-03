import java.util.Arrays;

public class Sequence {
    private int[] numbers;

    public Sequence(int length) {
        this.numbers = new int[length];
    }

    public Sequence(int[] numbers) {
        this.numbers = numbers;
    }

    public void modify(SequenceModifier modifier) {
        modifier.modify(this);
    }

    public int[] getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers);
    }
}
