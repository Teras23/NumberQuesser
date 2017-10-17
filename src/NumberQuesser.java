import java.util.Random;

public class NumberQuesser {
    private Random random;
    private int seed;

    public NumberQuesser() {
        Random seedGenerator = new Random();
        seed = Math.abs(seedGenerator.nextInt() % 10000);
        random = new Random(seed);
        //System.out.println(seed);
    }

    public NumberQuesser(int seed) {

    }
}
