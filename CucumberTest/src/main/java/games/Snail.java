package games;

public class Snail {

    public static int[][] calculateSnail(int size) {
        int[][] m = new int[size][size];
        int start = 0;
        if (size % 2 == 0) {
            start = (size - 2) / 2;
        } else {
            start = (size - 2) / 2 + 1;
        }
        int i = start;
        int j = start;
        int min_i = i;
        int max_i = i;
        int min_j = j;
        int max_j = j;
        int d = 0;
        for (int a = 1; a <= size * size; a++) {
            m[i][j] = a;
            switch (d) {
                case 0:
                    j += 1;
                    if (j > max_j) {
                        d = 3;
                        max_j = j;
                    }
                    break;
                case 1:
                    i -= 1;
                    if (i < min_i) {
                        d = 0;
                        min_i = i;
                    }
                    break;
                case 2:
                    j -= 1;
                    if (j < min_j) {
                        d = 1;
                        min_j = j;
                    }
                    break;
                case 3:
                    i += 1;
                    if (i > max_i) {
                        d = 2;
                        max_i = i;
                    }
            }
        }
        for (int k = 0; k < size; k++) {
            for (int v = 0; v < size; v++)
                System.out.print(m[k][v] + "\t");
            System.out.println();
        }
        return m;
    }
}