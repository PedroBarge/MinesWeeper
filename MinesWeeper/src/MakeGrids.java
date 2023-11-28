public class MakeGrids {
    void makeNewGrid(Position[][] oneMatrix) {
        for (int i = 0; i < oneMatrix.length; i++) {
            for (int j = 0; j < oneMatrix.length; j++) {
                oneMatrix[i][j] = new Position(Tiles.DEFAULT.getIndex(), Tiles.DEFAULT.getTileImage());
            }
        }
    }

    void buildGrid(Position[][] oneMatrix) {
        for (int i = 0; i < oneMatrix.length; i++) {
            System.out.print("\t" + i);
        }
        System.out.println();
        System.out.println("\t——————————————————————————————————————");
        for (int i = 0; i < oneMatrix.length; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < oneMatrix.length; j++) {
                System.out.print(oneMatrix[i][j].getImageEmoji());
            }
            System.out.println("|");
        }
        System.out.println("\t——————————————————————————————————————");

    }
}
