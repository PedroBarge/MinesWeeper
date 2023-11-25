import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);

    public void starGame() {
        String[][] matrixGridCPU = new String[10][10];
        String[][] matrixGridPlayer = new String[10][10];
        CPU cpuPlays = new CPU();
        cpuPlays.makeNewGrid(matrixGridCPU);
        Player player = new Player();
        int maxPlayes = 90;
        //——————————————————————————————————————————
        boolean firtsPlay = false;
        boolean isDead = false;
        player.makeNewGrid(matrixGridPlayer);
        player.buildGrid(matrixGridPlayer);
        do {
            try {
                System.out.println();
                System.out.print("Insert line: ");
                int lineByPlayer = scanner.nextInt();
                System.out.print("Insert column: ");
                int columnByPlayer = scanner.nextInt();
                if (!firtsPlay) {
                    cpuPlays.placeBombs(matrixGridCPU);
                    cpuPlays.buildGrid(matrixGridCPU);
                    firtsPlay = true;
                }
                //——————————————————————————————————————————
                if (matrixGridPlayer[lineByPlayer][columnByPlayer].equals("\t🟧")) {
                    System.out.println("You have already play in this position");
                    continue;
                }

                if (matrixGridCPU[lineByPlayer][columnByPlayer].equals("\t🟩")) {
                    System.out.println("No bomb");
                    matrixGridCPU[lineByPlayer][columnByPlayer] = "\t🟧";
                    matrixGridPlayer[lineByPlayer][columnByPlayer] = "\t🟧";
                    fillSpace(matrixGridPlayer, lineByPlayer, columnByPlayer, "\t🟩", "\t🟧");
                    player.buildGrid(matrixGridPlayer);

                    maxPlayes--;
                    if (maxPlayes == 0) {
                        System.out.println("YOU WON");
                        isDead = true;
                    }
                    continue;
                }

                if (matrixGridCPU[lineByPlayer][columnByPlayer].equals("\t1️⃣")) {
                    System.out.println("No bomb, but be carefull");
                    matrixGridCPU[lineByPlayer][columnByPlayer] = "\t1️⃣";
                    matrixGridPlayer[lineByPlayer][columnByPlayer] = "\t1️⃣";
                    player.buildGrid(matrixGridPlayer);
                    maxPlayes--;
                    if (maxPlayes == 0) {
                        System.out.println("YOU WON");
                        isDead = true;
                    }
                    continue;
                }

                if (matrixGridCPU[lineByPlayer][columnByPlayer].equals("\t💣")) {
                    System.out.println("BOOOOOOM!!");
                    matrixGridCPU[lineByPlayer][columnByPlayer] = "\t🟥";
                    for (int i = 0; i < matrixGridCPU.length; i++) {
                        for (int j = 0; j < matrixGridCPU.length; j++) {
                            if (matrixGridCPU[i][j].equals("\t1️⃣")) {
                                matrixGridCPU[i][j] = "\t🟩";
                            }
                        }
                    }
                    cpuPlays.buildGrid(matrixGridCPU);
                    isDead = true;
                }
            } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
                scanner.nextLine();
                System.out.println("Please, only insert the right numbers\n");
            }
        } while (!isDead);

    }

    public void fillSpace(String[][] oneMatrix, int oneLine, int oneColumn, String oldColor, String newColor) {
        if (!oneMatrix[oneLine][oneColumn].equals(oldColor) || oneMatrix[oneLine][oneColumn].equals(newColor)
                || oneLine < 0 || oneColumn < 0
                || oneLine >= oneMatrix.length || oneColumn >= oneMatrix[0].length) {
            return;
        }
        oneMatrix[oneLine][oneColumn] = newColor;
        fillSpace(oneMatrix, oneLine - 1, oneColumn, oldColor, newColor);
        fillSpace(oneMatrix, oneLine + 1, oneColumn, oldColor, newColor);
        fillSpace(oneMatrix, oneLine, oneColumn - 1, oldColor, newColor);
        fillSpace(oneMatrix, oneLine, oneColumn + 1, oldColor, newColor);
    }
}
