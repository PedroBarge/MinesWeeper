import java.util.InputMismatchException;
import java.util.Scanner;

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
                    //cpuPlays.buildGrid(matrixGridCPU);
                    firtsPlay = true;
                }
                //——————————————————————————————————————————
                checkRepeatPosition(matrixGridPlayer, lineByPlayer, columnByPlayer);
                //——————————————————————————————————————————

                //Safe play
                if (thisPalceInCPUIsEqualsToDefault(lineByPlayer, columnByPlayer)) {
                    Main.clean();
                    System.out.println("No bomb here");

                    fillSpace(matrixGridPlayer, lineByPlayer, columnByPlayer, Tiles.DEFAULT.getTileImage(), Tiles.TILE_PLAYER_ATACK.getTileImage());

                    player.buildGrid(matrixGridPlayer);

                    matrixGridCPU[lineByPlayer][columnByPlayer].update(Tiles.TILE_PLAYER_ATACK.getIndex(),Tiles.TILE_PLAYER_ATACK.getTileImage());
                    checkWin(matrixGridPlayer, lineByPlayer, columnByPlayer);
                    continue;
                }
                //——————————————————————————————————————————

                //Not so safe play
                for (int i = 0; i < numbersImages.length; i++) {
                    if (matrixGridCPU[lineByPlayer][columnByPlayer].getImageEmoji().equals(numbersImages[i])) {
                        Main.clean();
                        System.out.println("Not a bomb but...");
                        updatePlayer(lineByPlayer, columnByPlayer);
                        player.buildGrid(matrixGridPlayer);
                        updateCPU(lineByPlayer, columnByPlayer);
                        checkWin(matrixGridPlayer, lineByPlayer, columnByPlayer);
                    }
                }
                //——————————————————————————————————————————

                //O jogo acabou Jogador perdeu
                if (matrixGridCPU[lineByPlayer][columnByPlayer].getImageEmoji().equals(Tiles.TILE_BOMB.getTileImage())) {
                    Main.clean();
                    System.out.println("BOOOOOOM!!");
                    thisPlaceInCpuBombExplode(lineByPlayer, columnByPlayer);


                for (int i = 0; i < matrixGridCPU.length; i++) {
                        for (int j = 0; j < matrixGridCPU.length; j++) {
                            if (       !matrixGridCPU[i][j].getImageEmoji().equals(Tiles.TILE_BOMB.getTileImage())
                                    && !matrixGridCPU[i][j].getImageEmoji().equals(Tiles.TILE_PLAYER_ATACK.getTileImage())
                                    && !matrixGridCPU[i][j].getImageEmoji().equals(Tiles.TILE_BOMB_EXPLODE.getTileImage())
                            ) {
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
                Main.clean();
                System.out.println(e.getMessage());
                System.out.println("Please, insert only numbers\n");
            } catch (ArrayIndexOutOfBoundsException e) {
                scanner.nextLine();
                Main.clean();
                System.out.println(e.getMessage());
                System.out.println("You need to choose a number between 0-9\n");
            } catch (Exception e) {
                Main.clean();
                System.out.println(e.getMessage());
                scanner.nextLine();
                System.out.println("Another error");
            }
            //——————————————————————————————————————————
        } while (!isAlive);
    }//——————————————————————————————————————————

    private void thisPlaceInCpuBombExplode(int lineByPlayer, int columnByPlayer) {
        matrixGridCPU[lineByPlayer][columnByPlayer].update(Tiles.TILE_BOMB_EXPLODE.getIndex(), Tiles.TILE_BOMB_EXPLODE.getTileImage());
    }

    private void updateCPU(int lineByPlayer, int columnByPlayer) {
        matrixGridCPU[lineByPlayer][columnByPlayer].update(Tiles.TILE_PLAYER_NUMBER.getIndex(), Tiles.TILE_PLAYER_NUMBER.getTileImage());
    }

    private void updatePlayer(int lineByPlayer, int columnByPlayer) {
        matrixGridPlayer[lineByPlayer][columnByPlayer].update(matrixGridCPU[lineByPlayer][columnByPlayer].getValue(),
                matrixGridCPU[lineByPlayer][columnByPlayer].getImageEmoji());
    }

    private boolean thisPalceInCPUIsEqualsToDefault(int lineByPlayer, int columnByPlayer) {
        return matrixGridCPU[lineByPlayer][columnByPlayer].getImageEmoji().equals(Tiles.DEFAULT.getTileImage());
    }

    //——————————————————————————————————————————

    public void fillSpace(Position[][] oneMatrix, int oneLine, int oneColumn, String oldColor, String newColor) {

        if (oneLine < 0 || oneLine >= oneMatrix.length || oneColumn < 0 || oneColumn >= oneMatrix.length) {
            return;
        }
        if (!oneMatrix[oneLine][oneColumn].getImageEmoji().equals(oldColor)) {
            return;
        }

        if (matrixGridCPU[oneLine][oneColumn].getValue() > 0) {
            oneMatrix[oneLine][oneColumn].update(matrixGridCPU[oneLine][oneColumn].getValue(), matrixGridCPU[oneLine][oneColumn].getImageEmoji());
            updateCPU(oneLine, oneColumn);
            return;
        }
        oneMatrix[oneLine][oneColumn].update(Tiles.TILE_PLAYER_ATACK.getIndex(), Tiles.TILE_PLAYER_ATACK.getTileImage());
        matrixGridCPU[oneLine][oneColumn].update(Tiles.TILE_PLAYER_ATACK.getIndex(),Tiles.TILE_PLAYER_ATACK.getTileImage());

        fillSpace(oneMatrix, oneLine + 1, oneColumn, oldColor, newColor);
        fillSpace(oneMatrix, oneLine - 1, oneColumn, oldColor, newColor);
        fillSpace(oneMatrix, oneLine, oneColumn + 1, oldColor, newColor);
        fillSpace(oneMatrix, oneLine, oneColumn - 1, oldColor, newColor);

    }

    public void checkWin(Position[][] matrixGridPlayer, int oneLine, int oneColumn) throws InterruptedException {
        int counter = 0;
        for (int i = 0; i < matrixGridPlayer.length; i++) {
            for (int j = 0; j < matrixGridPlayer.length; j++) {
                if (matrixGridPlayer[i][j].getImageEmoji().equals(Tiles.TILE_PLAYER_ATACK.getTileImage()) || matrixGridPlayer[i][j].getValue() > 0) {
                    counter++;
                }

            }
        }

        System.out.println();
        System.out.println("Number of know places " + counter);
        if (counter >= 85) {
            Main.clean();
            System.out.println("YOU WON");
            player.buildGrid(matrixGridPlayer);
            Thread.sleep(5000);
            isAlive = true;
        }
    }

    public void checkRepeatPosition(Position[][] matrixGridPlayer, int lineByPlayer, int columnByPlayer) throws InterruptedException {

        if (matrixGridPlayer[lineByPlayer][columnByPlayer].getImageEmoji().equals(Tiles.TILE_PLAYER_ATACK.getTileImage())) {
            System.out.println("You have already play in this position");
        }
        if (matrixGridPlayer[lineByPlayer][columnByPlayer].getImageEmoji().equals(Tiles.TILE_ONE.getTileImage())) {
            System.out.println("You have already play in this position");
        }

    }

}