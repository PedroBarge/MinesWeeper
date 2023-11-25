import java.util.Objects;
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

        } while (!isDead);
    }
}
