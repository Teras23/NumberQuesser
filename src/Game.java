import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private boolean handicapMode;
    private boolean running = false;
    private Difficulty difficulty;

    enum Difficulty {
        EASY, MEDIUM, HARD
    };

    public Game() {
        handicapMode = true;
        difficulty = Difficulty.EASY;
    }

    public void sethandicapMode(boolean handicapMode) {
        this.handicapMode = handicapMode;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void start() {
        /*System.out.println("Kas te tahate kergendatud mänguviisi? ([y]/n)");
        System.out.println("Kergendatud mänguviis ütleb, kas teie number on kadunud numbrist väikesem või suurem.");

        Scanner scanner = new Scanner(System.in);
        String hc = scanner.next();
        if(hc.equals("n")) {
            handicapMode = false;
        }

        if(handicapMode)
            System.out.println("Kergendatud mänguviis on sisse lülitatud");
        else
            System.out.println("Kergendatud mänguviis on välja lülitatud");
        */
        /*
        System.out.println("Valige mängu raskus:");
        System.out.println("1 - kerge\n2 - keskmine\n3 - raske");
        int dif = scanner.nextInt();

        switch (dif) {
            case 2:
                difficulty = Difficulty.MEDIUM;
                System.out.println("Mängu raskus on keskmine");
                break;
            case 3:
                difficulty = Difficulty.HARD;
                System.out.println("Mängu raskus on raske");
                break;
            default:
                difficulty = Difficulty.EASY;
                System.out.println("Mängu raskus on kerge");
                break;
        }
        */
        /*
        System.out.println("Kui on, sisestage sõbra mängukood (0000-9999):");
        String randomSeed = scanner.next();

        try{
            if(Integer.parseInt(randomSeed) >= 10000) {

            }
            else {
                generateSession();
            }
        }
        catch (NumberFormatException e) {
            generateSession();
        }
        */

        gameLoop();
    }

    private void generateSession() {

    }

    private void gameLoop() {
        running = true;
        while(running) {

            SequenceGenerator generator = new SequenceGenerator();
            Sequence s = generator.generate(10);

            IncrementModifier im = new IncrementModifier(1);
            SquareModifier sm = new SquareModifier();
            MultiplicationModifier mm = new MultiplicationModifier(2);

            //s.modify(im);
            int[] answers = s.getEnding();
            System.out.println(Arrays.toString(s.getBeginning()));
            System.out.println("Sisesta jada kolm järgmist arvu tühikuga eraldatuna. Kolm korda võid arvata.");

            boolean arvatud = false;

            for (int i = 0; i < 3; i++) {

                Scanner scanner = new Scanner(System.in);
                String hc = scanner.nextLine();
                String[] arvud = hc.split(" ");

                if (Integer.valueOf(arvud[0]).equals(answers[0])) {
                    if (Integer.valueOf(arvud[1]).equals(answers[1])) {
                        if (Integer.valueOf(arvud[2]).equals(answers[2])) {
                            arvatud = true;
                        }
                    }
                }

                if (arvatud) {
                    System.out.println("Tubli, see on õige!");
                }
                else {
                    System.out.println("Vale.");
                }
            }

            System.out.println("Kas te tahate mängu jätkata?");
            Scanner scanner = new Scanner(System.in);
            String hc = scanner.next();
            if(hc.equals("n")) {
                running = false;
            }
        }
    }
}
