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
import static methods.Utils.splitToArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;

public class AutoTest {
    static CloseableHttpClient client = HttpClients.createDefault();

public void print(String a){
    System.out.println(a);
}
    @BeforeClass
    public static void before() throws IOException {
        getUuidSidAuth();

    }

    @Test //geo suggest
    public void lookingForMoskwaInSuggest() throws IOException {
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequestApi2().parameter("letters","москв").expect().statusCode(200).get("/1.1/suggest").jsonPath()
                .get("data.title").toString())), hasSize(greaterThan(0)));
//System.out.println(new RestRequest().getRequestApi2().parameter("letters","москв").expect().statusCode(200).get("/1.1/suggest").jsonPath()
//        .get("data.title").toString());
    }

    @Test
    public void NumberRegions10841() throws IOException {

        HttpGet get = new HttpGet(api2+"/1.1/regions/10841");
        get.setHeader("Authorization", uuid_header);
        get.setHeader("X-Authorization", x_auth);
        get.setHeader("Accept-Encoding", "gzip, deflate, identity");
        get.setHeader("Accept-Language", "en-RU;q=1, ru-RU;q=0.9, uk-RU;q=0.8, it-RU;q=0.7, de-RU;q=0.6");
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONArray regions = new JSONArray(EntityUtils.toString(entity, UTF_8));
        System.out.println(regions.get(1));

    }

    @Test //Тест проверяет корректность выдачи по фильтру Цена
    public void testPrice() throws IOException {
        int i;
        int[] lines = {0 , 50000, 60000, 65000, 70000, 700000, 2000000, 2500000};
        for (i = 0; i < lines.length - 1; i++) {
            RestAssured.baseURI = api2;
            Response r =
                    given().headers("Authorization", uuid_header, "X-Authorization", x_auth).
                            get("/1.1/search?category_id=15&page_num=1&page_size=50&creation_date_to=" + cutTime + "&price_from=" + lines[i] + "&price_to=" + lines[i + 1]);
            assertTrue(r.statusCode() == 200);
            int pager_count = Integer.parseInt(r.body().jsonPath().get("pager.count").toString());
            String[] price = splitToArray(r.body().jsonPath().get("list.price.RUR").toString());
            assertTrue("tt", price.length > 0);
            if (pager_count > 0) {
                for (int i1 = 0; i1 < price.length; i1++) {
                    int minprice = lines[i];
                    int maxprice = lines[i + 1];
                    int a = Integer.valueOf(price[i].trim());
                    assertTrue("Цена выходит за параметры фильтров - " + a, a >= minprice & a <= maxprice);
                }
            }else { assertTrue("Number of ads is zero, correct your filter. "+ lines[i], pager_count > 0);}
        }
    }

    @Test //Тест проверяет корректность выдачи по фильтру Год выпуска
    public void yearProd() throws IOException {
        int year_from = 1995;
        int year_to = 1996;
        HttpGet get = new HttpGet(url_api2_search + "&year_from=" + year_from + "&year_to=" + year_to);
        get.setHeader("Authorization", uuid_header);
        get.setHeader("X-Authorization", x_auth);
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONObject jsonTets = new JSONObject(EntityUtils.toString(entity, UTF_8));
        int lenght = jsonTets.getJSONArray("list").length();
        for (int i = 0; i < lenght; i++) {
            String yearStr = String.valueOf(jsonTets.getJSONArray("list").getJSONObject(i).get("year"));
            int year = Integer.valueOf(yearStr);
            assertTrue("Год выпуска выходит за параметры фильтров - " + year, year >= year_from & year <= year_to);
        }
    }

    @Test
    public void stat() {
        RestAssured.baseURI = api2;
        Response r =
                given().
                        headers("X-Authorization", x_auth, "Content-Type", "application/json; charset=UTF-8").
                        body("{\"events\":[{\"data\":{\"card_id\":\"1045293948-a9a066\",\"category_id\":\"15\",\"page_type\":\"card\",\"rid\":\"1\"," +
                                "\"user_agent\":\"Android 3.11.0 1963\",\"user_uid\":\"20084971\"},\"event\":\"card_view\"}]}").
                        when().post("/1.1/stat");
        assertTrue(r.statusCode() == 200);
        assertTrue(r.body().asString().contains("OK"));
    }

    @Test //Избранные
    public void favorites() {
        //autorize();
        RestAssured.baseURI = api2;
        Response r =
                given().
                        headers("X-Authorization", x_auth).
                        when().get("/1.1/user/favorites?sid=" + auth_sid);
        assertTrue(r.statusCode() == 200);
        assertTrue(r.body().asString().contains("active"));
    }

    @Test
    public void searchSort() {

        RestAssured.baseURI = api2;

        Response searchList = given().header("X-Authorization", x_auth).get("/1.1/search?page_num=1&page_size=50&prepend_empty_option=1&model_id=17118&creation_date_to=1488459883&mark_id=15&state=USED&sort=price-asc&category_id=15&photo=1&generation_id=17121");

        String[] priceList = splitToArray(searchList.body().jsonPath().get("list.price.RUR").toString());
        for (int i = 0; i < priceList.length - 1; i++) {
            int a = Integer.valueOf(priceList[i].replace(" ", ""));
            int b = Integer.valueOf(priceList[i + 1].replace(" ", ""));
            assertTrue("" + a + " " + b, a <= b);
            //  System.out.println(Integer.valueOf(priceList[i].replace(" ","")));
        }
    }
