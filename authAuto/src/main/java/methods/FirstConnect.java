package methods;

import static com.jayway.restassured.RestAssured.given;
import static methods.Constants.api;

import com.jayway.restassured.path.json.JsonPath;

public class FirstConnect {


    public static String sid;
    public static String uuid_header; //Authorization
    public static String uuid;
    public static String autoruuid;
    public static String x_auth; //"X-Authorization"
    public static String auth_sid;
    public static String auth_sid_key;
    public static String auth_autoruuid;
    public static String sid_221;
    public static String uuid_header_221; //Authorization
    public static String uuid_221;
    public static String autoruuid_221;
    public static String x_auth_221; //"X-Authorization"
    public static String auth_sid_221;
    public static String auth_sid_key_221;
    public static String auth_autoruuid_221;
//    public static long cutTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    public static String cutTime = "";
    public static String username = "79854406469";
     public static String password = "autoru";
//    public static String password = "111111";
    public static String keyAPI222 = "1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a";
    public static String keyAPI221 = "707e24ee14748fc796bcfb33e149424071b79cebc8537cdf0c24929403f0bc79";

    public static void getUuidSidAuth() {
        getUuid222();
        autorize222();
        getUuid221();
//        autorize221();
    }

    public static void autorize222() {
        JsonPath r = given().baseUri(api).contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParams("login", username, "pass", password, "sid", sid, "method", "users.auth.login", "key", keyAPI222, "version", "2.2.2", "uuid", uuid, "format", "json").when()
                .post("/rest").body().jsonPath();
        auth_sid = r.get("sid");
        auth_sid_key = r.get("sid_key");
        auth_autoruuid = r.get("autoruuid");
    }

    public static void getUuid222() {
        JsonPath json = given().baseUri(api).headers("Accept-Encoding", "gzip").params("method", "api.service.getUuid", "key", keyAPI222, "version", "2.2.2", "format", "json")
                .get("/rest").jsonPath();
        sid = json.get("sid");
        uuid_header = "OAuth" + " " + json.get("result.uuid");
        uuid = json.get("result.uuid");
        x_auth = "Vertis" + " " + json.get("result.uuid") + " " + "5c27f9e8-2b90-433e-a0bf-b5222bbd97d0";
        autoruuid = json.get("autoruuid");

    }
    public static void getUuid221() {
        JsonPath json = given().baseUri(api).headers("Accept-Encoding", "gzip").params("method", "api.service.getUuid", "key", keyAPI221, "version", "2.2.1", "format", "json")
                .get("/rest").jsonPath();
        sid_221 = json.get("sid");
        uuid_header_221 = "OAuth" + " " + json.get("result.uuid");
        uuid_221 = json.get("result.uuid");
        x_auth_221 = "Vertis" + " " + json.get("result.uuid") + " " + "5c27f9e8-2b90-433e-a0bf-b5222bbd97d0";
        autoruuid_221 = json.get("autoruuid");
    }
//    public static void autorize221() {
//        JsonPath r = given().baseUri(api).contentType("application/x-www-form-urlencoded; charset=UTF-8")
//                .formParams("login", username, "pass", password, "sid", sid, "method", "users.auth.login", "uuid", uuid, "format", "json").when()
//                .post("/rest").body().jsonPath();
//        auth_sid_221 = r.get("sid");
//        auth_sid_key_221 = r.get("sid_key");
//        auth_autoruuid_221 = r.get("autoruuid");
//    }
}