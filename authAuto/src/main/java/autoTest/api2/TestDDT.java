package autoTest.api2;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import ru.yandex.qatools.allure.annotations.Parameter;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;


import static methods.FirstConnect.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)

public class TestDDT {
  //  public static String url_api2 = "https://api2.auto.ru/1.1/search?category_id=15&page_num=1&page_size=50&creation_date_to=" + millis;

    static CloseableHttpClient client = HttpClients.createDefault();


    @BeforeClass
    public static void before() throws IOException {
        getUuidSidAuth();
    }
    
    @Parameterized.Parameters
    public static Collection<Object[]> testMark_Rig() {
        return Arrays.asList(
                new Object[][]{
                        {213, 15, 10000, 20000},
                        {1, -1, 10000, 30000},
                        {213, 15, 10000, 40000}
                }
        );
    }

    @Parameter
    private int rig;
    
    @Parameter
    private int mark;
    
    @Parameter
    private int km_age_from;
    
    @Parameter
    private int km_age_to;


    public TestDDT(int rig, int mark, int km_age_from, int km_age_to) {
        this.rig = rig;
        this.mark = mark;
        this.km_age_from = km_age_from;
        this.km_age_to = km_age_to;
    }

    @Test //Тест проверяет корректность выдачи по фильтру Пробег
    public void km_age() throws IOException {
        RestAssured.baseURI = AutoTest.api2;
        Response r =
                given().
                        headers("Accept-Encoding", "gzip", "Authorization", uuid, "X-Authorization", x_auth).
                        get("/1.1/search?category_id=15&page_num=1&page_size=50&creation_date_to=" + millis + "&km_age_from=" + km_age_from + "&km_age_to=" + km_age_to);
        assertTrue(r.statusCode() == 200);
        String[] km_age = r.body().jsonPath().get("list.km_age").toString().replace("[", "").replace("]", "").split(",");
        for (int i = 0; i < km_age.length; i++) {
            int a = Integer.valueOf(km_age[i].replace(" ", ""));

            assertTrue("" + a, a <= km_age_to && a >= km_age_from);
        }
        //  System.out.println(r.body().jsonPath().get("list.km_age").toString());

    }

//    @Test
//    public void test() throws IOException {
//
//        HttpGet get = new HttpGet("https://api2.auto.ru/1.1/search?page_num=1&page_size=20&prepend_empty_option=1&state=USED&sort=fresh_relevance_1-desc&category_id=15&photo=1&rid=" + rig + "&mark_id=" + mark + "&creation_date_to=" + millis);
//        get.setHeader("Authorization", uuid);
//        get.setHeader("X-Authorization", x_auth);
//        // get.setHeader("Content-Type", "application/json");
//        CloseableHttpResponse response = client.execute(get);
//        HttpEntity entity = response.getEntity();
//        JSONObject test = new JSONObject(EntityUtils.toString(entity, UTF_8));
//
//        System.out.println(test.getJSONArray("list").getJSONObject(0).get("mark"));
//        System.out.println(test.getJSONArray("list").getJSONObject(0).get("model"));
//        System.out.println(test.getJSONArray("list").getJSONObject(0).get("seller"));
//        System.out.println(test.getJSONArray("list").getJSONObject(0).get("price"));
//        // System.out.println(get.getURI());
////        "Accept-Encoding", "gzip, deflate, identity");
////        get.setHeader("Accept-Language",
//
//    }
}


//    HttpGet get = new HttpGet(url_api2 + "&km_age_from=" + km_age_from + "&km_age_to=" + km_age_to);
//        get.setHeader("Authorization", uuid);
//        get.setHeader("X-Authorization", x_auth);
//        CloseableHttpResponse response = client.execute(get);
//        HttpEntity entity = response.getEntity();
//        JSONObject jsonTets = new JSONObject(EntityUtils.toString(entity, UTF_8));
//        int lenght = jsonTets.getJSONArray("list").length();
//        for (int i = 0; i < lenght; i++) {
//            String km_ageStr = String.valueOf(jsonTets.getJSONArray("list").getJSONObject(i).get("km_age"));
//            int km_age = Integer.valueOf(km_ageStr);
//            assertTrue("Пробег выходит за параметры фильтров - " + km_age, km_age >= km_age_from & km_age <= km_age_to);
//        }