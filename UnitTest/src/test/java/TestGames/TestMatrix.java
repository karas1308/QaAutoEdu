package TestGames;

import games.Matrix;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestMatrix {
    //Проверяем, чтоб в матрице максимальный элемент был 9
    @Test
    public void testMaxElemMatr_8x8() {
        int n = 8;
        int[][] m = Matrix.matrix(n);
        int b9 = m[0][0];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] > 9) {
                    b9 = m[i][j];
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(m[i][j]);
            }
            System.out.println();
        }
        assertFalse("В матрице затесались элементы больше " + b9, b9 > 9);
    }

    //Количество элеметов матрицы
    @Test
    public void testConutElemMatr_7x7() {
        int n = 7;
        int[][] m = Matrix.matrix(n);
        int count = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                count++;
            }
        }
        assertTrue("Количество элементов не равно 49, а равно " + count, count == 49);
    }
    @Test
    public void testConutElemMatr_3x3() {
        int n = 3;
        String str="";
        int[][] m = Matrix.matrix(n);
        for (int i = n-1; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                str+=m[i][j];

            }
            System.out.print(str);
        }
        assertTrue("Последняя строка матрицы не равна 789, а равна " + str, str.equals("789"));
    }
}
