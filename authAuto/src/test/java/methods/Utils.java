package methods;

import java.util.Random;

import com.jayway.restassured.response.Response;

public class Utils {
    public static String[] splitToArray(String str) {
        String[] a = str.replace("[", "").replace("]", "").split(",");
        return a;
    }

    public static void prt(Response prt) {
        System.out.println(prt.body().asString());
    }

    public static int getRndInt(int i) {
        return new Random().nextInt(i);
    }
}