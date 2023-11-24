public class Grid {
    static String[][] matrixGrid = new String[10][10];

    public void makeNewGrid() {
        for (int i = 0; i < matrixGrid.length; i++) {
            for (int j = 0; j < matrixGrid.length; j++) {
                matrixGrid[i][j] = "\t🟩";
            }
        }
        //——————————————————————————————————————————————————————————————————
    }
    public void buildGrid(){
        for (int i = 0; i < matrixGrid.length; i++) {
            if (i == 0) {
                System.out.print("\t|"+i+"|");
            }else System.out.print(" |"+i+"| ");
        }
        System.out.println();
        System.out.println("\t——————————————————————————————————————————");
        for (int i = 0; i < matrixGrid.length; i++) {
            System.out.print("\t"+i + "| ");
            for (int j = 0; j < matrixGrid.length; j++) {
                System.out.print(matrixGrid[i][j]);
            }
            System.out.println();
        }
    }
}
