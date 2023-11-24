public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Grid grid = new Grid();
        grid.makeNewGrid();
        grid.buildGrid();

        placeBombsOnGrid placeBombsOnGrid = new placeBombsOnGrid();
        placeBombsOnGrid.plceBombs();

        CheckBombs.playerTryToFindBoms();
    }
}