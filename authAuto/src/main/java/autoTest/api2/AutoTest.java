package autoTest.api2;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import methods.RestRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static java.nio.charset.StandardCharsets.UTF_8;
import static methods.Constants.api2;
import static methods.Constants.url_api2_search;
import static methods.FirstConnect.*;
import static methods.Utils.getFile;
import static methods.Utils.prt;
import static methods.Utils.splitToArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static methods.ArrayContainsSubArray.containsSubArray;

public class AutoTest {
    static CloseableHttpClient client = HttpClients.createDefault();

    public void print(String a) {
        System.out.println(a);
    }

    @BeforeClass
    public static void before() throws IOException {
        getUuidSidAuth();

    }


    @Test //Тест проверяет корректность выдачи по фильтру Цена
    public void testFilterPriceApi2Auto() throws IOException {
        int i;
        int[] lines = {0, 50000, 60000, 65000, 70000, 700000, 2000000, 2500000};
        for (i = 0; i < lines.length - 1; i++) {
            Response r = new RestRequest().getRequestApi2Search()
                    .parameters("category_id", "15", "creation_date_to", cutTime, "price_from", lines[i], "price_to", lines[i + 1])
                    .expect().statusCode(200).get("/1.1/search");
            assertThat(Integer.parseInt(r.body().jsonPath().get("pager.count").toString()), greaterThan(0));
            String[] price = splitToArray(r.body().jsonPath().get("list.price.RUR").toString());
            assertThat(price.length, greaterThan(0));
            for (int j = 0; j < price.length; j++) {
                assertThat(Integer.valueOf(price[i].trim()), allOf(greaterThanOrEqualTo(lines[i]), lessThanOrEqualTo(lines[i + 1])));
            }
        }
    }

    @Test //Тест проверяет корректность выдачи по фильтру Год выпуска
    public void yearProd() throws IOException {
        int year_from = 1995;
        int year_to = 1996;
        Response r = new RestRequest().getRequestApi2Search().parameters("year_from", year_from, "year_to", year_to).expect().statusCode(200).get("/1.1/search");
        assertThat(Integer.parseInt(r.body().jsonPath().get("pager.count").toString()), greaterThan(0));
        String[] year = splitToArray(r.body().jsonPath().get("list.year").toString());
        assertThat(year.length, greaterThan(0));
        for (int i = 0; i < year.length; i++) {
            assertThat(Integer.valueOf(year[i].trim()), allOf(greaterThanOrEqualTo(year_from), lessThanOrEqualTo(year_to)));
        }
    }


    @Test
    public void searchSort() {

        RestAssured.baseURI = api2;

        Response searchList = given().header("X-Authorization", x_auth).params("page_num", "1", "page_size", "50", "model_id", "-2", "sort", "price-asc", "category_id", "15").get("/1.1/search");

        String[] priceList = splitToArray(searchList.body().jsonPath().get("list.price.RUR").toString());
        for (int i = 0; i < priceList.length - 1; i++) {
            int a = Integer.valueOf(priceList[i].replace(" ", ""));
            int b = Integer.valueOf(priceList[i + 1].replace(" ", ""));
            assertThat("" + a + " " + b, a <= b);
            //  System.out.println(Integer.valueOf(priceList[i].replace(" ","")));
        }
    }
//-------------------------------------------------Сортировки

    @Test //Сортировка даты размещения по убывани "sort", "cr_date-desc"
    public void searchSortDate() {
        String []  searchSortDate =  splitToArray(new RestRequest().getRequestApi2Search()
                .params("mark_id", "288","rid", "147", "creation_date_to", cutTime, "state", "USED", "sort", "cr_date-desc", "category_id", "15")
                .given().get("/1.1/search").jsonPath().get("list.true_creation_date").toString());
        for (int i = 15; i <  searchSortDate.length - 1; i++) {
            assertThat(Integer.valueOf( searchSortDate[i].trim()),  greaterThanOrEqualTo(Integer.valueOf( searchSortDate[i+1].trim())));
        }
    }

    @Test
    public void searchSortPriceAsc() {
        RestAssured.baseURI = api2;
        Response searchList = given().header("X-Authorization", x_auth).get("/1.2/search?page_num=1&page_size=50&sort=price-asc&category_id=15");
        String[] priceList = searchList.body().jsonPath().get("list.price.RUR").toString().replace("[", "").replace("]", "").split(",");
        prt(searchList.asString());
        for (int i = 0; i < priceList.length - 1; i++) {
            int a = Integer.valueOf(priceList[i].replace(" ", ""));
            int b = Integer.valueOf(priceList[i + 1].replace(" ", ""));
            assertTrue("Сортировка цены по возрастанию" + " " + a + " " + b, a <= b);
        }
    }

