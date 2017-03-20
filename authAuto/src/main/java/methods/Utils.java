package methods;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
        return new File(Utils.class.getClassLoader().getResource(fileName).getFile());
    }

    public static String urlDecode(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}