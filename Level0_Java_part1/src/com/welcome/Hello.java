package com.welcome;




public class Hello {

    private String name;
    private String name1;

    public String setupName(String name) {

        name1 = name;
        return name;
    }

    public void welcome() {
        System.out.println("Hello, " + name1);


    }

    public void byeBye() {

        System.out.println("byeBye, " + name1);
    }


}