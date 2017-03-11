package autoTest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;


public class FirstConnectJson {
    public static String api = "https://api.auto.ru";
    public static Response json;
    public static String sid;
    public static String uuid_header;
    public static String uuid;
    public static String autoruuid;
    public static String x_auth;
    public static String auth_sid;
    public static String auth_sid_key;
    public static String auth_autoruuid;
    public static String username = "yuioru@yandex.ru";
    public static String password = "111111";

    public static String key = "1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a";


    static CloseableHttpClient client = HttpClients.createDefault();

    public static Response getUuid() throws IOException {

        RestAssured.baseURI = api;
        Response json =
                given().headers("Accept-Encoding", "gzip").
                        parameters("method", "api.service.getUuid", "key", key, "version", "2.2.2", "format", "json").
                        get("/rest/");

//        HttpGet get = new HttpGet(api+"/rest/?sid=&method=api.service.getUuid&key=1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a&version=2.2.2&format=json");
//        CloseableHttpResponse response = client.execute(get);
//        HttpEntity entity = response.getEntity();
//        JSONObject json = new JSONObject(EntityUtils.toString(entity));
        return json;
    }
    public static void beforeClass() throws IOException {
        json = FirstConnectJson.getUuid();
        sid = json.jsonPath().get("sid");
        uuid_header = "OAuth" + " " + json.jsonPath().get("result.uuid");
        uuid = json.jsonPath().get("result.uuid");
        x_auth = "Vertis" + " " + json.jsonPath().get("result.uuid") + " " + "5c27f9e8-2b90-433e-a0bf-b5222bbd97d0";
        autoruuid = json.jsonPath().get("autoruuid");
    }
    public  static Response autorize() {
        RestAssured.baseURI = api;
        Response r =
                given().
                        headers("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8").
                        body("login=" + username + "&pass=" + password + "&sid=" + sid +
                                "&method=users.auth.login&key=1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a" +
                                "&version=2.2.2&uuid=" + uuid + "&format=json").
                        when().post("/rest/");
        auth_sid = r.body().jsonPath().get("sid");
        auth_sid_key = r.body().jsonPath().get("sid_key");
        auth_autoruuid = r.body().jsonPath().get("autoruuid");
        return r;

    }
}
