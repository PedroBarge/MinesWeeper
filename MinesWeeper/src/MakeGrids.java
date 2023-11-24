public class MakeGrids {

    void makeNewGrid(String[][] oneMatrix) {
        for (int i = 0; i < oneMatrix.length; i++) {
            for (int j = 0; j < oneMatrix.length; j++) {
                oneMatrix[i][j] = "\t🟩";
            }
        }
    }
    void buildGrid(String[][] oneMatrix) {
        for (int i = 0; i < oneMatrix.length; i++) {
            System.out.print("\t"+i);
        }
        System.out.println();
        System.out.println("\t——————————————————————————————————————");
        for (int i = 0; i < oneMatrix.length; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < oneMatrix.length; j++) {
                System.out.print(oneMatrix[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("\t——————————————————————————————————————");

    }
}
