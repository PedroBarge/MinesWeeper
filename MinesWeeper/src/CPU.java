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

        // TODO: 27/11/2023 Verificar os numeros e mudar conforme o numero de bombas perto.

        int numRows = oneMatrix.length;
        int numCols = oneMatrix[0].length;

        if (line - 1 >= 0 && oneMatrix[line][column].equals(Tiles.TILE_BOMB.getTileImage())
                && oneMatrix[line - 1][column].equals(Tiles.DEFAULT.getTileImage())) {
            oneMatrix[line - 1][column] = Tiles.TILE_ONE.getTileImage();
        }
        if (line + 1 < numRows && oneMatrix[line][column].equals(Tiles.TILE_BOMB.getTileImage())
                && oneMatrix[line + 1][column].equals(Tiles.DEFAULT.getTileImage())) {
            oneMatrix[line + 1][column] = Tiles.TILE_ONE.getTileImage();
        }
        if (column - 1 >= 0 && oneMatrix[line][column].equals(Tiles.TILE_BOMB.getTileImage())
                && oneMatrix[line][column - 1].equals(Tiles.DEFAULT.getTileImage())) {
            oneMatrix[line][column - 1] = Tiles.TILE_ONE.getTileImage();
        }
        if (column + 1 < numCols && oneMatrix[line][column].equals(Tiles.TILE_BOMB.getTileImage())
                && oneMatrix[line][column + 1].equals(Tiles.DEFAULT.getTileImage())) {
            oneMatrix[line][column + 1] = Tiles.TILE_ONE.getTileImage();
        }
        
    }


    @Override
    void buildGrid(String[][] matrixGridCPU) {
        super.buildGrid(matrixGridCPU);
    }
}