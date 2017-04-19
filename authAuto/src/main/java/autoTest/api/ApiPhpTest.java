package autoTest.api;

import static com.jayway.restassured.RestAssured.given;
import static methods.Constants.*;
import static methods.FirstConnect.*;
import static methods.MethodsAddForm.markList;
import static methods.MethodsAddForm.randMarkID;
import static methods.Utils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static methods.Utils.getRndInt;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import methods.RestRequest;

import static methods.ArrayContainsSubArray.containsSubArray;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.Parameter;

@RunWith(value = Parameterized.class)
//@Features("Api PHP")
//@Stories("Some Story")
public class ApiPhpTest {

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
    private String versionApi;

    @Parameter
    private String keyApi;


    public ApiPhpTest(String versionApi, String keyApi) {
        this.versionApi = versionApi;
        this.keyApi = keyApi;
    }

    @Test // sid and uuid
    public void sidIsNotEmpty() {
        assertThat(sid, not(isEmptyString()));
    }

    @Test // sid and uuid
    public void uuidIsNotEmpty() {
        assertThat(uuid, not(isEmptyString()));
    }

    @Test // sid and uuid
    public void sid221IsNotEmpty() {
        assertThat(sid_221, not(isEmptyString()));
    }

    @Test // sid and uuid
    public void uuid221IsNotEmpty() {
        assertThat(uuid_221, not(isEmptyString()));
    }

    @Test // method=api.service.start
    public void serviceStartAppleUrlIsNotEmpty() {
        JsonPath r = new RestRequest().getRequest().baseUri(api).params("method", "api.service.start", "key", keyApi, "version", versionApi).when().get("/rest").jsonPath();
        assertThat(r.get("result.status-api.ios-url").toString(), equalTo("https://itunes.apple.com/ru/app/avto.ru-pokupka-i-prodaza/id507760450?mt=8"));
    }

    @Test // method=api.service.start
    public void serviceStartGoogleUrlIsNotEmpty() {
        JsonPath r = new RestRequest().getRequest().params("method", "api.service.start", "key", keyApi, "version", versionApi).when().get("/rest").jsonPath();
        assertThat(r.get("result.status-api.android-url").toString(), equalTo("https://play.google.com/store/apps/details?id=ru.auto.ara"));
    }

//    @Test // all.sale.search
//    public void allSaleSearch() {
//        String [] markID = splitToArray(new RestRequest().getRequest().parameters("method", "all.mark.getList", "category_id", "15", "key", keyApi, "version", versionApi)
//                .when().expect().statusCode(200).get("/rest").jsonPath().get("result.items.id").toString());
//        int randMarkID = Integer.parseInt(markID[getRndInt(markID.length)]);
//       prt(randMarkID);
//
//        prt(new RestRequest().getRequest().params("mark_id", randMarkID, "method", "all.sale.search", "category_id", "15", "key", keyApi, "version", versionApi).when().get("/rest"));
//        prt(new RestRequest().getRequest().params("mark_id", randMarkID, "method", "all.sale.search", "category_id", "15", "key", keyApi, "version", versionApi).when().get("/rest").jsonPath().get("result.sales.mark.id").toString());
//        prt(new RestRequest().getRequest().params("mark_id", randMarkID, "method", "all.sale.search", "category_id", "15", "key", keyApi, "version", versionApi).when().get("/rest").jsonPath().get("result.sales.mark.name").toString());
//}
    @Test public void allSaleSearchPriceApiAuto() throws IOException {
        int i;
        int[] lines = {0, 50000, 60000, 65000, 70000, 700000, 2000000, 2500000};
        for (i = 0; i < lines.length - 1; i++) {
            Response r = new RestRequest().getRequest()
                    .parameters("category_id", "15","method", "all.sale.search", "page_num", "1", "page_size", "50", "creation_date_to", cutTime, "price[1]", lines[i], "price[2]", lines[i + 1],"key", keyApi, "version", versionApi)
                    .expect().statusCode(200).get("/rest");
            assertThat(Integer.parseInt(r.body().jsonPath().get("result.total").toString()), greaterThan(0));
            String[] price = splitToArray(r.body().jsonPath().get("result.sales.price.RUR").toString().replaceAll(" ","").replace("\u20BD",""));
            assertThat(price.length, greaterThan(0));
            for (int j = 0; j < price.length; j++) {
                assertThat(Integer.valueOf(price[i].trim()), allOf(greaterThanOrEqualTo(lines[i]), lessThanOrEqualTo(lines[i + 1])));
            }
        }
    }

