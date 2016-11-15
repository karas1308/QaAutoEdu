package games;

public class Snail {

    public static void calculateSnail(int size) {
        //   size = 5;
        int[][] m = new int[size][size];
        // центр
        int i = size / 2;
        int j = size / 2;
        // задаем границы движения
        int min_i = i; int max_i = i; // влево вправо
        int min_j = j; int max_j = j; // вверх вниз
        int d = 0; // сначала пойдем влево
        for (int a = 1; a <= size * size; a++) {
            m[i][j] = a;
            switch (d) {
                case 0:  // движение вниз проверка снизу
                    j += 1;
                    if (j > max_j) {
                        d = 3;
                        max_j = j;
                    }
                    break;
                case 1:
                    i -= 1;  // движение влево
                    if (i < min_i) { // проверка выхода за заполненную центральную часть слева
                        d = 0; // меняем направление
                        min_i = i; // увеличиваем заполненную часть влево
                    }
                    break;
                case 2:  // движение вверх проверка сверху
                    j -= 1;
                    if (j < min_j) {
                        d = 1;
                        min_j = j;
                    }
                    break;
                case 3:  // движение вправо проверка справа
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
    }
}