    @Test
    public void searchSortPriceDesc() {
        RestAssured.baseURI = api2;
        Response searchList = given().header("X-Authorization", x_auth).get("/1.1/search?page_num=1&page_size=50&mark_id=15&sort=price-desc&category_id=15");
        String[] priceList = searchList.body().jsonPath().get("list.price.RUR").toString().replace("[", "").replace("]", "").split(",");
        for (int i = 0; i < priceList.length - 1; i++) {
            int a = Integer.valueOf(priceList[i].replace(" ", ""));
            int b = Integer.valueOf(priceList[i + 1].replace(" ", ""));
            assertTrue("Сортировка цены по убыванию" + " " + a + " " + b, a >= b);

        }
    }

    @Test
    public void searchSortYearAsc() {
        RestAssured.baseURI = api2;
        Response searchList = given().header("X-Authorization", x_auth).get("/1.1/search?page_num=1&page_size=50&prepend_empty_option=1&model_id=17118" +
                "&creation_date_to=1488459883&mark_id=15&state=USED&sort=year-asc&category_id=15&photo=1&generation_id=17121");
        String[] yearList = searchList.body().jsonPath().get("list.year").toString().replace("[", "").replace("]", "").split(",");
        for (int i = 0; i < yearList.length - 1; i++) {
            int a = Integer.valueOf(yearList[i].replace(" ", ""));
            int b = Integer.valueOf(yearList[i + 1].replace(" ", ""));
            assertTrue("Сортировка года по возрастанию" + a + " " + b, a <= b);
        }
    }

    @Test
    public void searchSortYearDesc() {
        RestAssured.baseURI = api2;
        Response searchList = given().header("X-Authorization", x_auth).get("/1.1/search?page_num=1&page_size=50&prepend_empty_option=1&model_id=17118" +
                "&creation_date_to=1488459883&mark_id=15&state=USED&sort=year-desc&category_id=15&photo=1&generation_id=17121");
        String[] yearList = searchList.body().jsonPath().get("list.year").toString().replace("[", "").replace("]", "").split(",");

        for (int i = 0; i < yearList.length - 1; i++) {
            int a = Integer.valueOf(yearList[i].replace(" ", ""));
            int b = Integer.valueOf(yearList[i + 1].replace(" ", ""));
            assertTrue("Сортировка года по убыванию" + a + " " + b, a >= b);
        }
    }

    @Test
    public void searchSortRun() {
        RestAssured.baseURI = api2;
        Response searchList = given().header("X-Authorization", x_auth).get("/1.1/search?page_num=1&page_size=50&prepend_empty_option=1&model_id=17118" +
                "&creation_date_to=1488459883&mark_id=15&state=USED&sort=km_age-asc&category_id=15&photo=1&generation_id=17121");
        String[] runList = searchList.body().jsonPath().get("list.km_age").toString().replace("[", "").replace("]", "").split(",");

        for (int i = 0; i < runList.length - 1; i++) {
            int a = Integer.valueOf(runList[i].replace(" ", ""));
            int b = Integer.valueOf(runList[i + 1].replace(" ", ""));
            assertTrue("Сортировка по пробегу" + " " + a + " " + b, a <= b);
        }
    }



    //----------------------------------------------------------------
    @Test
    public void readFile() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(getFile("test.txt").getAbsolutePath()));
        for (int i = 0; i < lines.size(); i++) {
            String[] test = lines.get(i).replace("\uFEFF", "").split("/");

            int km_age_from = Integer.parseInt(test[0]);
            int km_age_to = Integer.parseInt(test[1]);
            HttpGet get = new HttpGet(url_api2_search + "&km_age_from=" + km_age_from + "&km_age_to=" + km_age_to);
            get.setHeader("Authorization", uuid_header);
            get.setHeader("X-Authorization", x_auth);
            CloseableHttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            JSONObject jsonTets = new JSONObject(EntityUtils.toString(entity, UTF_8));
            int lenght = jsonTets.getJSONArray("list").length();
            for (int j = 0; j < lenght; j++) {
                String km_ageStr = String.valueOf(jsonTets.getJSONArray("list").getJSONObject(j).get("km_age"));
                int km_age = Integer.valueOf(km_ageStr);
                assertTrue("Пробег выходит за параметры фильтров - " + km_age, km_age >= km_age_from & km_age <= km_age_to);
            }
        }
    }
}


