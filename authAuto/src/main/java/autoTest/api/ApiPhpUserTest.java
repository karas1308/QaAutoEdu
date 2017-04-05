package autoTest.api;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import methods.RestRequest;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static methods.ApiVersion.API_2_2_2;
import static methods.Constants.api;
import static methods.FirstConnect.*;
import static methods.FirstConnect.key;
import static methods.FirstConnect.uuid;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static methods.Utils.*;

public class ApiPhpUserTest {
    @BeforeClass
    public static void before() {
        getUuidSidAuth();
    }

    @Test // Авторизация пользователя
    public void login() {
        assertThat(new RestRequest().getRequest().contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParams("login", username, "pass", password, "method", "users.auth.login").post("/rest").jsonPath().get("result.sid").toString(), not(containsString(sid)));
    }

    @Test
    public void usersProfileMe() {
        assertThat(new RestRequest().getRequestAuth().params("method", "users.profile.me").expect().statusCode(200).post("/rest").jsonPath()
                .get("result.auth").toString(), equalTo("true"));
    }

    @Test // получаем список телефонов авторизованного пользователя
    public void getPhones() {
        assertThat(new RestRequest().getRequestAuth().
                param("method", "users.profile.getPhones").when().get("/rest").jsonPath()
                .get("result.phone").toString(), containsString(username));
    }

    @Test // Баланс
    public void fetchBalance() {
        assertThat(new RestRequest().getRequestAuth().param("method", "all.service.fetchBalance").expect().statusCode(200).get("/rest/").jsonPath()
                .get("result").toString(), greaterThan("1"));
    }

    @Test // Ошибка авторизации пользователя
    public void loginFail() {
        assertThat(new RestRequest().getRequest().contentType("application/x-www-form-urlencoded; charset=UTF-8").formParams("method", "users.auth.login", "login", username, "pass", "123").expect().statusCode(200).post("/rest/").jsonPath()
                .get("error.message").toString(), equalTo("Неверный логин или пароль."));
    }

    @Test // logout
    public void logout() {
        JsonPath r = given().baseUri(api).contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParams("login", username, "pass", password, "sid", sid, "method", "users.auth.login", "key", key, "version", "2.2.2", "uuid", uuid, "format", "json").when()
                .post("/rest").body().jsonPath();
        String auth_sid_for_logout = r.get("sid");
        String auth_sid_key_for_logout = r.get("sid_key");
        String auth_autoruuid_for_logout = r.get("autoruuid");
        String method = "users.auth.logout";
        Response out = given().baseUri(api).header("Accept-Encoding", "gzip")
                .get("/rest/?sid=" + auth_sid_for_logout + "&method=" + method + "&key=" + key +
                        "&version=2.2.2&uuid=" + uuid + "&format=json");
        assertTrue("result.success = " + out.jsonPath().get("result").toString(),
                Integer.valueOf(out.jsonPath().get("result.success").toString()) == 1);
    }
}
