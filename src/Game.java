import java.util.*;

import static java.lang.Integer.parseInt;

public class Game {

    private boolean handicapMode;
    private boolean running = false;
    private Difficulty difficulty;
    private int sessionId;
    private static HashMap<Difficulty, Integer> difficultySettings;
    static {
        difficultySettings = new HashMap<>();
        difficultySettings.put(Difficulty.EASY, 1);
        difficultySettings.put(Difficulty.MEDIUM, 2);
        difficultySettings.put(Difficulty.HARD, 3);

    }

    enum Difficulty {
        EASY, MEDIUM, HARD
    }

    public Game() {
        handicapMode = false;
        difficulty = Difficulty.EASY;
    }

    public void start() {
        System.out.println("Tere tulemast numbrite äraarvamismängu NumberQuesser!");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Kui on, sisestage sõbra mängukood, kui ei ole, vajutage enter:");
        String sessionCode = scanner.nextLine();

        if(!readSessionCode(sessionCode)) {
            generateSession();

            System.out.println("Kas te tahate kergendatud mänguviisi? (jah/ei)");
            System.out.println("Kergendatud mänguviisis tuleb ära arvata üks jada keskel asuv arv.\n" +
                    "Tavalises mänguviisis tuleb ära arvata jada järgmised kolm numbrit.");

            String hc = scanner.next().trim();
            if (hc.equals("jah")) {
                handicapMode = true;
            }

            if (handicapMode)
                System.out.println("Kergendatud mänguviis on sisse lülitatud");
            else
                System.out.println("Kergendatud mänguviis on välja lülitatud");

            System.out.println("Valige jada raskus:");
            System.out.println("1 - kerge\n2 - keskmine\n3 - raske");

            String dif = scanner.next().trim();

            switch (dif) {
                case "2":
                    difficulty = Difficulty.MEDIUM;
                    System.out.println("Jada raskus on keskmine");
                    break;
                case "3":
                    difficulty = Difficulty.HARD;
                    System.out.println("Jada raskus on raske");
                    break;
                default:
                    difficulty = Difficulty.EASY;
                    System.out.println("Jada raskus on kerge");
                    break;
            }
        }

        System.out.println("Mängite mängu koodiga " + getSessionCode());

        gameLoop();
    }

    private void generateSession() {
        this.sessionId = new Random().nextInt(10000);
    }

    private boolean readSessionCode(String sc) {
        if(sc.length() == 6) {
            switch (sc.charAt(0)) {
                case 'H':
                    handicapMode = true;
                    break;
                case 'N':
                    handicapMode = false;
                    break;
                default:
                    System.out.println("Sobimatu mängukood");
                    return false;
            }

            switch (sc.charAt(1)) {
                case 'E':
                    difficulty = Difficulty.EASY;
                    break;
                case 'M':
                    difficulty = Difficulty.MEDIUM;
                    break;
                case 'H':
                    difficulty = Difficulty.HARD;
                    break;
                default:
                    System.out.println("Sobimatu mängukood");
                    return false;
            }
            try {
                sessionId = Integer.parseInt(sc.substring(2));
            }
            catch (Exception e) {
                System.out.println("Sobimatu mängukood");
                return false;
            }
            return true;
        }
        return false;
    }

    private String getSessionCode() {
        StringBuilder sb = new StringBuilder();

        if(handicapMode) {
            sb.append('H');
        }
        else {
            sb.append('N');
        }

        switch (difficulty) {
            case EASY:
                sb.append('E');
                break;
            case MEDIUM:
                sb.append('M');
                break;
            case HARD:
                sb.append('H');
                break;
        }

        sb.append(String.format("%04d", sessionId));
        return sb.toString();
    }

    private void gameLoop() {
        running = true;
        Random random = new Random(sessionId);
        while(running) {

            SequenceGenerator generator = new SequenceGenerator();
            Sequence s = generator.generate(10);
            s.modify(new IncrementModifier(1));

            String[] explanations = new String[difficultySettings.get(difficulty)];

            for (int i = 0; i < explanations.length; i++) {
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

            String explanation = "Kõigepealt võeti täisarvud alates ühest ja seejärel iga liige ";

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

            if (!handicapMode) {
                int[] answers = s.getEnding();
                System.out.println(Arrays.toString(s.getBeginning()));
                System.out.println("Sisesta jada kolm järgmist arvu tühikuga eraldatuna. Kolm korda võid arvata.");

                boolean guessed = false;

                for (int i = 0; i < 3; i++) {

                    Scanner scanner = new Scanner(System.in);
                    String hc = scanner.nextLine().trim();
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
                    catch (Exception e) {

                    }

                    if (guessed) {
                        System.out.println("Tubli, see on õige!");
                        break;
                    } else {
                        System.out.println("Vale.");
                        if (i == 2) {
                            System.out.println("Jada õige jätk oli: " + answers[0] + ", " + answers[1] + ", " + answers[2]);
                            System.out.println(explanation);
                            System.out.println();
                        }
                    }
                }
            }

            else {
                int answer = s.getMiddle();
                s.setGuessNumberIndexAsMiddle();
                System.out.println(s.toString());
                System.out.println("Sisesta jada keskel olev X-ga varjatud arv. Kolm korda võid arvata. ");

                boolean guessed = false;

                for (int i = 0; i < 3; i++) {

                    Scanner scanner = new Scanner(System.in);
                    String input = scanner.nextLine().trim();

                    try {
                        if (Integer.valueOf(input).equals(answer)) {
                            guessed = true;
                        }
                    }
                    catch (Exception e) {

                    }

                    if (guessed) {
                        System.out.println("Tubli, see on õige!");
                        break;
                    } else {
                        System.out.println("Vale.");
                        if (i == 2) {
                            System.out.println("Jada õige puuduv liige oli: " + answer);
                            System.out.println(explanation);
                            System.out.println();
                        }
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

        System.out.println("Laske sõbral ka proovida: " + getSessionCode());
        System.out.println("Aitäh mängimast!");
    }
}
