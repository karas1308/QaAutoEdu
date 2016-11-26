package games;

public class Palindrom {

    static int i;


    public static boolean checkWord(String someWord) {

        someWord = someWord.toLowerCase();
        String temp = "";
        String temp1 = "";
        if (someWord.contains(" "))
            return false;
        else {
            for (i = 0; i < someWord.length() / 2; i++)
                temp = temp + someWord.charAt(someWord.length() - i - 1);
            for (i = 0; i < someWord.length() / 2; i++)
                temp1 = temp1 + someWord.charAt(i);
            return temp.equals(temp1);
        }
//        StringBuffer stringBuffer = new StringBuffer(someWord);
//        return stringBuffer.reverse().toString().equals(someWord);
    }


    public static boolean checkPhrase(String somePhrase) {
        String temp = "";
        String temp1 = "";
        somePhrase = somePhrase.toLowerCase();
        somePhrase = somePhrase.replace(" ", "");
        for (i = 0; i < somePhrase.length() / 2; i++)
            temp = temp + somePhrase.charAt(somePhrase.length() - i - 1);
        for (i = 0; i < somePhrase.length() / 2; i++)
            temp1 = temp1 + somePhrase.charAt(i);
        return temp.equals(temp1);
    }
}


//"В пакете games создаем класс Palindrom. В этом классе создаем следующие методы:
//        - checkWord(String someWord) - проверяем, является ли слово палиндромом (регистр не учитывается, пробелов быть
//        не должно)
//        - checkPhrase(String somePhrase) - проверяем, является ли фраза палиндромом(без учета регистра и пробелов).
//        Оба метода должны возвращать булевое значение(true - если палиндром, false - если нет)."
//        В методе main продемонстрировать работу двух методов класса Palindrom, предлагая пользователю ввести фразу и
//        выводя в консоль описание результата (например, "Такая -то фраза является палиндромом" либо наобророт).