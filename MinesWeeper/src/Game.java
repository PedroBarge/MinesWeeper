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
    public void starGame() {
        String[][] matrixGridCPU = new String[10][10];
        String[][] matrixGridPlayer = new String[10][10];
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

                /*if (matrixGridPlayer[lineByPlayer][columnByPlayer].equals(Tiles.TILE_PLAYER_ATACK.getTileImage())) {
                    System.out.println("You have already play in this position");
                    continue;
                }
                if (matrixGridPlayer[lineByPlayer][columnByPlayer].equals(Tiles.TILE_ONE.getTileImage())) {
                    System.out.println("You have already play in this position");
                    continue;
                }*/
                checkRepeatPosition(matrixGridPlayer, lineByPlayer, columnByPlayer);
                //——————————————————————————————————————————
                if (matrixGridCPU[lineByPlayer][columnByPlayer].equals(Tiles.DEFAULT.getTileImage())) {
                    clean();
                    System.out.println("No bomb here");
                    fillSpace(matrixGridCPU, lineByPlayer, columnByPlayer, Tiles.DEFAULT.getTileImage(), Tiles.TILE_PLAYER_ATACK.getTileImage());

                    for (int i = 0; i < matrixGridPlayer.length; i++) {
                        for (int j = 0; j < matrixGridPlayer.length; j++) {
                            if (matrixGridCPU[i][j].equals(Tiles.TILE_PLAYER_ATACK.getTileImage())) {
                                matrixGridPlayer[i][j] = matrixGridCPU[i][j];
                            }
                        }
                    }
                    player.buildGrid(matrixGridPlayer);
                    checkWin(matrixGridPlayer, lineByPlayer, columnByPlayer);
                    continue;
                }
                //——————————————————————————————————————————
                //Existe bom por perto
                // TODO: 28/11/2023 Tenho que acabar isto

                for (int i = 0; i < numbersImages.length; i++) {
                    if (matrixGridCPU[lineByPlayer][columnByPlayer].equals(numbersImages[i])) {
                        clean();
                        System.out.println("Not a bomb but...");
                        matrixGridPlayer[lineByPlayer][columnByPlayer] = numbersImages[i];
                        player.buildGrid(matrixGridPlayer);
                        checkWin(matrixGridPlayer, lineByPlayer, columnByPlayer);
                    }
                }
                //——————————————————————————————————————————

                //O jogo acabou Jogador perdeu
                if (matrixGridCPU[lineByPlayer][columnByPlayer].equals(Tiles.TILE_BOMB.getTileImage())) {
                    clean();
                    System.out.println("BOOOOOOM!!");
                    matrixGridCPU[lineByPlayer][columnByPlayer] = Tiles.TILE_BOMB_EXPLODE.getTileImage();
                    for (int i = 0; i < matrixGridCPU.length; i++) {
                        for (int j = 0; j < matrixGridCPU.length; j++) {
                            if (!matrixGridCPU[i][j].equals(Tiles.TILE_BOMB.getTileImage()) && !matrixGridCPU[i][j].equals(Tiles.TILE_PLAYER_ATACK.getTileImage())
                                    && !matrixGridCPU[i][j].equals(Tiles.TILE_BOMB_EXPLODE.getTileImage())) {
                                matrixGridCPU[i][j] = Tiles.DEFAULT.getTileImage();
                            }
                        }
                    }
                    for (int i = 0; i < matrixGridCPU.length; i++) {
                        for (int j = 0; j < matrixGridCPU.length; j++) {
                            if (matrixGridPlayer[i][j].equals(Tiles.TILE_ONE.getTileImage())) {
                                matrixGridCPU[i][j] = Tiles.TILE_PLAYER_ATACK.getTileImage();
                            }
                        }
                    }
                    cpuPlays.buildGrid(matrixGridCPU);
                    Thread.sleep(5000);
                    isAlive = true;
                }

            } catch (InputMismatchException | InterruptedException e) {
                scanner.nextLine();
                System.out.println("Please, insert only numbers\n");
            } catch (ArrayIndexOutOfBoundsException e) {
                scanner.nextLine();
                System.out.println("You need to choose a number between 0-9\n");
            } catch (Exception e) {
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

    public void fillSpace(String[][] oneMatrix, int oneLine, int oneColumn, String oldColor, String newColor) {

        if (oneLine < 0 || oneLine >= oneMatrix.length || oneColumn < 0 || oneColumn >= oneMatrix.length) {
            return;
        }
        if (!oneMatrix[oneLine][oneColumn].equals(oldColor)) {
            return;
        }

        oneMatrix[oneLine][oneColumn] = newColor;

        fillSpace(oneMatrix, oneLine + 1, oneColumn, oldColor, newColor);
        fillSpace(oneMatrix, oneLine - 1, oneColumn, oldColor, newColor);
        fillSpace(oneMatrix, oneLine, oneColumn + 1, oldColor, newColor);
        fillSpace(oneMatrix, oneLine, oneColumn - 1, oldColor, newColor);

    }

    public void checkWin(String[][] matrixGridPlayer, int oneLine, int oneColumn) throws InterruptedException {
        int counter = 0;
        for (int i = 0; i < matrixGridPlayer.length; i++) {
            for (int j = 0; j < matrixGridPlayer.length; j++) {
                if (matrixGridPlayer[i][j].equals(Tiles.TILE_PLAYER_ATACK.getTileImage())) {
                    counter++;
                }
            }
        }
        for (int i = 0; i < numbersImages.length; i++) {
            if (matrixGridPlayer[oneLine][oneColumn].equals(numbersImages[i])) {
                counter++;
            }
        }
        System.out.println();
        System.out.println("Number of know places "+counter);
        if (counter >= 90) {
            clean();
            System.out.println("YOU WON");
            player.buildGrid(matrixGridPlayer);
            Thread.sleep(5000);
            isAlive = true;
        }
    }

    public void checkRepeatPosition(String[][] matrixGridPlayer, int lineByPlayer, int columnByPlayer) {

        if (matrixGridPlayer[lineByPlayer][columnByPlayer].equals(Tiles.TILE_PLAYER_ATACK.getTileImage())) {
            System.out.println("You have already play in this position");
        }
        if (matrixGridPlayer[lineByPlayer][columnByPlayer].equals(Tiles.TILE_ONE.getTileImage())) {
            System.out.println("You have already play in this position");
        }
    }

}
