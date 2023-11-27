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
                    clean();
                    System.out.println("No bomb");
                    fillSpace(matrixGridCPU, lineByPlayer, columnByPlayer, "\t🟩", "\t🟧");

                    for (int i = 0; i < matrixGridPlayer.length; i++) {
                        for (int j = 0; j < matrixGridPlayer.length; j++) {
                            if (matrixGridCPU[i][j].equals("\t🟧")) {
                                matrixGridPlayer[i][j]= matrixGridCPU[i][j];
                            }
                        }
                    }
                    player.buildGrid(matrixGridPlayer);

                    maxPlayes--;
                    if (maxPlayes == 0) {
                        clean();
                        System.out.println("YOU WON");
                        player.buildGrid(matrixGridPlayer);
                        isDead = true;
                    }
                    continue;
                }

                for (int i = 0; i < cpuPlays.numbers.length - 1; i++) {
                    if (matrixGridCPU[lineByPlayer][columnByPlayer].equals(cpuPlays.numbers[i])) {
                        clean();
                        System.out.println("No bomb, but be carefull");
                        matrixGridCPU[lineByPlayer][columnByPlayer] = "\t🟧";
                        matrixGridPlayer[lineByPlayer][columnByPlayer] = cpuPlays.numbers[i];
                        player.buildGrid(matrixGridPlayer);
                        maxPlayes--;
                        if (maxPlayes == 0) {
                            clean();
                            System.out.println("YOU WON");
                            player.buildGrid(matrixGridPlayer);
                            isDead = true;
                        }
                    }
                }

                if (matrixGridCPU[lineByPlayer][columnByPlayer].equals("\t💣")) {
                    clean();
                    System.out.println("BOOOOOOM!!");
                    matrixGridCPU[lineByPlayer][columnByPlayer] = "\t🟥";
                    for (int i = 0; i < matrixGridCPU.length; i++) {
                        for (int j = 0; j < matrixGridCPU.length; j++) {
                            if (!matrixGridCPU[i][j].equals("\t💣") && !matrixGridCPU[i][j].equals("\t🟧")
                                    && !matrixGridCPU[i][j].equals("\t🟥")) {
                                matrixGridCPU[i][j] = "\t🟩";
                            }
                        }
                    }
                    cpuPlays.buildGrid(matrixGridCPU);
                    isDead = true;
                }
                //ArrayIndexOutOfBoundsException
            } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
                scanner.nextLine();
                System.out.println(e);
                System.out.println("Please, only insert the right numbers\n");
            }
        } while (!isDead);

    }

    void clean() {
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
    }

    public void fillSpace(String[][] oneMatrix, int oneLine, int oneColumn, String oldColor, String newColor) {

        if (oneLine < 0 || oneLine >= oneMatrix.length || oneColumn < 0 || oneColumn >= oneMatrix.length) {
            return;
        }
        if (!oneMatrix[oneLine][oneColumn].equals(oldColor)) {
            return;
        }
        oneMatrix[oneLine][oneColumn] = newColor;

        fillSpace(oneMatrix, oneLine + 1, oneColumn, oldColor, newColor);
        fillSpace(oneMatrix, oneLine - 1, oneColumn, oldColor, newColor);
        fillSpace(oneMatrix, oneLine, oneColumn + 1, oldColor, newColor);
        fillSpace(oneMatrix, oneLine, oneColumn - 1, oldColor, newColor);

    }
}
