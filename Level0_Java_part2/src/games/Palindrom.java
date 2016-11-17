package games;

public class Palindrom {

//    public static void main(String[] args) {
//
//    }

    public static boolean isPalindrome(String data) {
            data = data.toLowerCase();
            data = data.replace(" ", "");
            int dataLen = data.length();
            for(int i = 0; i < dataLen / 2; i++)
                if(data.charAt(i) != data.charAt(dataLen - i - 1))
                    return false;
            return true;
        }


    public static boolean checkPhrase(String somePhrase) {
        int dataLen = somePhrase.length();
        for (int i = 0; i < dataLen / 2; i++)
            if (somePhrase.charAt(i) != somePhrase.charAt(dataLen - i - 1))
                return false;
        return true;
    }
}

//"В пакете games создаем класс Palindrom. В этом классе создаем следующие методы:
//        - checkWord(String someWord) - проверяем, является ли слово палиндромом (регистр не учитывается, пробелов быть
//        не должно)
//        - checkPhrase(String somePhrase) - проверяем, является ли фраза палиндромом(без учета регистра и пробелов).
//        Оба метода должны возвращать булевое значение(true - если палиндром, false - если нет)."
//        В методе main продемонстрировать работу двух методов класса Palindrom, предлагая пользователю ввести фразу и
//        выводя в консоль описание результата (например, "Такая -то фраза является палиндромом" либо наобророт).