    @Test // my.review, они же отзывы
    public void myReviewTopOpinions() {
        assertThat(new RestRequest().getRequest().parameters("category_id", "15","key",keyApi, "version", versionApi,"method", "my.review.getBlocks")
                .expect().statusCode(200).get("/rest").jsonPath().get("result.top_opinions").toString(), containsString("model_title"));
    }
    @Test // my.review, они же отзывы
    public void myReviewNewOpinions() {
        assertThat(new RestRequest().getRequest().parameters("category_id", "15","key",keyApi, "version", versionApi,"method", "my.review.getBlocks")
                .expect().statusCode(200).get("/rest").jsonPath().get("result.new_opinions").toString(), containsString("model_title"));
    }

    @Test
    public void getPresetsCom() {
        String[] presetsConst = {"Легкие коммерческие", "Седельные тягачи", "Грузовики", "Автобусы", "Прицепы"};
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequest()
                .params("category_id", COMM_CATEGORY, "method", "all.sale.getPresets", "key", keyApi, "version", versionApi)
                .get("/rest").jsonPath().get("result.label").toString())), hasSize(5));
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequest()
                .params("category_id", COMM_CATEGORY, "method", "all.sale.getPresets", "key", keyApi, "version", versionApi)
                .get("/rest").jsonPath().get("result.label").toString())), containsInAnyOrder(presetsConst));
    }

    @Test
    public void getPresetsMoto() {
        String[] presetsConst = {"Мотоциклы", "Скутеры", "Снегоходы", "Мотовездеходы"};
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequest()
                .params("category_id", MOTO_CATEGORY, "method", "all.sale.getPresets", "key", keyApi, "version", versionApi)
                .get("/rest").jsonPath().get("result.label").toString())), hasSize(4));
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequest()
                .params("category_id", MOTO_CATEGORY, "method", "all.sale.getPresets", "key", keyApi, "version", versionApi)
                .get("/rest").jsonPath().get("result.label").toString())), containsInAnyOrder(presetsConst));
    }

    @Test
    public void getPresetsAuto() {
        List<String> presetsConst = new ArrayList<String>(Arrays.asList("Кроссоверы до миллиона", "Новые до 650 тыс.", "Toyota Corolla с пробегом", "Иномарки до 300 тыс.",
                "Внедорожники до 500 тыс.", "На автомате до 400 тыс."));
        String[] presets = splitToArray(new RestRequest().getRequest().baseUri(api).params("category_id", AUTO_CATEGORY, "method", "all.sale.getPresets", "key", keyApi, "version", versionApi).get("/rest").jsonPath()
                .get("result.label").toString());
        assertThat(Arrays.asList(presets), hasSize(4));
        int c = 0;
        for (String string : presets) {
            if (presetsConst.contains(string)) {
                c++;
            }
        }
        assertThat(c, equalTo(4));
    }

    @Test // Жалоба method=all.sale.complain
    public void complain() {
        String text = urlEncode("Тестовая жалоба");
        assertThat(new RestRequest().getRequest().params("section_id", 1, "category_id", 15, "sale_id", 1048427881,
                "text", text, "method", "all.sale.complain", "key", keyApi, "version", versionApi).expect().statusCode(200)
                .post("/rest").jsonPath().get("result.success").toString(), equalTo("true"));
    }

    @Test //Поиск по фото
    public void uploadPhotoRecognize() {
        assertThat(new RestRequest().getRequest().params("method", "all.sale.uploadphotorecognize", "key", keyApi, "version", versionApi).multiPart("files[0]", getFile("mazda3.png"), "application/image")
                .expect().statusCode(200).post("/rest").jsonPath().get("result.recognize.mark.name").toString(), equalTo("[Mazda]"));
    }

    //
    @Test // Список марок
    public void allMarkGetListAutoGroups() {
        assertThat(splitToArray(new RestRequest().getRequest().params("method", "all.mark.getList", "category_id", 15, "key", keyApi, "version", versionApi)
                .expect().statusCode(200).get("/rest").jsonPath()
                .get("result.groups.name").toString()), arrayContainingInAnyOrder(GROUPS_CONST_LIST));
    }
    @Test // Список марок
    public void allMarkGetListAuto() {
      String [] marks =  {"LADA (ВАЗ)", "AC", "Acura", "Adler", "Alfa Romeo", "Alpina", "Aro", "Asia", "Aston Martin", "Audi", "Bentley", "BMW", "Borgward", "Brabus", "Brilliance", "Bugatti", "Buick", "BYD", "Cadillac", "Caterham", "Changan", "ChangFeng", "Chery", "Chevrolet", "Chrysler", "Citroen", "Dacia", "Dadi", "Daewoo", "Daihatsu", "Daimler", "Datsun", "DeLorean", "Derways", "Dodge", "DongFeng", "Doninvest", "DS", "Eagle", "Excalibur", "FAW", "Ferrari", "Fiat", "Ford", "Foton", "Geely", "Genesis", "Geo", "GMC", "Great Wall", "Hafei", "Haima", "Haval", "Hawtai", "Honda", "HuangHai", "Hudson", "Hummer", "Hyundai", "Infiniti", "Iran Khodro", "Isuzu", "JAC", "Jaguar", "Jeep", "JMC", "Kia", "Lamborghini", "Lancia", "Land Rover", "Landwind", "Lexus", "Lifan", "Lincoln", "Lotus", "Luxgen", "Mahindra", "Maserati", "Maybach", "Mazda", "McLaren", "Mercedes-Benz", "Mercury", "MG", "MINI", "Mitsubishi", "Mitsuoka", "Morgan", "Nissan", "Oldsmobile", "Opel", "Peugeot", "Plymouth", "Pontiac", "Porsche", "Proton", "PUCH", "Ravon", "Renault", "Rolls-Royce", "Rover", "Saab", "Saleen", "Saturn", "Scion", "SEAT", "Shanghai Maple", "ShuangHuan", "Skoda", "Smart", "Soueast", "Spyker", "SsangYong", "Subaru", "Suzuki", "TATA", "Tatra", "Tesla", "Tianma", "Tianye", "Toyota", "Trabant", "Ultima", "Volkswagen", "Volvo", "Vortex", "Wanderer", "Wartburg", "Westfield", "Willys", "Xin Kai", "Zotye", "ZX", "Автокам", "Бронто", "ГАЗ", "ЗАЗ", "ЗИЛ", "ЗиС", "ИЖ", "ЛуАЗ", "Москвич", "СМЗ", "ТагАЗ", "УАЗ"};
    assertThat(splitToArray(new RestRequest().getRequest().params("method", "all.mark.getList", "category_id", 15).params("key", keyApi, "version", versionApi)
            .expect().statusCode(200).get("/rest").jsonPath()
                .get("result.items.name").toString()), containsSubArray(marks));
    }

    @Test // Сообщение об ошибке, method=api.service.feedback
    public void apiServiceFeedback() {
        assertThat(given().baseUri(api).headers("Accept-Encoding", "gzip", "Content-Type", "application/x-www-form-urlencoded").params("email", "yuioru@yandex.ru", "method", "api.service.feedback", "sid", sid, "uuid", uuid,
                "key", keyApi, "version", versionApi, "format", "json", "message", "Test").expect().statusCode(200).post("/rest").jsonPath().get("result.message").toString(), equalTo("Спасибо! Ваше сообщение не останется без внимания!"));
    }
}





