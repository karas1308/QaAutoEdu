package games;

public class Matrix {

//Рисуем квадритную матрицу значениями от 1 до 9

    public static void matrix(int n) {
        int k = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(k++);
                if (k > 9)
                    k = 1;
            }
            System.out.println();
        }

    }

}