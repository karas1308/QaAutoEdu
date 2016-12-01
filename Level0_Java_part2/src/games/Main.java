package games;

import java.util.Scanner;

public class Main {
    String str = "";

    public static void main(String[] args) {
        for (int i = 1; ; i++) {
            System.out.println("Введите число от 1 до 9. Для выхода введите q");
            Scanner in = new Scanner(System.in); //Ведите имя
            String str = in.nextLine();

            if (str.equals("q")) {
                System.out.println("Пока пока :("); //Выходим, если ввели q
                break;
            }
            char c = str.charAt(0);
            if (!str.equals("q") && (Character.isDigit(c) == false)) {
                System.out.println("Вы ввели херню");
                continue;
            }
            int n = Integer.parseInt(str);
            if (n < 1 || n > 9) {
                System.out.println("Вы ввели херню");
                continue;
            }
            if (n > 0 && n < 10) {
                int[][] printMatr = Matrix.matrix(n);
                for (i = 0; i < printMatr.length; i++) {
                    for (int j = 0; j < printMatr.length; j++) {
                        System.out.print(printMatr[i][j]);
                    }
                    System.out.println();
                }
            }
        }
        //Улитка
        System.out.println("Введите число > 3");
        Scanner in = new Scanner(System.in); //Ведите имя
        int size = in.nextInt();
        System.out.println("Вы ввели " + size);
        Snail.calculateSnail(size);

        //Палиндром без учеты регистра и пробелов
        System.out.println("Введите строку. Палиндром она или нет, скажу Вам я. Регистр и пробелы будут проигнорированя");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        if (Palindrom.checkWord(s)) {
            System.out.println("Таки да, палиндром");
        } else {
            System.out.println("Не, нифига не палиндром");
        }

        //Палиндром с учетом регистра и пробелов
        System.out.println("Введите строку. Палиндром она или нет, скажу Вам я. Пробелы и регистр будем учитывать");
        scan = new Scanner(System.in);
        String s1 = scan.nextLine();
        if (Palindrom.checkPhrase(s1)) {
            System.out.println("Таки да, палиндром");
        } else {
            System.out.println("Не, нифига не палиндром");

        }
    }
}