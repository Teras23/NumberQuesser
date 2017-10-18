import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private boolean handicapMode;
    private boolean running = false;
    //private Difficulty difficulty;
    private int difficulty;

    /*enum Difficulty {
        EASY, MEDIUM, HARD
    };*/

    public Game() {
        handicapMode = true;
        //difficulty = Difficulty.EASY;
        difficulty = 1;
    }

    public void sethandicapMode(boolean handicapMode) {
        this.handicapMode = handicapMode;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);

        /*System.out.println("Kas te tahate kergendatud mänguviisi? ([y]/n)");
        System.out.println("Kergendatud mänguviis ütleb, kas teie number on kadunud numbrist väikesem või suurem.");

        String hc = scanner.next();
        if(hc.equals("n")) {
            handicapMode = false;
        }

        if(handicapMode)
            System.out.println("Kergendatud mänguviis on sisse lülitatud");
        else
            System.out.println("Kergendatud mänguviis on välja lülitatud");
        */

        System.out.println("Valige jada raskus:");
        System.out.println("1 - kerge\n2 - keskmine\n3 - raske");

        String dif = scanner.next().trim();

        switch (dif) {
            case "2":
                difficulty = 2;
                System.out.println("Jada raskus on keskmine");
                break;
            case "3":
                difficulty = 3;
                System.out.println("Jada raskus on raske");
                break;
            default:
                difficulty = 1;
                System.out.println("Jada raskus on kerge");
                break;
        }

        System.out.println();

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

        System.out.println();
        gameLoop();
    }

    private void generateSession() {

    }

    private void gameLoop() {
        running = true;
        while(running) {

            SequenceGenerator generator = new SequenceGenerator();
            Sequence s = generator.generate(10);

            Random random = new Random();

            String[] explanations = new String[difficulty];

            for (int i = 0; i < difficulty; i++) {
                int rnd1 = random.nextInt(3);
                int rnd2 = random.nextInt(4) + 1;

                if (rnd1 == 0) {
                    IncrementModifier m = new IncrementModifier(rnd2);
                    explanations[i] = "suurendati " + String.valueOf(rnd2) + " võrra";
                    s.modify(m);
                }
                if (rnd1 == 1) {
                    MultiplicationModifier m = new MultiplicationModifier(rnd2);
                    explanations[i] = "korrutati " + String.valueOf(rnd2) + "-ga";
                    s.modify(m);
                }
                if (rnd1 == 2) {
                    SquareModifier m = new SquareModifier();
                    explanations[i] = "võeti ruutu";
                    s.modify(m);
                }
            }

            String explanation = "Kõigepealt võeti täisarvud alates nullist ja seejärel iga liige ";

            for (int i = 0; i < explanations.length; i++) {
                explanation += explanations[i];
                if (i < explanations.length - 1) {
                    explanation += " ning siis ";
                }
                else if (i < explanations.length - 2) {
                    explanation += ", siis";
                }
            }
            explanation += ". ";

            int[] answers = s.getEnding();
            System.out.println(Arrays.toString(s.getBeginning()));
            System.out.println("Sisesta jada kolm järgmist arvu tühikuga eraldatuna. Kolm korda võid arvata.");

            boolean guessed = false;

            for (int i = 0; i < 3; i++) {

                Scanner scanner = new Scanner(System.in);
                String hc = scanner.nextLine();
                String[] arvud = hc.split(" ");

                try {
                    if (Integer.valueOf(arvud[0]).equals(answers[0])) {
                        if (Integer.valueOf(arvud[1]).equals(answers[1])) {
                            if (Integer.valueOf(arvud[2]).equals(answers[2])) {
                                guessed = true;
                            }
                        }
                    }
                }
                catch(Exception e) {

                }

                if (guessed) {
                    System.out.println("Tubli, see on õige!");
                    break;
                }
                else {
                    System.out.println("Vale.");
                    if (i == 2) {
                        System.out.println("Jada õige jätk oli: " + answers[0] + ", " + answers[1] + ", " + answers[2]);
                        System.out.println(explanation);
                        System.out.println();
                    }
                }
            }

            System.out.println("Kas te tahate mängu jätkata? [jah/ei]");
            Scanner scanner = new Scanner(System.in);
            String hc = scanner.next();
            if(hc.equals("ei")) {
                running = false;
            }
        }

        System.out.println("Aitäh mängimast!");
    }
}
