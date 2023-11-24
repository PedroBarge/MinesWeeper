import java.util.Objects;
import java.util.Random;

public class CPU extends MakeGrids {
    Random random = new Random();

    @Override
    void makeNewGrid(String[][] matrixGridCPU) {
        super.makeNewGrid(matrixGridCPU);
    }
    void placeBombs(String[][] matrixGridCPU) {
        for (int i = 0; i < matrixGridCPU.length; i++) {
            int line = random.nextInt(matrixGridCPU.length);
            int column = random.nextInt(matrixGridCPU[0].length);

            while (Objects.equals(matrixGridCPU[line][column], "\t💣")) {
                line = random.nextInt(matrixGridCPU.length);
                column = random.nextInt(matrixGridCPU[0].length);
            }
            matrixGridCPU[line][column] = "\t💣";
        }
    }
    @Override
    void buildGrid(String[][] matrixGridCPU) {
        super.buildGrid(matrixGridCPU);
    }
}


