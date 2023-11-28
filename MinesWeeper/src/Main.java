import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String userChoiceMenu;
        do {
            clean();
            System.out.println("\t\tMINESWEEPER");
            showMenu();
            userChoiceMenu = scanner.next();
            switch (userChoiceMenu) {
                case "1":
                    showRules();
                    break;
                case "2":
                    clean();
                    Game game = new Game();
                    game.starGame();
                    break;
                case "0":
                    System.out.println("Closing...");
                    break;
                default:
                    System.out.println("\nPlease insert correct option\n");
                    Thread.sleep(2000);
            }
        } while (!userChoiceMenu.equals("0"));
    }

    static void showMenu() {
        System.out.println("\t\tMENU");
        System.out.println("\t1- Show Rules");
        System.out.println("\t2- Start Game");
        System.out.println("\t0- Exit");
        System.out.print("\t-> ");
    }

    static void showRules() throws InterruptedException {
        clean();
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.println("\t\t\t\t\t\t\t\t\tRULES");
        System.out.println("\t\t\tThis game is based in Minesweeper\n");
        System.out.println("""
                \tWhen the game starts, you will see a exemple map\s
                \tYou need to pick a line's number and press enter to choose a column's number.\s
                \tThe "🟧" it is your play.\s
                \tThe "💣" it is the bomb.\s
                \tThe numbers (ex: "0️⃣") it is the number correspondet of bombs nearby.\s
                \tWhen you lose the game all the bombs will apear and a "🟥" on the bomb you blow up.\s
                
                """);
        System.out.println();
        System.out.println("OBS: The game can be lost in the frist play like the old rules.");
        System.out.println("+------------------------------------------------------------------------------------+");
        Thread.sleep(20000);
        clean();
    }
    static void clean(){
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
    }
}