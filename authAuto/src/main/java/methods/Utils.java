package methods;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jayway.restassured.response.Response;

public class Utils {
    public static String[] splitToArray(String str) {
        String[] a = str.replace("[", "").replace("]", "").split(",");
        List<String> tmp = new ArrayList<String>();
        for (String string : a) {
            tmp.add(string.trim());
        }
        return tmp.toArray(new String[tmp.size()]);
    }

    public static void prt(Response prt) {
        System.out.println(prt.body().asString());
    }

    public static int getRndInt(int i) {
        return new Random().nextInt(i);
    }

    public static File getFile(String fileName) {
        ClassLoader classLoader = Utils.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}