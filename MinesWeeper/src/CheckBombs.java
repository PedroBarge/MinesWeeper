import java.util.Objects;
import java.util.Scanner;

public class CheckBombs {
    static Scanner scanner = new Scanner(System.in);

    public static void playerTryToFindBoms() {
        System.out.println();
        for (int i = 0; i < placeBombsOnGrid.matrixGrid.length; i++) {
            for (int j = 0; j < placeBombsOnGrid.matrixGrid.length; j++) {
                System.out.print(placeBombsOnGrid.matrixGrid[i][j]);
            }
            System.out.println();
        }

        System.out.print("Insert line: ");
        int lineByPlayer = scanner.nextInt();
        System.out.print("Insert column: ");
        int columnByPlayer = scanner.nextInt();

        if (Objects.equals(placeBombsOnGrid.matrixGrid[lineByPlayer][columnByPlayer], "\t🟩")) {
            System.out.println("No bomb");
        } else {
            System.out.println("BOOOOOOM!!");
        }
    }
}
