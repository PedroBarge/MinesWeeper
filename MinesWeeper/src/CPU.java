import java.util.Objects;
import java.util.Random;

public class CPU extends MakeGrids {
    Random random = new Random();

    @Override
    void makeNewGrid(String[][] oneMatrix) {
        super.makeNewGrid(oneMatrix);
    }

    int lineAll;
    int columnAll;
    int leghtMatrix;

    void placeBombs(String[][] oneMatrix) {
        leghtMatrix = oneMatrix.length;
        for (int i = 0; i < oneMatrix.length; i++) {
            int line = random.nextInt(oneMatrix.length);
            lineAll = line;
            int column = random.nextInt(oneMatrix[0].length);
            columnAll = column;

            while (Objects.equals(oneMatrix[line][column], "\t💣")) {
                line = random.nextInt(oneMatrix.length);
                column = random.nextInt(oneMatrix[0].length);
            }
            oneMatrix[line][column] = "\t💣";
            //placeNumbers(oneMatrix);
        }
    }

    void placeNumbers(String[][] oneMatrix) {
        if (oneMatrix[lineAll][columnAll].equals("\t💣") && oneMatrix[lineAll - 1][columnAll].equals("\t🟩")) {
            oneMatrix[lineAll - 1][columnAll] = "\t1️⃣";
        }
        if (oneMatrix[lineAll][columnAll].equals("\t💣") && oneMatrix[lineAll + 1][columnAll].equals("\t🟩")) {
            oneMatrix[lineAll + 1][columnAll] = "\t1️⃣";
        }
        if (oneMatrix[lineAll][columnAll].equals("\t💣") && oneMatrix[lineAll][columnAll - 1].equals("\t🟩")) {
            oneMatrix[lineAll][columnAll - 1] = "\t1️⃣";
        }
        if (oneMatrix[lineAll][columnAll].equals("\t💣") && oneMatrix[lineAll][columnAll + 1].equals("\t🟩")) {
            oneMatrix[lineAll][columnAll + 1] = "\t1️⃣";
        }
    }

    @Override
    void buildGrid(String[][] matrixGridCPU) {
        super.buildGrid(matrixGridCPU);
    }
}


