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
        System.out.println("Kas te tahate kergendatud mänguviisi? ([y]/n)");
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


        gameLoop();
    }

    private void generateSession() {

    }

    private void gameLoop() {
        running = true;
        while(running) {
            System.out.println("Kas te tahate mängu jätkata?");
            Scanner scanner = new Scanner(System.in);
            String hc = scanner.next();
            if(hc.equals("n")) {
                running = false;
            }
        }
    }
}
