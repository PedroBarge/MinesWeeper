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
                continue;
            }
            System.out.println("BOOOOOOM!!");
            matrixGridCPU[lineByPlayer][columnByPlayer] = "\t🟥";
            cpuPlays.buildGrid(matrixGridCPU);
            isDead = true;

        } while (!isDead);
    }
}
