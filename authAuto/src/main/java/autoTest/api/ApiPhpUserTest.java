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
import static methods.Utils.prt;
import static methods.Utils.splitToArray;
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

    @Test  // all.sale.getArchiveReasons
    public void allSaleGetArchiveReasons() {
        String[] reasons = {"Я продал автомобиль на auto.ru", "Я продал автомобиль где-то еще", "Передумал продавать", "Мало звонков от покупателей", "Другая причина"};
        assertThat(splitToArray(new RestRequest().getRequestAuth().params("method", "all.sale.getArchiveReasons", "key", keyApi, "version", version).expect().statusCode(200).get("/rest/").jsonPath()
                .get("result.name").toString()), arrayContaining(reasons));
    }
    @Test  // allSaleGetEditForm
    public void  allSaleGetEditForm() {
        String saleId = "1049807990-43d2";
        assertThat((new RestRequest().getRequestAuth().params("method", "all.sale.getEditForm", "key", keyApi, "version", version, "sale_id", saleId, "category_id", "15").expect().statusCode(200).get("/rest/").jsonPath()
                .get("result.fields.value").toString()), containsString(saleId) );
    }
}

