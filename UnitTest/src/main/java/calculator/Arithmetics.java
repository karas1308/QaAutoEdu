package calculator;

public class Arithmetics {

    //Произведение элементов массива
    public static int arrayMultiplication(int myArray[]) {
        int sum = 1;
        for (int num : myArray) {
            sum = sum * num;
        }
        return sum;
    }

    public static double power(double a, double b) {
        double rez = Math.pow(a, b);
        return rez;
    }

    public static double division(double a, double b) {
        double del = a / b;
        return del;
    }

    public static double root(double a) {
        double sq = Math.sqrt(a);
        return sq;
    }
}
