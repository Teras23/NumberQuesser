import java.util.Arrays;

public class Sequence {
    private int[] numbers;
    private int guessNumberIndex = -1;

    public Sequence(int length) {
        this.numbers = new int[length];
    }

    public Sequence(int[] numbers) {
        this.numbers = numbers;
    }

    public void modify(SequenceModifier modifier) {
        modifier.modify(this);
    }

    public int getGuessNumberIndex() {
        return guessNumberIndex;
    }

    public void setGuessNumberIndexAsMiddle() {
        this.guessNumberIndex = numbers.length/2;
    }

    public void setGuessNumberIndex(int guessNumberIndex) {
        this.guessNumberIndex = guessNumberIndex;
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

    public int getMiddle() {
        return numbers[numbers.length/2];
    }

    public int[] getEdges() {
        int[] edges = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            if (i == numbers.length/2) {
                edges[i] = 0;
            }
            else {
                edges[i] = numbers[i];
            }
        }
        return edges;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < numbers.length; i++) {
            if(i != guessNumberIndex) {
                text.append(numbers[i]);
            }
            else {
                text.append("X");
            }

            if(i < numbers.length - 1) {
                text.append(", ");
            }
        }
        return text.toString();
    }
}
