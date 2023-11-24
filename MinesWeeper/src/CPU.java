import java.util.Objects;
import java.util.Random;

public class CPU extends MakeGrids {
    Random random = new Random();


    @Override
    void makeNewGrid(String[][] oneMatrix) {
        super.makeNewGrid(oneMatrix);
    }

    int leghtMatrix;

    void placeBombs(String[][] oneMatrix) {
        leghtMatrix = oneMatrix.length;
        for (int i = 0; i < oneMatrix.length; i++) {
            int line = random.nextInt(oneMatrix.length);
            int column = random.nextInt(oneMatrix[0].length);

            while (Objects.equals(oneMatrix[line][column], "\t💣")) {
                line = random.nextInt(oneMatrix.length);
                column = random.nextInt(oneMatrix[0].length);
            }
            oneMatrix[line][column] = "\t💣";
            placeNumbers(oneMatrix, line, column);
        }
    }

    void placeNumbers(String[][] oneMatrix, int line, int column) {
        int numRows = oneMatrix.length;
        int numCols = oneMatrix[0].length;

        if (line - 1 >= 0 && oneMatrix[line][column].equals("\t💣")
                && oneMatrix[line - 1][column].equals("\t🟩")) {

            oneMatrix[line - 1][column] = "\t1️⃣";

        }
        if (line + 1 < numRows && oneMatrix[line][column].equals("\t💣")
                && oneMatrix[line + 1][column].equals("\t🟩")) {

            oneMatrix[line + 1][column] = "\t1️⃣";
        }
        if (column - 1 >= 0 && oneMatrix[line][column].equals("\t💣")
                && oneMatrix[line][column - 1].equals("\t🟩")) {

            oneMatrix[line][column - 1] = "\t1️⃣";
        }
        if (column + 1 < numCols && oneMatrix[line][column].equals("\t💣")
                && oneMatrix[line][column + 1].equals("\t🟩")) {

            oneMatrix[line][column + 1] = "\t1️⃣";
        }
    }

    @Override
    void buildGrid(String[][] matrixGridCPU) {
        super.buildGrid(matrixGridCPU);
    }
}


