package methods;

import com.jayway.restassured.response.Response;

public class Utils2 {
    public static String[] splitToArray(String str) {
       String [] a = str.replace("[", "").replace("]", "").split(",");
        return a;
    }

    public static void prt (Response prt) {
        System.out.println(prt.body().asString());
    }
}