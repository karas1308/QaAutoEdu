package games;

import java.util.Scanner;

public class Palindrom {

    public static void main(String[] args) {
        System.out.println("Введите строку. Палиндром она или нет, скажу Вам я.");
        Scanner scan = new Scanner(System.in);
        String s=scan.nextLine();
        //isPalindrome(s);
        if(isPalindrome(s) == false) {
            System.out.println("Не, нифига не палиндром");
        }
        else {
            System.out.println("Таки да, палиндром");
        }
    }

    private static boolean isPalindrome(String data) {
            data = data.toLowerCase();
            data = data.replace(" ", "");
            int dataLen = data.length();
            for(int i = 0; i < dataLen / 2; i++)
                if(data.charAt(i) != data.charAt(dataLen - i - 1))
                    return false;
            return true;
        }
    }

//        public String checkPhrase(String somePhrase){
//        }
//
//}

//"В пакете games создаем класс Palindrom. В этом классе создаем следующие методы:
//        - checkWord(String someWord) - проверяем, является ли слово палиндромом (регистр не учитывается, пробелов быть
//        не должно)
//        - checkPhrase(String somePhrase) - проверяем, является ли фраза палиндромом(без учета регистра и пробелов).
//        Оба метода должны возвращать булевое значение(true - если палиндром, false - если нет)."
//        В методе main продемонстрировать работу двух методов класса Palindrom, предлагая пользователю ввести фразу и
//        выводя в консоль описание результата (например, "Такая -то фраза является палиндромом" либо наобророт).