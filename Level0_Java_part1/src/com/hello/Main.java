package com.hello;
import com.welcome.Hello;
import java.util.Scanner;

public class Main {
    private static String name;

    public String getName(){
        return name;
    }
    public static void main(String[] args) {

        System.out.println("Как тебя зовут?");
        Scanner in = new Scanner(System.in); //Ведите имя
        String name = in.nextLine();
        Hello test = new Hello();
        test.setupName(name);
        test.welcome();                                 // System.out.println("Hello, " +name1);
        System.out.println("Hello, world!!!");
        test.byeBye();                                  //System.out.println("byeBye, " + name1);
    }


}
