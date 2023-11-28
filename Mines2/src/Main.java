import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Minesweeper");
        makeNewGrid();
        buildGrid();
        playerTryToFindBoms();
    }

    //——————————————————————————————————————————
    static String[][] matrixGrid = new String[10][10];
    static String[][] matrixGridPlayer = new String[10][10];

    private static void makeNewGrid() {
        for (int i = 0; i < matrixGrid.length; i++) {
            for (int j = 0; j < matrixGrid.length; j++) {
                matrixGrid[i][j] = "\t🟩";
            }
        }
    }

    private static void buildGrid() {
        for (int i = 0; i < matrixGrid.length; i++) {
            if (i == 0) {
                System.out.print("\t|" + i + "|");
            } else System.out.print(" |" + i + "| ");
        }
        System.out.println();
        System.out.println("\t——————————————————————————————————————————");
        for (int i = 0; i < matrixGrid.length; i++) {
            System.out.print("\t" + i + "| ");
            for (int j = 0; j < matrixGrid.length; j++) {
                System.out.print(matrixGrid[i][j]);
            }
            System.out.println();
        }
    }

    private static void makeNewGridPlayer() {
        for (int i = 0; i < matrixGridPlayer.length; i++) {
            for (int j = 0; j < matrixGridPlayer.length; j++) {
                matrixGridPlayer[i][j] = "\t🟩";
            }
        }
    }

    private static void buildGridPlayer() {
        for (int i = 0; i < matrixGridPlayer.length; i++) {
            if (i == 0) {
                System.out.print("\t|" + i + "|");
            } else System.out.print(" |" + i + "| ");
        }
        System.out.println();
        System.out.println("\t——————————————————————————————————————————");
        for (int i = 0; i < matrixGridPlayer.length; i++) {
            System.out.print("\t" + i + "| ");
            for (int j = 0; j < matrixGridPlayer.length; j++) {
                System.out.print(matrixGridPlayer[i][j]);
            }
            System.out.println();
        }
    }

    //——————————————————————————————————————————
    public static void placeBombs() {
        Random random = new Random();
        makeNewGrid();
        for (int i = 0; i < matrixGrid.length; i++) {
            int line = random.nextInt(matrixGrid.length);
            int column = random.nextInt(matrixGrid[0].length);

            while (Objects.equals(matrixGrid[line][column], "\t💣")) {
                line = random.nextInt(matrixGrid.length);
                column = random.nextInt(matrixGrid[0].length);
            }
            matrixGrid[line][column] = "\t💣";
        }
        //buildGrid();
    }

    //——————————————————————————————————————————
    static Scanner scanner = new Scanner(System.in);

    public static void playerTryToFindBoms() {
        int firtsPlay = 0;
        boolean isDead = false;
        makeNewGridPlayer();
        do {
            System.out.println();
            System.out.print("Insert line: ");
            int lineByPlayer = scanner.nextInt();
            System.out.print("Insert column: ");
            int columnByPlayer = scanner.nextInt();
            if (firtsPlay == 0) {
                placeBombs();
                firtsPlay++;
            }
            if (Objects.equals(matrixGrid[lineByPlayer][columnByPlayer], "\t🟩")) {
                System.out.println("No bomb");
                matrixGridPlayer[lineByPlayer][columnByPlayer] = "\t🟧";
                matrixGrid[lineByPlayer][columnByPlayer] = "\t🟧";
                buildGridPlayer();
            } else {
                System.out.println("BOOOOOOM!!");
                buildGrid();
                isDead = true;
            }
        } while (!isDead);
    }
}