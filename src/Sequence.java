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

    public int[] getEnding() {
        int[] ending = {numbers[numbers.length-3], numbers[numbers.length-2], numbers[numbers.length-1]};
        return ending;
    }

    public int[] getBeginning() {
        int[] beginning = new int[numbers.length-3];
        for (int i = 0; i < numbers.length - 3; i++) {
            beginning[i] = numbers[i];
        }
        return beginning;
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers);
    }
}
