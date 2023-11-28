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

        /*if (line - 1 >= 0 && oneMatrix[line][column].equals(Tiles.TILE_BOMB.getTileImage())
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
        }*/

        if (oneMatrix[line][column].equals(Tiles.TILE_BOMB.getTileImage())) {
            return; // Se a célula é uma bomba, não é necessário atribuir um número
        }
        int bombsNearby = countBombs(oneMatrix);

        if (bombsNearby > 0) {
            // Array de imagens correspondentes aos números de bombas vizinhas
            String[] numbersImages = {
                    Tiles.TILE_ONE.getTileImage(),
                    Tiles.TILE_TWO.getTileImage(),
                    Tiles.TILE_THREE.getTileImage(),
                    Tiles.TILE_FOUR.getTileImage(),
                    Tiles.TILE_FIVE.getTileImage(),
                    Tiles.TILE_SIX.getTileImage(),
                    Tiles.TILE_SEVEN.getTileImage(),
                    Tiles.TILE_EIGHT.getTileImage()
            };

            // Atribui a imagem correspondente ao número de bombas vizinhas
            if (line - 1 >= 0 && oneMatrix[line][column].equals(Tiles.TILE_BOMB.getTileImage())
                    && oneMatrix[line - 1][column].equals(Tiles.DEFAULT.getTileImage())) {
                oneMatrix[line - 1][column] = numbersImages[countBombs(oneMatrix) - 1];
            }
            if (line + 1 < numRows && oneMatrix[line][column].equals(Tiles.TILE_BOMB.getTileImage())
                    && oneMatrix[line + 1][column].equals(Tiles.DEFAULT.getTileImage())) {
                oneMatrix[line + 1][column] = numbersImages[countBombs(oneMatrix) - 1];
            }
            if (column - 1 >= 0 && oneMatrix[line][column].equals(Tiles.TILE_BOMB.getTileImage())
                    && oneMatrix[line][column - 1].equals(Tiles.DEFAULT.getTileImage())) {
                oneMatrix[line][column - 1] = numbersImages[countBombs(oneMatrix) - 1];
            }
            if (column + 1 < numCols && oneMatrix[line][column].equals(Tiles.TILE_BOMB.getTileImage())
                    && oneMatrix[line][column + 1].equals(Tiles.DEFAULT.getTileImage())) {
                oneMatrix[line][column + 1] = numbersImages[countBombs(oneMatrix) - 1];
            }
        }
    }

    /*int countBombs(String[][] oneMatrix, int line, int column) {
        int counterBombs = 0;
        int numRows = oneMatrix.length;
        int numCols = oneMatrix[0].length;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = line + i;
                int newCol = column + j;

                if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols
                        && !(i == 0 && j == 0)
                        && oneMatrix[newRow][newCol].equals(Tiles.TILE_BOMB.getTileImage())) {
                    counterBombs++;
                }
            }
        }
        return counterBombs;
    }*/

    int countBombs(String[][] oneMatrix) {
        int counterBombs = 0;
        for (int i = 0; i < oneMatrix.length; i++) {
            for (int j = 0; j < oneMatrix.length; j++) {
                if(oneMatrix[i][j].equals(Tiles.TILE_BOMB.getTileImage())){
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