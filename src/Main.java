public class Main {

    public static void main(String[] args) {
	    SequenceGenerator generator = new SequenceGenerator();
	    Sequence s = generator.generate(10);

	    IncrementModifier im = new IncrementModifier(3);
        SquareModifier sm = new SquareModifier();
        MultipicationModifier mm = new MultipicationModifier(2);

	    System.out.println(s);

	    s.modify(im);

	    System.out.println(s);

	    s.modify(sm);

	    System.out.println(s);

	    s.modify(mm);

	    System.out.println(s);

	    s.modify(im);

	    System.out.println(s);

	    NumberQuesser nq = new NumberQuesser();

	    Game game = new Game();
	    game.start();
    }
}
