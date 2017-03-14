package methods;

import com.jayway.restassured.response.Response;

public class Utils {
    public static String[] replaceSome(String str) {
       String [] a = str.replace("[", "").replace("]", "").split(",");
        return a;
    }

    public static void prt (Response prt) {
        System.out.println(prt.body().asString());
    }
}