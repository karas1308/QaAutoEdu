package autoTest;

import com.jayway.restassured.response.Response;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
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
import java.util.HashMap;
import java.util.Map;

public class AutoTest {
    public static final String ACCESS_KEY = "";
    //  public static final String BASE_URL = "https://api.auto.ru/rest/?sid=&client_tz=180&method=api.service.getUuid&client_version=3.10.1&key=1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a&client_os=6.0.1&version=2.2.2&device_name=motorola%20XT1562&client_platform=android&format=json";
    public static JSONObject json;
    public static String sid;
    public static String uuid;
    public static String autoruuid;
    public static String x_auth;
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

    @Test
    public void NumberRegions() throws IOException {

//        System.out.println(json);
//        System.out.println(sid);
//        System.out.println(uuid);
        HttpGet get = new HttpGet("https://api2.auto.ru/1.1/regions");
        get.setHeader("Authorization", uuid);
        get.setHeader("X-Authorization", x_auth);
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONArray regions = new JSONArray(EntityUtils.toString(entity));
        // regions.optJSONObject(0);
        System.out.println(regions.getJSONObject(1));
        System.out.println(regions.getJSONObject(1).get("rid"));
        String cp866 = new String(regions.getJSONObject(1).get("name").toString().getBytes(), "windows-1251");
        System.out.print(cp866);
    }

    @Test
    public void NumberRegions10841() throws IOException {

//        System.out.println(json);
//        System.out.println(sid);
//        System.out.println(uuid);
        HttpGet get = new HttpGet("https://api2.auto.ru/1.1/regions/10841");
        get.setHeader("Authorization", "OAuth eb33980619853b4fab16f0349ca64c78");
        get.setHeader("X-Authorization", "Vertis eb33980619853b4fab16f0349ca64c78 5c27f9e8-2b90-433e-a0bf-b5222bbd97d0");
        get.setHeader("Accept-Encoding", "gzip, deflate, identity");
        get.setHeader("Accept-Language", "en-RU;q=1, ru-RU;q=0.9, uk-RU;q=0.8, it-RU;q=0.7, de-RU;q=0.6");
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONArray regions = new JSONArray(EntityUtils.toString(entity));
        System.out.println(regions.get(1));
        System.out.println();
        String cp866 = new String(String.valueOf(regions.getJSONObject(1).get("name")));
        System.out.print(cp866);
    }

    @Test
    public void test() throws IOException {

        System.out.println(json);
        System.out.println(sid);
        System.out.println(uuid);
        HttpGet get = new HttpGet("https://api2.auto.ru/1.0/search/count?category_id=15&mark_id=-2");
        get.setHeader("Authorization", uuid);
        get.setHeader("X-Authorization", "Vertis eb33980619853b4fab16f0349ca64c78 5c27f9e8-2b90-433e-a0bf-b5222bbd97d0");
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONObject test = new JSONObject(EntityUtils.toString(entity));
        System.out.println(test);
    }

    @Test
    public void mark_list() throws IOException {

        System.out.println(json);
        System.out.println(sid);
        System.out.println(uuid);
        HttpGet get = new HttpGet("https://api.auto.ru/rest/?method=all.mark.getList&category_id=15&sid=e21f5804f95d3252_87ac09264e00d4026dbbfbdee316b702&client_tz=180&method=all.mark.getList&client_version=3.10.1&key=1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a&client_os=6.0.1&version=2.2.2&uuid=1a63c0971a6d72f43156c4fe2a8edf98&device_name=motorola%20XT1562&client_platform=android&format=json");
        get.setHeader("Authorization", uuid);
        get.setHeader("X-Authorization", x_auth);
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONObject test = new JSONObject(EntityUtils.toString(entity));
        System.out.println(test);
    }


    @Test
    public void testAutori() throws IOException {
        Map<String, Object> jsonValues = new HashMap<String, Object>();
        jsonValues.put("login", "yuioru@yandex.ru");
        jsonValues.put("pass", "111111");
        jsonValues.put("sid", "e221f177f91f444e_e757ecaf620c0233e5af8f11721565e3");
        jsonValues.put("client_tz", "180");
        jsonValues.put("method", "users.auth.login");
        jsonValues.put("client_version", "3.10.1");
        jsonValues.put("key", "1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a");
        jsonValues.put("uuid", "f8cd3d92047755055dce604bab99be82");
        jsonValues.put("format", "json");
        jsonValues.put("client_platform", "android");


        JSONObject json = new JSONObject(jsonValues);
        String url = "https://api.auto.ru/rest/";
        HttpPost httpost = new HttpPost(url);

        StringEntity entity = new StringEntity(json.toString());
          //entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httpost.setEntity(entity);

        //   HttpResponse response = client.execute(httpost);

        CloseableHttpResponse response = client.execute(httpost);
        HttpEntity entity1 = response.getEntity();
       // JSONObject test = new JSONObject(EntityUtils.toString(entity1));


        //HttpEntity entity = response.getEntity();
        String test = new String(EntityUtils.toString(entity1));
        System.out.println(httpost);
        System.out.println(response.toString());
        System.out.println("test " + test);
        System.out.println(json);
    }
}


//    //creating map object to create JSON object from it
//    Map< String, Object >jsonValues = new HashMap< String, Object >();
//jsonValues.put("param1", message);
//        jsonValues.put("param2", message);
//        jsonValues.put("param3", message);
//        JSONObjectjson = new JSONObject(jsonValues);
//        String url = "http://Yout_URL";
//        DefaultHttpClient client = new DefaultHttpClient();
//        HttpPost post = new HttpPost(url);
//        post.setHeader("Accept", "application/json");
//        post.setHeader("headerValue", "HeaderInformation");
////setting json object to post request.
//        StringEntity entity = new StringEntity(json.toString(), "UTF8");
//        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//        post.setEntity(entity);
////this is your response:
//        HttpResponse response = client.execute(post);
//        System.out.println("Response: " + response.getStatusLine());
//        return response.getStatusLine().toString();
