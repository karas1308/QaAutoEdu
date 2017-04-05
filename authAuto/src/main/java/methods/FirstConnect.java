package methods;

import static com.jayway.restassured.RestAssured.given;
import static methods.Constants.api;

import java.util.concurrent.TimeUnit;

import com.jayway.restassured.path.json.JsonPath;

public class FirstConnect {


    public static String sid;
    public static String uuid_header;
    public static String uuid;
    public static String autoruuid;
    public static String x_auth;
    public static String auth_sid;
    public static String auth_sid_key;
    public static String auth_autoruuid;
//    public static long cutTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    public static String cutTime = "";
    public static String username = "79854406469";
//     public static String password = "autoru";
    public static String password = "qqqqqq";
    public static String key = "1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a";

    public static void getUuidSidAuth() {
        JsonPath json = given().baseUri(api).headers("Accept-Encoding", "gzip").params("method", "api.service.getUuid", "key", key, "version", "2.2.2", "format", "json")
                .get("/rest").jsonPath();
        sid = json.get("sid");
        uuid_header = "OAuth" + " " + json.get("result.uuid");
        uuid = json.get("result.uuid");
        x_auth = "Vertis" + " " + json.get("result.uuid") + " " + "5c27f9e8-2b90-433e-a0bf-b5222bbd97d0";
        autoruuid = json.get("autoruuid");
        autorize();
    }

    public static void autorize() {
        JsonPath r = given().baseUri(api).contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParams("login", username, "pass", password, "sid", sid, "method", "users.auth.login", "key", key, "version", "2.2.2", "uuid", uuid, "format", "json").when()
                .post("/rest").body().jsonPath();
        auth_sid = r.get("sid");
        auth_sid_key = r.get("sid_key");
        auth_autoruuid = r.get("autoruuid");
    }

    public void logout() {
        given().baseUri(api).header("Accept-Encoding", "gzip")
                .params("sid", auth_sid, "method", "users.auth.logout", "key", key, "version", "2.2.2", "uuid", uuid, "format", "json").get("/rest");
    }

}