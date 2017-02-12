package autoTest;
import com.jayway.restassured.response.Response;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.ArrayList;

public class AutoTest {
    public static final String ACCESS_KEY = "";
    //  public static final String BASE_URL = "https://api.auto.ru/rest/?sid=&client_tz=180&method=api.service.getUuid&client_version=3.10.1&key=1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a&client_os=6.0.1&version=2.2.2&device_name=motorola%20XT1562&client_platform=android&format=json";
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

    @Test
    public void NumberRegions() throws IOException {

//        System.out.println(json);
//        System.out.println(sid);
//        System.out.println(uuid);
        HttpGet get = new HttpGet("https://api2.auto.ru/1.1/regions");
        get.setHeader("Authorization", "OAuth eb33980619853b4fab16f0349ca64c78");
        get.setHeader("X-Authorization", "Vertis eb33980619853b4fab16f0349ca64c78 5c27f9e8-2b90-433e-a0bf-b5222bbd97d0");
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONArray regions = new JSONArray(EntityUtils.toString(entity));
        System.out.println(regions.length());
    }
    @Test
    public void NumberRegions10841() throws IOException {

//        System.out.println(json);
//        System.out.println(sid);
//        System.out.println(uuid);
        HttpGet get = new HttpGet("https://api2.auto.ru/1.1/regions/10841");
        get.setHeader("Authorization", "OAuth eb33980619853b4fab16f0349ca64c78");
        get.setHeader("X-Authorization", "Vertis eb33980619853b4fab16f0349ca64c78 5c27f9e8-2b90-433e-a0bf-b5222bbd97d0");
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONArray regions = new JSONArray(EntityUtils.toString(entity));
        System.out.println(regions.length());
    }

    @Test
    public void test() throws IOException {

        System.out.println(json);
        System.out.println(sid);
        System.out.println(uuid);
        HttpGet get = new HttpGet("https://api2.auto.ru/1.0/search/count?category_id=15&mark_id=-2");
        get.setHeader("Authorization", sid);
        get.setHeader("X-Authorization", "Vertis eb33980619853b4fab16f0349ca64c78 5c27f9e8-2b90-433e-a0bf-b5222bbd97d0");
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONObject test = new JSONObject(EntityUtils.toString(entity));
        System.out.println(test);
    }



}

