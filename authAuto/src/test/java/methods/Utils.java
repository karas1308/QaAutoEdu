package methods;

public class Utils {
    public static String[] replaceSome(String str) {
       String [] a = str.replace("[", "").replace("]", "").split(",");
        return a;
    }
}