import java.awt.Point;
import java.util.Random;
public class Main {

    static Character player = new Character("Elrond",
            "🧝🏻‍",
            new Point(new Random().nextInt(20) + 1, new Random().nextInt(10) + 1));


    public static void main(String[] args) {

        // Menü
        byte chosen = 0;
        do {
            System.out.println("Willkommen in Governia");
            System.out.println("Wähle Deinen nächsten Schritt!");
            System.out.println("1 - Neues Spiel starten");
            //System.out.println("2 - Spiel laden");
            System.out.println("3 - Neuen Charakter erstellen");
            System.out.println("4 - Spiel beenden");

            chosen = new java.util.Scanner(System.in).nextByte();

            switch (chosen) {
                case 1:
                    System.out.println("Das neue Spiel startet jetzt!");
                    startGame();
                    break;
                case 2:
                    System.out.println("Spielstand wird geladen");
                    break;
                case 3:
                    System.out.println("Erstelle jetzt deinen neuen Charakter");
                    createPlayer();

                    break;
                case 4:
                    System.out.println("Spiel wird beendet");
                    break;
                default:
                    System.out.println("Falsche Eingabe! Versuche es noch einmal");
            }
        } while (chosen != 4);

    }


    public static void startGame(){


        Goblin goblin = new Goblin();

        Dragon dragon = new Dragon();


        // Spieler zufällig platzieren
        //player.setPosition(new Point(new Random().nextInt(20) + 1, new Random().nextInt(10) + 1));
        //player.setAvatar("🧝🏻‍");
        //player.setName("Elrond");



        // Schatz zufällig platzieren und wenn Schatz und Spieler auf der gleichen Position liegen, neu platzieren
        Point treasure;
        do {
            treasure = new Point(new Random().nextInt(20) + 1, new Random().nextInt(10) + 1);
        } while (player.currentPosition(treasure));

        // Goblin zufällig platzieren und prüfen, ob er auf derselben Position liegt, wie Schatz oder Spieler, dann neu platzieren
        do {
            goblin.setPosition(new Point(new Random().nextInt(20) + 1, new Random().nextInt(10) + 1));
        } while (player.currentPosition(goblin.getPosition()) || treasure.equals(goblin.getPosition()));

        // Drachen zufällig platzieren und prüfen, ob er auf derselben Position liegt, wie Schatz oder Spieler, dann neu platzieren
        do {
            dragon.setPosition(new Point(new Random().nextInt(20) + 1, new Random().nextInt(10) + 1));
        } while (player.currentPosition(dragon.getPosition()) || treasure.equals(dragon.getPosition()) || goblin.currentPosition(dragon.getPosition()));


        // Tür analog der anderen Objekte
        // Tür immer am Rand positionieren
        // hierfür eine Hilfsvariable axis einführen, diese kann die Werte 1 bis 4 zufällig annehmen
        // 1 bedeutet, die Tür liegt am oberen Rand (y = 1)
        // 2 bedeutet, die Tür liegt am unteren Rand (y = 10)
        // 3 bedeutet, die Tür liegt am linken Rand (x = 1)
        // 4 bedeutet, die Tür liegt am rechten Rand (x = 20)
        Point door = new Point(0,0);
        do {
            int axis = new Random().nextInt(4) + 1;
            switch (axis){
                case 1 -> door.setLocation(new Point(new Random().nextInt(20) + 1, 1));
                case 2 -> door.setLocation(new Point(new Random().nextInt(20) + 1, 10));
                case 3 -> door.setLocation(new Point(1, new Random().nextInt(10) + 1));
                case 4 -> door.setLocation(new Point(20, new Random().nextInt(10) + 1));
            }
        } while (player.currentPosition(door) || treasure.equals(door) || goblin.currentPosition(door));


        boolean wall = false;
        //boolean hasFoundTreasure = false;
        //boolean isDeadGoblin = false;
        boolean playerstats = false;

        do {
            // Spielfeld zeichnen
            for (int y = 1; y <= 10; y++) { // Zeilennummer, y-Achse
                for (int x = 1; x <= 20; x++) { // x-Achse

                    // Spieler eintragen
                    if (y == player.getPosition().y && x == player.getPosition().x) {
                        System.out.print(player.getAvatar());
                    } else if (y == treasure.y && x == treasure.x) {
                        System.out.print("👑");
                    } else if (y == door.y && x == door.x) {
                        System.out.print("🚪");
                    } else if (y == goblin.getPosition().y && x == goblin.getPosition().x){
                        //if (!goblin.isAlive()){
                            System.out.print(goblin.getAvatar());
                        //} else {
                        //    System.out.print("👹");
                        //}
                    } else if (y == dragon.getPosition().y && x == dragon.getPosition().x) {
                        System.out.print("🐉");
                    } else {
                        System.out.print("⬛️");
                    }
                }
                System.out.println();
            }

            if (wall) {
                System.out.println("Da ist eine Wand!");
                wall = false;
            }

            if (player.currentPosition(treasure)){
                System.out.println("Du hast den Schatz gefunden!");
                //System.out.println("Du hast das Spiel gewonnen");
                //return;
                treasure.setLocation(0,0);
                player.pickUpTreasure();
                player.levelUp();
            }

            if (player.hasTreasure() && player.currentPosition(door)){
                System.out.println("Du hast gewonnen!");
                return;
            }

            if (player.currentPosition(door)){
                System.out.println("Du hast verloren");
                return;
            }

            // Kampf Spieler Goblin
            if (goblin.isAlive() && player.currentPosition(goblin.getPosition())){
                if (player.getLevel() > goblin.getLevel()){
                    System.out.println("Es kommt zum Kampf! Der Goblin stirbt!");
                    player.levelUp();
                    goblin.dies();
                } else {
                    System.out.println("Der Goblin gewinnt! Du stirbst!");
                    return;
                }
            }

            if (playerstats){
                System.out.println(player);
                playerstats = false;
            }




            // Spieler bewegen
            switch (new java.util.Scanner(System.in).next()) {
                case "w":
                    if (player.getPosition().y > 1) {
                        player.move("w");
                    } else {
                        wall = true;
                    }
                    break;
                case "a":
                    if (player.getPosition().x > 1) {
                        player.move("a");
                    } else {
                        wall = true;
                    }
                    break;
                case "s":
                    if (player.getPosition().y < 10) {
                        player.move("s");
                    } else {
                        wall = true;
                    }
                    break;

                case "d":
                    if (player.getPosition().x < 20) {
                        player.move("d");
                    } else {
                        wall = true;
                    }
                    break;

                case "t":
                    // Spielerstatus ausgeben
                    playerstats = true;
                    break;

                case "x":
                    return;
            }

            // Goblin bewegt sich auf Spieler zu
            if (goblin.isAlive() && !playerstats) {
                if (player.getPosition().x < goblin.getPosition().x) {
                    goblin.moveLeft();
                } else if (player.getPosition().x > goblin.getPosition().x) {
                    goblin.moveRight();
                }
                if (player.getPosition().y < goblin.getPosition().y) {
                    goblin.moveUp();
                } else if (player.getPosition().y > goblin.getPosition().y) {
                    goblin.moveDown();
                }
            }


        } while(true);






    }

    public static void createPlayer(){
        System.out.println("Bitte geben Sie einen Namen ein:");
        String name = new java.util.Scanner(System.in).next();
        System.out.println("Bitte geben Sie einen Avatar ein:");
        String avatar = new java.util.Scanner(System.in).next();

        player.setName(name);
        player.setAvatar(avatar);

        System.out.println("Vielen Dank! Dein Spieler wurde angelegt!");

    }
}