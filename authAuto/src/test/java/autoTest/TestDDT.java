package autoTest;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)

public class TestDDT {
    public static JSONObject json;
    public static String sid;
    public static String uuid;
    public static String autoruuid;
    public static String x_auth;
    public static long millis = System.currentTimeMillis();
    public static String url_api2 = "https://api2.auto.ru/1.1/search?category_id=15&page_num=1&page_size=50&creation_date_to=" + millis;
    String username = "yuioru@yandex.ru";
    String password = "111111";

    static CloseableHttpClient client = HttpClients.createDefault();


    @BeforeClass
    public static void beforeClass() throws IOException {
        json = FirstConnectJson.json();
        sid = String.valueOf(json.get("sid"));
        uuid = "OAuth" + " " + String.valueOf(json.getJSONObject("result").get("uuid"));
        x_auth = "Vertis" + " " + String.valueOf(json.getJSONObject("result").get("uuid")) + " " + "5c27f9e8-2b90-433e-a0bf-b5222bbd97d0";
        autoruuid = String.valueOf(json.get("autoruuid"));
    }

    private Integer rig, mark, km_age_from, km_age_to;

    @Parameterized.Parameters
    public static Collection testMark_Rig() {
        return Arrays.asList(
                new Object[][]{
                        {213, 15, 10000, 20000},
                        {1, -1, 10000, 30000},
                        {213, 15, 10000, 40000}
                }
        );
    }

    public TestDDT(Integer rig, Integer mark, Integer km_age_from, Integer km_age_to) {
        this.rig = rig;
        this.mark = mark;
        this.km_age_from = km_age_from;
        this.km_age_to = km_age_to;
    }

    @Test //Тест проверяет корректность выдачи по фильтру Пробег
    public void km_age() throws IOException {
        //int km_age_from = 40000;
        // int km_age_to = 42000;
        HttpGet get = new HttpGet(url_api2 + "&km_age_from=" + km_age_from + "&km_age_to=" + km_age_to);
        get.setHeader("Authorization", uuid);
        get.setHeader("X-Authorization", x_auth);
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONObject jsonTets = new JSONObject(EntityUtils.toString(entity, UTF_8));
        int lenght = jsonTets.getJSONArray("list").length();
        for (int i = 0; i < lenght; i++) {
            String km_ageStr = String.valueOf(jsonTets.getJSONArray("list").getJSONObject(i).get("km_age"));
            int km_age = Integer.valueOf(km_ageStr);
            assertTrue("Пробег выходит за параметры фильтров - " + km_age, km_age >= km_age_from & km_age <= km_age_to);
        }
    }

    @Test
    public void test() throws IOException {

//        System.out.println(json);
//        System.out.println(sid);
//        System.out.println(uuid);
        HttpGet get = new HttpGet("https://api2.auto.ru/1.1/search?page_num=1&page_size=20&prepend_empty_option=1&state=USED&sort=fresh_relevance_1-desc&category_id=15&photo=1&rid=" + rig + "&mark_id=" + mark + "&creation_date_to=" + millis);
        // HttpGet get = new HttpGet("https://api2.auto.ru/1.0/search/count?category_id=15&rid="+rig+"&mark_id="+mark);
        get.setHeader("Authorization", uuid);
        get.setHeader("X-Authorization", x_auth);
        get.setHeader("Content-Type", "application/json");
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONObject test = new JSONObject(EntityUtils.toString(entity, UTF_8));

//        System.out.println(get);
//        System.out.println(entity);
//        System.out.println(response);
//           System.out.println(test);
        System.out.println(test.getJSONArray("list").getJSONObject(0).get("mark"));
        System.out.println(test.getJSONArray("list").getJSONObject(0).get("model"));
        System.out.println(test.getJSONArray("list").getJSONObject(0).get("seller"));
        System.out.println(test.getJSONArray("list").getJSONObject(0).get("price"));
        // System.out.println(get.getURI());
//        "Accept-Encoding", "gzip, deflate, identity");
//        get.setHeader("Accept-Language",

    }
}
