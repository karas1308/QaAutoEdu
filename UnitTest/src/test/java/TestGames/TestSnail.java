package TestGames;

import games.Snail;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestSnail {

    @Test
    public void testElem1and5nail() {
        int size = 5;
        int[][] m = Snail.calculateSnail(size);
        assertTrue("Что-то пошло не так " + m[0][m.length - 1], m[0][m.length - 1] == 25);
    }

    @Test
    public void testElem6and6Snail() {
        int size = 6;
        int[][] m = Snail.calculateSnail(size);
        assertTrue("Элемент (нижний правый)не равен " + m[m.length - 1][m.length - 1],
                m[m.length - 1][m.length - 1] == 31);
    }

    @Test
    public void testMaxElemSnail() {
        int size = 7;
        int[][] m = Snail.calculateSnail(size);
        int maxValue = m[0][0];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] > maxValue) {
                    maxValue = m[i][j];
                }
            }
        }
        assertTrue("Максимальное значение не равно размерности массива в квадрате " +
                maxValue, maxValue == size * size);
    }

    //Проверяем, что нет нулевых значений
    @Test
    public void testZeroElemSnail() {
        int size = 8;
        int[][] m = Snail.calculateSnail(size);
        int zeroValue = m[0][0];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] == 0) {
                    zeroValue = m[i][j];
                    break;
                }
            }
        }
        assertFalse("В матрице есть нулевые элементы, переделывай! ", zeroValue == 0);
    }

    @Test
    public void testSumElemSnail() {
        int size = 4;
        long sum = 0;
        int[][] m = Snail.calculateSnail(size);
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                sum = sum + m[i][j];
            }
        }
        assertTrue("Что-то пошло не так " + sum, sum == 136);
    }
}


//4	"Пишем тест на метод calculateSnail класса Snail:
//        Тест проверяет, что самое большое число в масиве равно его размерности в квадрате.
//        То есть напрмиер размерность 3 - самое большое число массива - 9,
//        размерность 5 - самое большое число массива 25 и т.д."
//        5	Пишем еще 3  других теста на класс Snail.