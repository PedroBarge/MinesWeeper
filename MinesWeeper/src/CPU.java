import java.util.Random;

public class CPU extends MakeGrids {
    Random random = new Random();


    @Override
    void makeNewGrid(Position[][] oneMatrix) {
        super.makeNewGrid(oneMatrix);
    }


    void placeBombs(Position[][] oneMatrix) {
        int line;
        int column;
        for (int i = 0; i < 15; i++) {
            line = random.nextInt(oneMatrix.length);
            column = random.nextInt(oneMatrix[0].length);
            while (oneMatrix[line][column].getImageEmoji().equals(Tiles.TILE_BOMB.getTileImage())) {
                line = random.nextInt(oneMatrix.length);
                column = random.nextInt(oneMatrix[0].length);
            }
            oneMatrix[line][column] = new Position(Tiles.TILE_BOMB.getIndex(), Tiles.TILE_BOMB.getTileImage());
            placeNumbers(oneMatrix, line, column);
        }
    }

    void placeNumbers(Position[][] oneMatrix, int line, int column) {

        chekNumbers(oneMatrix, line - 1, column);
        chekNumbers(oneMatrix, line + 1, column);
        chekNumbers(oneMatrix, line, column - 1);
        chekNumbers(oneMatrix, line, column + 1);

        chekNumbers(oneMatrix, line + 1, column - 1);
        chekNumbers(oneMatrix, line + 1, column + 1);
        chekNumbers(oneMatrix, line - 1, column - 1);
        chekNumbers(oneMatrix, line - 1, column + 1);

    }

    void chekNumbers(Position[][] oneMatrix, int line, int column) {
        String[] numbersImages = {
                Tiles.DEFAULT.getTileImage(),
                Tiles.TILE_ONE.getTileImage(),
                Tiles.TILE_TWO.getTileImage(),
                Tiles.TILE_THREE.getTileImage(),
                Tiles.TILE_FOUR.getTileImage(),
                Tiles.TILE_FIVE.getTileImage(),
                Tiles.TILE_SIX.getTileImage(),
                Tiles.TILE_SEVEN.getTileImage(),
                Tiles.TILE_EIGHT.getTileImage()
        };

        if (line < 0 || line >= oneMatrix.length || column < 0 || column >= oneMatrix[0].length) {
            return;
        }
        if (!oneMatrix[line][column].getImageEmoji().equals(Tiles.TILE_BOMB.getTileImage())) {
            oneMatrix[line][column].update(oneMatrix[line][column].getValue() + 1, numbersImages[oneMatrix[line][column].getValue() + 1]);
        }

    }

    @Override
    void buildGrid(Position[][] matrixGridCPU) {
        super.buildGrid(matrixGridCPU);
    }
}