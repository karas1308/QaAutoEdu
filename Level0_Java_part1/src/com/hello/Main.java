package com.hello;

import com.welcome.Hello;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Hello test = new Hello();
        System.out.println("Как тебя зовут?");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();            //Ведите имя
        test.welcome();                                 // System.out.println("Hello, " +name1);
        System.out.println("Hello, world!!!");
        test.byeBye();                                  //System.out.println("byeBye, " + name1);
    }


}
