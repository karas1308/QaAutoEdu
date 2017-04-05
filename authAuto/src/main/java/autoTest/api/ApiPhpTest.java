package autoTest.api;

import static com.jayway.restassured.RestAssured.given;
import static methods.ApiVersion.API_2_2_2;
import static methods.Constants.*;
import static methods.FirstConnect.auth_sid;
import static methods.FirstConnect.getUuidSidAuth;
import static methods.FirstConnect.key;
import static methods.FirstConnect.password;
import static methods.FirstConnect.sid;
import static methods.FirstConnect.username;
import static methods.FirstConnect.uuid;
import static methods.MethodsAddForm.markList;
import static methods.MethodsAddForm.modelList;
import static methods.Utils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import methods.RestRequest;

import static methods.ArrayContainsSubArray.containsSubArray;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Api PHP")
@Stories("Some Story")
public class ApiPhpTest {

    @BeforeClass
    public static void before() {
        getUuidSidAuth();
    }

    @Test // sid and uuid
    public void sidIsNotEmpty() {
        assertThat(sid, not(isEmptyString()));
    }

    @Test // sid and uuid
    public void uuidIsNotEmpty() {
        assertThat(uuid, not(isEmptyString()));
    }

    @Test // method=api.service.start
    public void serviceStartAppleUrlIsNotEmpty() {
        JsonPath r = new RestRequest().getRequest().baseUri(api).params("method", "api.service.start").when().get("/rest").jsonPath();
        assertThat(r.get("result.status-api.ios-url").toString(), equalTo("https://itunes.apple.com/ru/app/avto.ru-pokupka-i-prodaza/id507760450?mt=8"));
    }

    @Test // method=api.service.start
    public void serviceStartGoogleUrlIsNotEmpty() {
        JsonPath r = new RestRequest().getRequest().baseUri(api).params("method", "api.service.start").when().get("/rest").jsonPath();
        assertThat(r.get("result.status-api.android-url").toString(), equalTo("https://play.google.com/store/apps/details?id=ru.auto.ara"));
    }





    @Test // my.review, они же отзывы
    public void myReview() {
        RestAssured.baseURI = api;
        Response r = given().header("Accept-Encoding", "gzip").when().get("/rest/?category_id=15&sid=" + sid
                + "&method=my.review.getBlocks&key=" + key + "&version=2.2.2&uuid=" + uuid + "&format=json");
        assertTrue("statusCode = " + r.statusCode(), r.statusCode() == 200);
        String test = r.body().jsonPath().get("result").toString();
        System.out.println(r.body().jsonPath().get("result.new_opinions.id").toString());
        System.out.println(test);
        System.out.println(test.contains("current_search"));
    }


    @Test
    public void getPresetsCom() {
        String[] presetsConst = {"Легкие коммерческие", "Седельные тягачи", "Грузовики", "Автобусы", "Прицепы"};
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequest()
                .params("category_id", COMM_CATEGORY, "method", "all.sale.getPresets")
                .get("/rest").jsonPath().get("result.label").toString())), hasSize(5));
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequest()
                .params("category_id", COMM_CATEGORY, "method", "all.sale.getPresets")
                .get("/rest").jsonPath().get("result.label").toString())), containsInAnyOrder(presetsConst));
    }

    @Test
    public void getPresetsMoto() {
        String[] presetsConst = {"Мотоциклы", "Скутеры", "Снегоходы", "Мотовездеходы"};
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequest()
                .params("category_id", MOTO_CATEGORY, "method", "all.sale.getPresets")
                .get("/rest").jsonPath().get("result.label").toString())), hasSize(4));
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequest()
                .params("category_id", MOTO_CATEGORY, "method", "all.sale.getPresets")
                .get("/rest").jsonPath().get("result.label").toString())), containsInAnyOrder(presetsConst));
    }

    @Test
    public void getPresetsAuto() {
        List<String> presetsConst = new ArrayList<String>(Arrays.asList("Кроссоверы до миллиона", "Новые до 650 тыс.", "Toyota Corolla с пробегом", "Иномарки до 300 тыс.",
                "Внедорожники до 500 тыс.", "На автомате до 400 тыс."));
        String[] presets = splitToArray(new RestRequest().getRequest().baseUri(api).params("category_id", AUTO_CATEGORY, "method", "all.sale.getPresets").get("/rest").jsonPath()
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
        String text = urlEncode("Неверная модель/поколение");
        assertThat(new RestRequest().getRequest().params("section_id", 1, "category_id", 15, "sale_id", 1048427881,
                "text", text, "method", "all.sale.complain").expect().statusCode(200)
                .post("/rest").jsonPath().get("result.success").toString(), equalTo("true"));
    }

    @Test //Поиск по фото
    public void uploadPhotoRecognize() {
        assertThat(new RestRequest().getRequest().params("method", "all.sale.uploadphotorecognize").multiPart("files[0]", getFile("mazda3.png"), "application/image")
                .expect().statusCode(200).post("/rest").jsonPath().get("result.recognize.mark.name").toString(), equalTo("[Mazda]"));
    }
    //
    @Test // Список марок
    public void allMarkGetList() {
        String method = "all.mark.getList";
        RestAssured.baseURI = api;
        Response r = given().header("Accept-Encoding", "gzip")
                .get("/rest/?method=" + method + "&category_id=15" + "&sid=" + sid + "&client_tz=120&key=" + key + "&version=2.2.2&uuid=" + uuid + "&format=json");
        assertTrue("statusCode = " + r.statusCode(), r.statusCode() == 200);
        System.out.println(r.jsonPath().get("result.items.name").toString());
        String[] mark = splitToArray(r.jsonPath().get("result.items.name").toString());
        String[] groups = splitToArray(r.jsonPath().get("result.groups.name").toString());
        assertThat(Arrays.asList(groups),
                containsInAnyOrder(equalToIgnoringWhiteSpace("Иномарки"), equalToIgnoringWhiteSpace("Любая марка"), (equalToIgnoringWhiteSpace("Отечественные"))));
        prt(r);
    }



//    GET https://api.auto.ru/rest/?sid=16338009.e25bb23dfe0f6ae2_7030c4e460ddcf433a0483d83e00db9c&client_tz=180&method=users.profile.me&client_version=3.13.0&key=1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a&client_os=6.0.1&version=2.2.2&uuid=e0666149817300e5cf9581312e1bab3a&device_name=motorola%20XT1562&client_platform=android&format=json HTTP/1.1
//    Host: api.auto.ru
//    Connection: Keep-Alive
//    Accept-Encoding: gzip
//    User-Agent: okhttp/3.4.1

    // //Список моделей
    // public static Response modelList() {
    // Response markList = markList();
    // String[] markID =
    // markList.body().jsonPath().get("result.items.id").toString().replace("[",
    // "").replace("]", "").split(",");
    // int a = rand.nextInt(markID.length);
    // randMarkID = markID[a];
    // String method = "catalog.folder.getEditModels";
    // RestAssured.baseURI = api;
    // Response modelList = given().
    // header("Accept-Encoding", "gzip").
    // get("/rest/?method=" + method + "&category_id=15&" +
    // "is_for_editform=1&sid=" + sid + "&mark_id=" + randMarkID +
    // "&client_tz=120&key=b7bf0dfc8cc562c1bf2cffdd9e78fc181f97f6c82f85fbca16d62d3d3258963c"
    // +
    // "&version=2.2.2&uuid=" + uuid + "&format=json");
    // return modelList;
    // }


}
