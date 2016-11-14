package calculator;

import java.util.Scanner;

//import static calculator.Arithmetics.arrayMultiplication;
//import static calculator.Arithmetics.power;
//import static calculator.Arithmetics.division;
import static calculator.Arithmetics.*;

public class Main {
    public static int[] array;

    public static void main(String[] args) {
        //Вводим массив из 5 чисел. получаем их произведение
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[5];
        System.out.println("Введите 5 чисел:");
        for (int i = 0; i < 5; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println("Произведение всех элементов массива = " + arrayMultiplication(array));

        //Вводим 2 числа, получаем возведение в степень
        System.out.println("Введите 2 числа. В результате получим восроиздение первого в степень второго:");
        Scanner step = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        System.out.println(a + " в степени " + b + " = " + power(a, b));

        //Вводим 2 числа, Первое делим на второе
        System.out.println("Введите 2 числа. В результате получим результат делиния первого на второй.");
        Scanner del = new Scanner(System.in);
        double c = scanner.nextDouble();
        double d = scanner.nextDouble();
        System.out.println(c + " разделить на " + d + " = " + division(c, d));

        //Вводим число, Находим квадратный корень
        System.out.println("Введите число. В результате получим квадратный корень числа.");
        Scanner sq = new Scanner(System.in);
        double sq1 = scanner.nextDouble();
        System.out.println("Квадратный корень из " + sq1 + " равен " + root(sq1));

    }
}
