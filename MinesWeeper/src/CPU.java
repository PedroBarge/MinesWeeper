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

            while (oneMatrix[line][column].equals(Tiles.TILE_BOMB.getTileImage())) {
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
            oneMatrix[line - 1][column] = numbers[countSpace(oneMatrix, line, column)-1];
        }
        if (line + 1 < numRows && oneMatrix[line][column].equals("\t💣")
                && oneMatrix[line + 1][column].equals("\t🟩")) {
            oneMatrix[line + 1][column] = numbers[countSpace(oneMatrix, line, column)-1];
        }
        if (column - 1 >= 0 && oneMatrix[line][column].equals("\t💣")
                && oneMatrix[line][column - 1].equals("\t🟩")) {
            oneMatrix[line][column - 1] = numbers[countSpace(oneMatrix, line, column)-1];
        }
        if (column + 1 < numCols && oneMatrix[line][column].equals("\t💣")
                && oneMatrix[line][column + 1].equals("\t🟩")) {
            oneMatrix[line][column + 1] = numbers[countSpace(oneMatrix, line, column)-1];
        }
    }

    int countSpace(String[][] oneMatrix, int line, int column) {
        int counterBombs = 0;
        int numRows = oneMatrix.length;
        int numCols = oneMatrix[0].length;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = line + i;
                int newCol = column + j;
                if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols
                        && oneMatrix[newRow][newCol].equals("\t💣")) {
                    counterBombs++;
                }
            }
        }
        return counterBombs;
    }


    @Override
    void buildGrid(String[][] matrixGridCPU) {
        super.buildGrid(matrixGridCPU);
    }
}