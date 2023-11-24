import java.util.Objects;
import java.util.Random;

public class placeBombsOnGrid extends Grid {

    public void plceBombs() {
        Random random = new Random();
        makeNewGrid();
        for (int i = 0; i < matrixGrid.length; i++) {
            int line = random.nextInt(matrixGrid.length);
            int column = random.nextInt(matrixGrid[0].length);

            while (Objects.equals(matrixGrid[line][column], " O ")) {
                line = random.nextInt(matrixGrid.length);
                column = random.nextInt(matrixGrid[0].length);
            }
            matrixGrid[line][column] = "\t🟥";
        }
        buildGrid();
    }
}
