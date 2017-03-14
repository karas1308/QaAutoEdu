package autoTest.exp;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.hamcrest.core.Every;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static autoTest.FirstConnectJson.*;
import static autoTest.FirstConnectJson.beforeClass;
import static autoTest.FirstConnectJson.sid;
import static autoTest.FirstConnectJson.uuid;
import static com.jayway.restassured.RestAssured.given;
import static methods.Utils.replaceSome;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)

public class CommNewTest {

    @BeforeClass
    public static void before() throws IOException {
        beforeClass();
    }

    private Integer id;

    @Parameterized.Parameters
    public static Collection categoryId() {
        return Arrays.asList(new Object[][] { { 31 }, { 33 }, { 32 }, { 34 }, { 16 } });
    }

    public CommNewTest(Integer id) {
        this.id = id;

    }

    @Test
    public void markListCom() throws IOException {
//        String method = "all.mark.getList";
        String[] groups_name_const = { "Любая марка", "Отечественные", "Иномарки" };
        String[] mark_name_const = { "BAW", "Changan", "Chevrolet", "Citroen", "DFSK", "Dodge", "FAW", "Fiat", "Ford", "Foton", "Freightliner", "Hyundai", "IFA", "Isuzu", "IVECO",
                "JAC", "JBC", "JMC", "Kia", "LDV", "Mazda", "Mercedes-Benz", "Mitsubishi", "Multicar", "Nissan", "Opel", "Peugeot", "Piaggio", "Renault", "Skoda", "Ssang Yong",
                "Toyota", "Volkswagen", "YueJin", "Богдан", "ВАЗ", "ВИС", "ГАЗ", "ГАЗ-САЗ", "ИЖ", "РАФ", "ТагАЗ", "УАЗ" };
//        RestAssured.baseURI = api;
        Response r = new RestRequest().getRequest().baseUri(api).parameters("category_id", 31, "method", "all.mark.getList").when().get("/rest");
//        Response r = given().header("Accept-Encoding", "gzip")
//                .get("/rest/?method=" + method + "&category_id=31" + "&sid=" + sid + "&key=" + key + "&version=2.2.2&uuid=" + uuid + "&format=json");
//        String groups_name = r.body().jsonPath().get("result.groups.name").toString();
        for (int i = 0; i < groups_name_const.length; i++) {
            assertTrue("Where is group " + groups_name_const[i], r.body().jsonPath().get("result.groups.name").toString().contains(groups_name_const[i]));
        }
        for (int i = 0; i < mark_name_const.length; i++) {
            assertTrue("Where is mark " + mark_name_const[i], r.body().jsonPath().get("result.items.name").toString().contains(mark_name_const[i]));
        }
    }

    @Test // geo_id = "213" МСК
    public void filterGeoCityCountTotalCom() throws IOException {
        String geo_id = "213";
        String method = "all.sale.countTotal";
        RestAssured.baseURI = api;
        Response r = given().header("Accept-Encoding", "gzip").get("rest/?category_id=" + id + "&geo_id=" + geo_id + "&photo=1&prepend_empty_option=1&used_key=5&sid=" + sid + ""
                + "&method=" + method + "&key=" + key + "&version=2.2.2&uuid=" + uuid + "&format=json");
        assertTrue("countTotal = 0", Integer.valueOf(r.jsonPath().get("result.total_found").toString()) > 0);
    }

    @Test // geo_id=1 - МО
    public void filterGeoRegCountTotalCom() throws IOException {
        String geo_id = "1";
        String method = "all.sale.countTotal";
        RestAssured.baseURI = api;
        Response r = given().header("Accept-Encoding", "gzip").get("rest/?category_id=" + id + "&geo_id=" + geo_id + "&photo=1&prepend_empty_option=1&used_key=5&sid=" + sid + ""
                + "&method=" + method + "&key=" + key + "&version=2.2.2&uuid=" + uuid + "&format=json");
        assertTrue("countTotal = 0", Integer.valueOf(r.jsonPath().get("result.total_found").toString()) > 0);
    }

    @Test // geo_id = "213" МСК
    public void filterGeoCitySearchCom() throws IOException {
        String geo_id = "213";
        String method = "all.sale.search";
        RestAssured.baseURI = api;
        Response r = given().header("Accept-Encoding", "gzip").get("/rest/?offset=0&category_id=" + id + "&creation_date_to=" + millis + "&geo_id=" + geo_id + "&limit=20&photo=1"
                + "&prepend_empty_option=1&sort[price]=asc&used_key=5&sid=" + sid + "&method=" + method + "&key=" + key + "" + "&version=2.2.2&uuid=" + uuid + "&format=json");
        // print(r.jsonPath().get("result.sales.poi.region").toString());
        // print(r.jsonPath().get("result").toString());
        String[] city_search = replaceSome(r.jsonPath().get("result.sales.poi.region").toString());
        assertThat(Arrays.asList(city_search), Every.everyItem(anyOf(equalToIgnoringWhiteSpace("Москва"))));
    }

    @Test // geo_id=1 - МО
    public void filterGeoRegSearchCom() throws IOException {
        String geo_id = "1";
        String method = "all.sale.search";
        RestAssured.baseURI = api;
        Response r = given().header("Accept-Encoding", "gzip").get("/rest/?offset=0&category_id=" + id + "&creation_date_to=" + millis + "&geo_id=" + geo_id + "&limit=20&photo=1"
                + "&prepend_empty_option=1&sort[price]=asc&used_key=5&sid=" + sid + "&method=" + method + "&key=" + key + "" + "&version=2.2.2&uuid=" + uuid + "&format=json");
        // print(r.jsonPath().get("result.sales.poi.region").toString());
        // print(r.asString());
        String[] city_search = replaceSome(r.jsonPath().get("result.sales.poi.region").toString());
        assertThat(Arrays.asList(city_search), Every.everyItem(anyOf(equalToIgnoringWhiteSpace("Москва"), (equalToIgnoringWhiteSpace("Московская обл.")))));
    }
}
