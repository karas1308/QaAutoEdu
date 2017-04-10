package autoTest.api;

import methods.RestRequest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.Parameter;

import java.util.Arrays;
import java.util.Collection;

import static com.jayway.restassured.RestAssured.given;
import static methods.Constants.api;
import static methods.FirstConnect.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class ApiPhpUserTest {
    @BeforeClass
    public static void before() {
        getUuidSidAuth();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testVersionApi() {
        return Arrays.asList(
                new Object[][]{
                        {"2.2.2", keyAPI222},
                        {"2.2.1", keyAPI221}

                }
        );
    }

    @Parameter
    private String version;

    @Parameter
    private String keyApi;


    public ApiPhpUserTest(String version, String keyApi) {
        this.version = version;
        this.keyApi = keyApi;
    }

    @Test // Авторизация пользователя
    public void login() {
        assertThat(new RestRequest().getRequest().contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParams("login", username, "pass", password, "method", "users.auth.login", "key", keyApi, "version", version).post("/rest").jsonPath().get("result.sid").toString(), not(containsString(sid)));
    }

    @Test
    public void usersProfileMe() {
        assertThat(new RestRequest().getRequestAuth().params("method", "users.profile.me", "key", keyApi, "version", version).expect().statusCode(200).post("/rest").jsonPath()
                .get("result.auth").toString(), equalTo("true"));
    }

    @Test // получаем список телефонов авторизованного пользователя
    public void getPhones() {
        assertThat(new RestRequest().getRequestAuth().
                params("method", "users.profile.getPhones", "key", keyApi, "version", version).when().get("/rest").jsonPath()
                .get("result.phone").toString(), containsString(username));
    }

    @Test // Баланс
    public void fetchBalance() {
        assertThat(new RestRequest().getRequestAuth().params("method", "all.service.fetchBalance", "key", keyApi, "version", version).expect().statusCode(200).get("/rest/").jsonPath()
                .get("result").toString(), greaterThan("1"));
    }

    @Test // Ошибка авторизации пользователя
    public void loginFailT() {
        assertThat(new RestRequest().getRequest().contentType("application/x-www-form-urlencoded; charset=UTF-8").formParams("key", keyApi, "version", version, "method", "users.auth.login", "login", username, "pass", "123").expect().statusCode(200).post("/rest/").jsonPath()
                .get("error.message").toString(), equalTo("Неверный логин или пароль."));
    }

    @Test // logout
    public void logoutTest() {
        String auth_sid = new RestRequest().getRequest().contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParams("login", username, "pass", password, "method", "users.auth.login", "key", keyApi, "version", version).post("/rest").jsonPath().get("sid").toString();
        assertThat(given().baseUri(api).contentType("application/x-www-form-urlencoded; charset=UTF-8").formParams("sid", auth_sid, "uuid", uuid, "format", "json", "key", keyApi, "version", version, "method", "users.auth.logout").expect().statusCode(200).post("/rest/").jsonPath()
                .get("result.success").toString(), equalTo("1"));

    }

}

//    @Test // logout
//    public void logout() {
//        JsonPath r = given().baseUri(api).contentType("application/x-www-form-urlencoded; charset=UTF-8")
//                .formParams("login", username, "pass", password, "sid", sid, "method", "users.auth.login", "key", key, "version", "2.2.2", "uuid", uuid, "format", "json").when()
//                .post("/rest").body().jsonPath();
//        String auth_sid_for_logout = r.get("sid");
//        String auth_sid_key_for_logout = r.get("sid_key");
//        String auth_autoruuid_for_logout = r.get("autoruuid");
//        String method = "users.auth.logout";
//        Response out = given().baseUri(api).header("Accept-Encoding", "gzip")
//                .get("/rest/?sid=" + auth_sid_for_logout + "&method=" + method + "&key=" + key +
//                        "&version=2.2.2&uuid=" + uuid + "&format=json");
//        assertTrue("result.success = " + out.jsonPath().get("result").toString(),
//                Integer.valueOf(out.jsonPath().get("result.success").toString()) == 1);
//    }