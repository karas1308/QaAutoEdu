package autoTest.api2;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import methods.RestRequest;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.Parameter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static com.jayway.restassured.RestAssured.given;
import static methods.ArrayContainsSubArray.containsSubArray;
import static methods.Constants.*;
import static methods.Constants.ATV;
import static methods.Constants.SNOWMOBILE;
import static methods.FirstConnect.*;
import static methods.Utils.splitToArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)

public class NodeWithVersionsTest {
    @BeforeClass
    public static void before() throws IOException {
        getUuidSidAuth();

    }

    @Parameter
    private String version;

    @Parameterized.Parameters
    public static Collection<Object[]> version() {
        return Arrays.asList(new Object[][]{
                {"1.0"},
                {"1.1"},
                {"1.2"}});
    }

    public NodeWithVersionsTest(String version) {
        this.version = version;
    }

    @Test //geo suggest
    public void lookingForMoskwaInSuggest() throws IOException {
        String[] msk = {"Москва", "Москва и Московская область"};
        assertThat(splitToArray(new RestRequest().getRequestApi2().parameter("letters", "москв").expect().statusCode(200).get("/" + version + "/suggest").jsonPath()
                .get("data.title").toString()), containsSubArray(msk));
    }

    @Test //10995 - Краснодарский край
    public void NumberRegions10995() throws IOException {
        assertThat(new RestRequest().getRequestApi2().expect().statusCode(200).get("/" + version + "/regions/10995").getBody().asString(), containsString("Анапа"));
        assertThat(splitToArray(new RestRequest().getRequestApi2().expect().statusCode(200).get("/" + version + "/regions/10995").jsonPath().get("name").toString()), containsSubArray(new String[]{"Анапа"}));
    }
    @Test //Избранные
    public void favorites() {
        //autorize();
        RestAssured.baseURI = api2;
        Response r =
                given().
                        headers("X-Authorization", x_auth).
                        when().get("/" + version + "/user/favorites?sid=" + auth_sid);
        assertTrue(r.statusCode() == 200);
        assertTrue(r.body().asString().contains("active"));
    }
    @Test
    public void stat() {
        RestAssured.baseURI = api2;
        Response r =
                given().
                        headers("X-Authorization", x_auth, "Content-Type", "application/json; charset=UTF-8").
                        body("{\"events\":[{\"data\":{\"card_id\":\"1045293948-a9a066\",\"category_id\":\"15\",\"page_type\":\"card\",\"rid\":\"1\"," +
                                "\"user_agent\":\"Android 3.11.0 1963\",\"user_uid\":\"20084971\"},\"event\":\"card_view\"}]}").
                        when().post("/" + version + "/stat");
        assertTrue(r.statusCode() == 200);
        assertTrue(r.body().asString().contains("OK"));
    }
}