//-------------------------------------------------Сортировки

    @Test
    public void searchSortPriceAsc() {
        RestAssured.baseURI = api2;
        Response searchList = given().header("X-Authorization", x_auth).get("/1.1/search?page_num=1&page_size=50&prepend_empty_option=1&model_id=17118"+
                "&creation_date_to=1488459883&mark_id=15&state=USED&sort=price-asc&category_id=15&photo=1&generation_id=17121");
        String[] priceList = searchList.body().jsonPath().get("list.price.RUR").toString().replace("[", "").replace("]", "").split(",");
        for (int i = 0; i < priceList.length - 1; i++) {
            int a = Integer.valueOf(priceList[i].replace(" ", ""));
            int b = Integer.valueOf(priceList[i + 1].replace(" ", ""));
            assertTrue("Сортировка цены по возрастанию"+ " " + a + " " + b, a <= b);
        }
    }

    @Test
    public void searchSortPriceDesc() {
        RestAssured.baseURI = api2;
        Response searchList = given().header("X-Authorization", x_auth).get("/1.1/search?page_num=1&page_size=50&prepend_empty_option=1&model_id=17118"+
                "&creation_date_to=1488459883&mark_id=15&state=USED&sort=price-desc&category_id=15&photo=1&generation_id=17121");
        String[] priceList = searchList.body().jsonPath().get("list.price.RUR").toString().replace("[", "").replace("]", "").split(",");
        for (int i = 0; i < priceList.length - 1; i++) {
            int a = Integer.valueOf(priceList[i].replace(" ", ""));
            int b = Integer.valueOf(priceList[i + 1].replace(" ", ""));
            assertTrue("Сортировка цены по убыванию"+ " " + a + " " + b, a >= b);

        }
    }

    @Test
    public void searchSortYearAsc() {
        RestAssured.baseURI = api2;
        Response searchList = given().header("X-Authorization", x_auth).get("/1.1/search?page_num=1&page_size=50&prepend_empty_option=1&model_id=17118"+
                "&creation_date_to=1488459883&mark_id=15&state=USED&sort=year-asc&category_id=15&photo=1&generation_id=17121");
        String[] yearList = searchList.body().jsonPath().get("list.year").toString().replace("[", "").replace("]", "").split(",");
        for (int i = 0; i < yearList.length - 1; i++) {
            int a = Integer.valueOf(yearList[i].replace(" ", ""));
            int b = Integer.valueOf(yearList[i + 1].replace(" ", ""));
            assertTrue("Сортировка года по возрастанию"+ a + " " + b, a <= b);
        }
    }

    @Test
    public void searchSortYearDesc() {
        RestAssured.baseURI = api2;
        Response searchList = given().header("X-Authorization", x_auth).get("/1.1/search?page_num=1&page_size=50&prepend_empty_option=1&model_id=17118"+
                "&creation_date_to=1488459883&mark_id=15&state=USED&sort=year-desc&category_id=15&photo=1&generation_id=17121");
        String[] yearList = searchList.body().jsonPath().get("list.year").toString().replace("[", "").replace("]", "").split(",");

        for (int i = 0; i < yearList.length - 1; i++) {
            int a = Integer.valueOf(yearList[i].replace(" ", ""));
            int b = Integer.valueOf(yearList[i + 1].replace(" ", ""));
            assertTrue("Сортировка года по убыванию"+ a + " " + b, a >= b);
        }
    }

    @Test
    public void searchSortRun() {
        RestAssured.baseURI = api2;
        Response searchList = given().header("X-Authorization", x_auth).get("/1.1/search?page_num=1&page_size=50&prepend_empty_option=1&model_id=17118"+
                "&creation_date_to=1488459883&mark_id=15&state=USED&sort=km_age-asc&category_id=15&photo=1&generation_id=17121");
        String[] runList = searchList.body().jsonPath().get("list.km_age").toString().replace("[", "").replace("]", "").split(",");

        for (int i = 0; i < runList.length - 1; i++) {
            int a = Integer.valueOf(runList[i].replace(" ", ""));
            int b = Integer.valueOf(runList[i + 1].replace(" ", ""));
            assertTrue("Сортировка по пробегу"+ " "+ a + " " + b, a <= b);
        }
    }

    @Test
    public void searchSortDate() {
        RestAssured.baseURI = api2;
        Response searchList = given().header("X-Authorization", x_auth).get("/1.1/search?page_num=1&page_size=50&prepend_empty_option=1&model_id=17118"+
                "&creation_date_to=1488459883&mark_id=15&state=USED&sort=cr_date-desc&category_id=15&photo=1&generation_id=17121");
        String[] upDateList = searchList.body().jsonPath().get("list.true_creation_date").toString().replace("[", "").replace("]", "").split(",");
        for (int i = 8; i < upDateList.length - 1; i++) {

            int a = Integer.valueOf(upDateList[i].replace(" ", ""));
            int b = Integer.valueOf(upDateList[i + 1].replace(" ", ""));
            assertTrue("Сортировка даты размещения по убыванию"+ " " + a + " " + b, a >= b);
        }

    }

//----------------------------------------------------------------
    @Test
    public void readFile() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(getFile("test.txt").getAbsolutePath()));
        int linesSize = lines.size();
        for (int i = 0; i < linesSize; i++) {
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


