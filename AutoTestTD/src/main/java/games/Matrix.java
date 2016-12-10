package games;

public class Matrix {

//Рисуем квадритную матрицу значениями от 1 до 9

    public static int[][] matrix(int n) {
        int k = 1;
        int[][] mt = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mt[i][j] = k++;
                if (k > 9)
                    k = 1;
            }
        }
        return mt;
    }
}