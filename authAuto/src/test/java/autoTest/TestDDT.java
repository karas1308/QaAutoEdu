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

@RunWith(value = Parameterized.class)

public class TestDDT {
    public static JSONObject json;
    public static String sid;
    public static String uuid;
    public static String autoruuid;
    public static String x_auth;
    public static long millis = System.currentTimeMillis();
    String username = "yuioru@yandex.ru";
    String password = "111111";

    static CloseableHttpClient client = HttpClients.createDefault();


    @BeforeClass
    public static void beforeClass() throws IOException {
        json = FirstConnectJson.json();
        sid = String.valueOf(json.get("sid"));
        uuid ="OAuth" +  " " + String.valueOf(json.getJSONObject("result").get("uuid"));
        x_auth = "Vertis" + " " + String.valueOf(json.getJSONObject("result").get("uuid"))+ " " +"5c27f9e8-2b90-433e-a0bf-b5222bbd97d0";
        autoruuid = String.valueOf(json.get("autoruuid"));
    }
    private Integer rig , mark;
    @Parameterized.Parameters
    public static Collection testMark_Rig(){
        return Arrays.asList(
                new Object[][]{
                        {213,15},
                        {1,-1},
                        {213,15}
                }
        );
    }
    public TestDDT(Integer rig, Integer mark){
        this.rig = rig;
        this.mark = mark;
    }
    @Test
    public void test() throws IOException {

//        System.out.println(json);
//        System.out.println(sid);
//        System.out.println(uuid);
        HttpGet get = new HttpGet("https://api2.auto.ru/1.1/search?page_num=1&page_size=20&prepend_empty_option=1&state=USED&sort=fresh_relevance_1-desc&category_id=15&photo=1&rid="+rig+"&mark_id="+mark+ "&creation_date_to=" +millis );
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
        System.out.println(test.getJSONArray("list").getJSONObject(0).get("seller"));
        System.out.println(test.getJSONArray("list").getJSONObject(0).get("price"));
       // System.out.println(get.getURI());
//        "Accept-Encoding", "gzip, deflate, identity");
//        get.setHeader("Accept-Language",

    }
}
