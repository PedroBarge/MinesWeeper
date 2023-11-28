import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;

public class Game {
    Scanner scanner = new Scanner(System.in);
    //——————————————————————————————————————————
    CPU cpuPlays = new CPU();
    Player player = new Player();
    //——————————————————————————————————————————
    boolean firtsPlay = false;
    boolean isAlive = false;
    //——————————————————————————————————————————
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

    //——————————————————————————————————————————
    Position[][] matrixGridCPU = new Position[10][10];
    Position[][] matrixGridPlayer = new Position[10][10];

    public void starGame() {
        //——————————————————————————————————————————
        cpuPlays.makeNewGrid(matrixGridCPU);
        //——————————————————————————————————————————
        player.makeNewGrid(matrixGridPlayer);
        player.buildGrid(matrixGridPlayer);

        do {
            try {
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
                checkRepeatPosition(matrixGridPlayer, lineByPlayer, columnByPlayer);
                //——————————————————————————————————————————
                if (matrixGridCPU[lineByPlayer][columnByPlayer].getImageEmoji().equals(Tiles.DEFAULT.getTileImage())) {
                    clean();
                    System.out.println("No bomb here");

                    fillSpace(matrixGridPlayer, lineByPlayer, columnByPlayer, Tiles.DEFAULT.getTileImage(), Tiles.TILE_PLAYER_ATACK.getTileImage());

                    for (int i = 0; i < matrixGridPlayer.length; i++) {
                        for (int j = 0; j < matrixGridPlayer.length; j++) {
                            if (matrixGridCPU[i][j].getImageEmoji().equals(Tiles.TILE_PLAYER_ATACK.getTileImage())) {
                                matrixGridPlayer[i][j] = matrixGridCPU[i][j];
                            }
                        }
                    }
                    player.buildGrid(matrixGridPlayer);
                    checkWin(matrixGridPlayer, lineByPlayer, columnByPlayer);
                    continue;
                }
                //——————————————————————————————————————————

                for (int i = 0; i < numbersImages.length; i++) {
                    if (matrixGridCPU[lineByPlayer][columnByPlayer].getImageEmoji().equals(numbersImages[i])) {
                        clean();
                        System.out.println("Not a bomb but...");
                        matrixGridPlayer[lineByPlayer][columnByPlayer].update(matrixGridCPU[lineByPlayer][columnByPlayer].getValue(), matrixGridCPU[lineByPlayer][columnByPlayer].getImageEmoji());
                        player.buildGrid(matrixGridPlayer);
                        matrixGridCPU[lineByPlayer][columnByPlayer].update(Tiles.TILE_PLAYER_NUMBER.getIndex(),Tiles.TILE_PLAYER_NUMBER.getTileImage());
                        checkWin(matrixGridPlayer, lineByPlayer, columnByPlayer);
                    }
                }
                //——————————————————————————————————————————

                //O jogo acabou Jogador perdeu
                if (matrixGridCPU[lineByPlayer][columnByPlayer].getImageEmoji().equals(Tiles.TILE_BOMB.getTileImage())) {
                    clean();
                    System.out.println("BOOOOOOM!!");
                    matrixGridCPU[lineByPlayer][columnByPlayer].update(Tiles.TILE_BOMB_EXPLODE.getIndex(), Tiles.TILE_BOMB_EXPLODE.getTileImage());

                    for (int i = 0; i < matrixGridCPU.length; i++) {
                        for (int j = 0; j < matrixGridCPU.length; j++) {
                            if (!matrixGridCPU[i][j].getImageEmoji().equals(Tiles.TILE_BOMB.getTileImage())
                                    && !matrixGridCPU[i][j].getImageEmoji().equals(Tiles.TILE_PLAYER_ATACK.getTileImage())
                                    && !matrixGridCPU[i][j].getImageEmoji().equals(Tiles.TILE_BOMB_EXPLODE.getTileImage())) {
                                matrixGridCPU[i][j].update(Tiles.DEFAULT.getIndex(), Tiles.DEFAULT.getTileImage());
                            }
                        }
                    }

                    cpuPlays.buildGrid(matrixGridCPU);
                    Thread.sleep(5000);
                    isAlive = true;
                }

            } catch (InputMismatchException | InterruptedException e) {
                scanner.nextLine();
                System.out.println(e.getMessage());
                System.out.println("Please, insert only numbers\n");
            } catch (ArrayIndexOutOfBoundsException e) {
                scanner.nextLine();
                System.out.println(e.getMessage());
                System.out.println("You need to choose a number between 0-9\n");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
                System.out.println("Another error");
            }
            //——————————————————————————————————————————
        } while (!isAlive);
    }

    //——————————————————————————————————————————
    void clean() {
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
    }

    public void fillSpace(Position[][] oneMatrix, int oneLine, int oneColumn, String oldColor, String newColor) {

        if (oneLine < 0 || oneLine >= oneMatrix.length || oneColumn < 0 || oneColumn >= oneMatrix.length) {
            return;
        }
        if (!oneMatrix[oneLine][oneColumn].getImageEmoji().equals(oldColor)) {
            return;
        }

        if (matrixGridCPU[oneLine][oneColumn].getValue() > 0) {
            oneMatrix[oneLine][oneColumn].update(matrixGridCPU[oneLine][oneColumn].getValue(), matrixGridCPU[oneLine][oneColumn].getImageEmoji());
            matrixGridCPU[oneLine][oneColumn].update(Tiles.TILE_PLAYER_NUMBER.getIndex(),Tiles.TILE_PLAYER_NUMBER.getTileImage());
            return;
        }
        oneMatrix[oneLine][oneColumn].update(Tiles.TILE_PLAYER_ATACK.getIndex(), Tiles.TILE_PLAYER_ATACK.getTileImage());

        fillSpace(oneMatrix, oneLine + 1, oneColumn, oldColor, newColor);
        fillSpace(oneMatrix, oneLine - 1, oneColumn, oldColor, newColor);
        fillSpace(oneMatrix, oneLine, oneColumn + 1, oldColor, newColor);
        fillSpace(oneMatrix, oneLine, oneColumn - 1, oldColor, newColor);

    }

    public void checkWin(Position[][] matrixGridPlayer, int oneLine, int oneColumn) throws InterruptedException {
        int counter = 0;
        for (int i = 0; i < matrixGridPlayer.length; i++) {
            for (int j = 0; j < matrixGridPlayer.length; j++) {
                if (matrixGridPlayer[i][j].getImageEmoji().equals(Tiles.TILE_PLAYER_ATACK.getTileImage())) {
                    counter++;
                }
            }
        }
        for (int i = 0; i < numbersImages.length; i++) {
            if (matrixGridPlayer[oneLine][oneColumn].getImageEmoji().equals(numbersImages[i])) {
                counter++;
            }
        }
        System.out.println();
        System.out.println("Number of know places " + counter);
        if (counter >= 90) {
            clean();
            System.out.println("YOU WON");
            player.buildGrid(matrixGridPlayer);
            Thread.sleep(5000);
            isAlive = true;
        }
    }

    public void checkRepeatPosition(Position[][] matrixGridPlayer, int lineByPlayer, int columnByPlayer) {

        if (matrixGridPlayer[lineByPlayer][columnByPlayer].getImageEmoji().equals(Tiles.TILE_PLAYER_ATACK.getTileImage())) {
            System.out.println("You have already play in this position");
        }
        if (matrixGridPlayer[lineByPlayer][columnByPlayer].getImageEmoji().equals(Tiles.TILE_ONE.getTileImage())) {
            System.out.println("You have already play in this position");
        }
    }

}
