package autoTest;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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

@RunWith(value = Parameterized.class)

public class TestDDT {
    public static JSONObject json;
    public static String sid;
    public static String uuid;
    public static String autoruuid;
    String username = "yuioru@yandex.ru";
    String password = "111111";

    static CloseableHttpClient client = HttpClients.createDefault();


    @BeforeClass
    public static void beforeClass() throws IOException {
        json = FirstConnectJson.json();
        sid = String.valueOf(json.get("sid"));
        uuid = String.valueOf(json.getJSONObject("result").get("uuid"));
        autoruuid = String.valueOf(json.get("autoruuid"));
    }
    private Integer rig , mark;
    @Parameterized.Parameters
    public static Collection testMark_Rig(){
        return Arrays.asList(
                new Object[][]{
                        {213,-2},
                        {1,-1},
                        {15,15}
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
        HttpGet get = new HttpGet("https://api2.auto.ru/1.0/search/count?category_id=15&rid="+rig+"&mark_id="+mark);
        get.setHeader("Authorization", sid);
        get.setHeader("X-Authorization", "Vertis eb33980619853b4fab16f0349ca64c78 5c27f9e8-2b90-433e-a0bf-b5222bbd97d0");
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONObject test = new JSONObject(EntityUtils.toString(entity));
        System.out.println(test);
    }
}
