import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    CPU cpuPlays = new CPU();

    public void starGame() {
        String[][] matrixGridCPU = new String[10][10];
        String[][] matrixGridPlayer = new String[10][10];
        //——————————————————————————————————————————
        cpuPlays.makeNewGrid(matrixGridCPU);
        Player player = new Player();
        //——————————————————————————————————————————
        boolean firtsPlay = false;
        boolean isAlive = false;
        //——————————————————————————————————————————
        player.makeNewGrid(matrixGridPlayer);
        player.buildGrid(matrixGridPlayer);
        int maxPlayes = 0;

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
                //——————————————————————————————————————————
                if (matrixGridCPU[lineByPlayer][columnByPlayer].equals("\t🟩")) {
                    clean();
                    System.out.println("No bomb");
                    for (int i = 0; i < cpuPlays.numbers.length; i++) {
                        if (matrixGridCPU[lineByPlayer][columnByPlayer].equals(cpuPlays.numbers[i])) {
                            matrixGridPlayer[lineByPlayer][columnByPlayer] = cpuPlays.numbers[i];
                        }
                    }

                    fillSpace(matrixGridCPU, lineByPlayer, columnByPlayer, "\t🟩", "\t🟧");

                    for (int i = 0; i < matrixGridPlayer.length; i++) {
                        for (int j = 0; j < matrixGridPlayer.length; j++) {
                            if (matrixGridCPU[i][j].equals("\t🟧")) {
                                matrixGridPlayer[i][j] = matrixGridCPU[i][j];
                            }
                        }
                    }
                    player.buildGrid(matrixGridPlayer);
                    continue;
                }
                //——————————————————————————————————————————
                for (int i = 0; i < cpuPlays.numbers.length; i++) {
                    if (matrixGridCPU[lineByPlayer][columnByPlayer].equals(cpuPlays.numbers[i])) {
                        clean();
                        System.out.println("No bomb, but be carefull");
                        matrixGridPlayer[lineByPlayer][columnByPlayer] = cpuPlays.numbers[i];
                        player.buildGrid(matrixGridPlayer);
                        matrixGridCPU[lineByPlayer][columnByPlayer] = "\t🟨";
                    }
                }
                //——————————————————————————————————————————
                for (int i = 0; i < matrixGridPlayer.length; i++) {
                    for (int j = 0; j < matrixGridPlayer.length; j++) {
                        if (matrixGridPlayer[i][j].equals("\t🟧")) {
                            maxPlayes++;
                            //System.out.println(maxPlayes);
                        }
                    }
                }
                if (maxPlayes == 90) {
                    clean();
                    System.out.println("YOU WON");
                    for (int i = 0; i < matrixGridCPU.length; i++) {
                        for (int j = 0; j < matrixGridCPU.length; j++) {
                            if (matrixGridCPU[i][j].equals("\t🟨")) {
                                matrixGridPlayer[i][j] = "\t🟨";
                            }
                        }
                    }
                    player.buildGrid(matrixGridPlayer);
                    isAlive = true;
                }
                //——————————————————————————————————————————
                if (matrixGridCPU[lineByPlayer][columnByPlayer].equals("\t💣")) {
                    clean();
                    System.out.println("BOOOOOOM!!");
                    matrixGridCPU[lineByPlayer][columnByPlayer] = "\t🟥";
                    for (int i = 0; i < matrixGridCPU.length; i++) {
                        for (int j = 0; j < matrixGridCPU.length; j++) {
                            if (matrixGridCPU[i][j].equals("\t🟨")) {
                                matrixGridCPU[i][j] = "\t🟨";
                            }
                            if (!matrixGridCPU[i][j].equals("\t💣") && !matrixGridCPU[i][j].equals("\t🟧")
                                    && !matrixGridCPU[i][j].equals("\t🟥") && !matrixGridCPU[i][j].equals("\t🟨")) {
                                matrixGridCPU[i][j] = "\t🟩";
                            }
                        }
                    }
                    cpuPlays.buildGrid(matrixGridCPU);
                    Thread.sleep(5000);
                    isAlive = true;
                }
            } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
                scanner.nextLine();
                //System.out.println(e);
                System.out.println("Please, only insert the right numbers\n");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //——————————————————————————————————————————
        } while (!isAlive);
    }

    //——————————————————————————————————————————
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
