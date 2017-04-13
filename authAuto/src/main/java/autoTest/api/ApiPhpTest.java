package autoTest.api;

import static com.jayway.restassured.RestAssured.given;
import static methods.Constants.*;
import static methods.FirstConnect.*;
import static methods.MethodsAddForm.markList;
import static methods.Utils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;


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
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.Stories;

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
    private String version;

    @Parameter
    private String keyApi;


    public ApiPhpTest(String version, String keyApi) {
        this.version = version;
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
        JsonPath r = new RestRequest().getRequest().baseUri(api).params("method", "api.service.start", "key", keyApi, "version", version).when().get("/rest").jsonPath();
        assertThat(r.get("result.status-api.ios-url").toString(), equalTo("https://itunes.apple.com/ru/app/avto.ru-pokupka-i-prodaza/id507760450?mt=8"));
    }

    @Test // method=api.service.start
    public void serviceStartGoogleUrlIsNotEmpty() {
        JsonPath r = new RestRequest().getRequest().baseUri(api).params("method", "api.service.start", "key", keyApi, "version", version).when().get("/rest").jsonPath();
        assertThat(r.get("result.status-api.android-url").toString(), equalTo("https://play.google.com/store/apps/details?id=ru.auto.ara"));
    }

    @Test
    public void markGetList() {
        String[] markList = splitToArray(markList().body().jsonPath().get("result.items.name").toString());
        String[] markListConst = {"LADA (ВАЗ)", "AC", "Acura", "Adler", "Alfa Romeo", "Alpina", "Alpine", "AM General", "AMC", "Ariel", "Aro", "Asia", "Aston Martin", "Audi",
                "Austin", "Autobianchi", "Baltijas Dzips", "Batmobile", "Beijing", "Bentley", "Bertone", "Bilenkin", "Bitter", "BMW", "Borgward", "Brabus", "Brilliance", "Bristol",
                "Bufori", "Bugatti", "Buick", "BYD", "Byvin", "Cadillac", "Callaway", "Carbodies", "Caterham", "Changan", "ChangFeng", "Chery", "Chevrolet", "Chrysler", "Citroen",
                "Cizeta", "Coggiola", "Dacia", "Dadi", "Daewoo", "Daihatsu", "Daimler", "Datsun", "De Tomaso", "Delage", "DeLorean", "Derways", "DeSoto", "Dodge", "DongFeng",
                "Doninvest", "Donkervoort", "DS", "E-Car", "Eagle", "Eagle Cars", "Ecomotors", "Excalibur", "FAW", "Ferrari", "Fiat", "Fisker", "Ford", "Foton", "FSO", "Fuqi",
                "Geely", "Genesis", "Geo", "GMC", "Gonow", "Gordon", "Great Wall", "Hafei", "Haima", "Hanomag", "Haval", "Hawtai", "Hindustan", "Holden", "Honda", "HuangHai",
                "Hudson", "Hummer", "Hyundai", "Infiniti", "Innocenti", "Invicta", "Iran Khodro", "Isdera", "Isuzu", "JAC", "Jaguar", "Jeep", "Jensen", "JMC", "Kia", "Koenigsegg",
                "KTM AG", "Lamborghini", "Lancia", "Land Rover", "Landwind", "Lexus", "Liebao Motor", "Lifan", "Lincoln", "Lotus", "LTI", "Luxgen", "Mahindra", "Marcos", "Marlin",
                "Marussia", "Maruti", "Maserati", "Maybach", "Mazda", "McLaren", "Mega", "Mercedes-Benz", "Mercury", "Metrocab", "MG", "Microcar", "Minelli", "MINI", "Mitsubishi",
                "Mitsuoka", "Morgan", "Morris", "Nissan", "Noble", "Oldsmobile", "Opel", "Osca", "Packard", "Pagani", "Panoz", "Perodua", "Peugeot", "PGO", "Piaggio", "Plymouth",
                "Pontiac", "Porsche", "Premier", "Proton", "PUCH", "Puma", "Qoros", "Qvale", "Ravon", "Reliant", "Renaissance", "Renault", "Renault Samsung", "Rezvani", "Rimac",
                "Rolls-Royce", "Ronart", "Rover", "Saab", "Saleen", "Santana", "Saturn", "Scion", "SEAT", "Shanghai Maple", "ShuangHuan", "Skoda", "Smart", "Soueast", "Spectre",
                "Spyker", "SsangYong", "Steyr", "Subaru", "Suzuki", "Talbot", "TATA", "Tatra", "Tazzari", "Tesla", "Tianma", "Tianye", "Tofas", "Toyota", "Trabant", "Tramontana",
                "Triumph", "TVR", "Ultima", "Vauxhall", "Vector", "Venturi", "Volkswagen", "Volvo", "Vortex", "W Motors", "Wanderer", "Wartburg", "Westfield", "Wiesmann", "Willys",
                "Xin Kai", "Zastava", "Zenos", "Zenvo", "Zibar", "Zotye", "ZX", "Автокам", "Бронто", "ГАЗ", "Ё-мобиль", "ЗАЗ", "ЗИЛ", "ЗиС", "ИЖ", "Канонир", "Комбат", "ЛуАЗ",
                "Москвич", "СМЗ", "ТагАЗ", "УАЗ", "Think"};
        assertThat(markList, containsSubArray(markListConst));
    }


    @Test // my.review, они же отзывы
    public void myReview() {
        RestAssured.baseURI = api;
        Response r = given().header("Accept-Encoding", "gzip").when().get("/rest/?category_id=15&sid=" + sid
                + "&method=my.review.getBlocks&key=" + keyAPI222 + "&version=2.2.2&uuid=" + uuid + "&format=json");
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
                .params("category_id", COMM_CATEGORY, "method", "all.sale.getPresets", "key", keyApi, "version", version)
                .get("/rest").jsonPath().get("result.label").toString())), hasSize(5));
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequest()
                .params("category_id", COMM_CATEGORY, "method", "all.sale.getPresets", "key", keyApi, "version", version)
                .get("/rest").jsonPath().get("result.label").toString())), containsInAnyOrder(presetsConst));
    }

    @Test
    public void getPresetsMoto() {
        String[] presetsConst = {"Мотоциклы", "Скутеры", "Снегоходы", "Мотовездеходы"};
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequest()
                .params("category_id", MOTO_CATEGORY, "method", "all.sale.getPresets", "key", keyApi, "version", version)
                .get("/rest").jsonPath().get("result.label").toString())), hasSize(4));
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequest()
                .params("category_id", MOTO_CATEGORY, "method", "all.sale.getPresets", "key", keyApi, "version", version)
                .get("/rest").jsonPath().get("result.label").toString())), containsInAnyOrder(presetsConst));
    }

    @Test
    public void getPresetsAuto() {
        List<String> presetsConst = new ArrayList<String>(Arrays.asList("Кроссоверы до миллиона", "Новые до 650 тыс.", "Toyota Corolla с пробегом", "Иномарки до 300 тыс.",
                "Внедорожники до 500 тыс.", "На автомате до 400 тыс."));
        String[] presets = splitToArray(new RestRequest().getRequest().baseUri(api).params("category_id", AUTO_CATEGORY, "method", "all.sale.getPresets", "key", keyApi, "version", version).get("/rest").jsonPath()
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
                "text", text, "method", "all.sale.complain", "key", keyApi, "version", version).expect().statusCode(200)
                .post("/rest").jsonPath().get("result.success").toString(), equalTo("true"));
    }

    @Test //Поиск по фото
    public void uploadPhotoRecognize() {
        assertThat(new RestRequest().getRequest().params("method", "all.sale.uploadphotorecognize", "key", keyApi, "version", version).multiPart("files[0]", getFile("mazda3.png"), "application/image")
                .expect().statusCode(200).post("/rest").jsonPath().get("result.recognize.mark.name").toString(), equalTo("[Mazda]"));
    }

    //
    @Test // Список марок
    public void allMarkGetList() {
        String method = "all.mark.getList";
        RestAssured.baseURI = api;
        Response r = given().header("Accept-Encoding", "gzip")
                .get("/rest/?method=" + method + "&category_id=15" + "&sid=" + sid + "&client_tz=120&key=" + keyAPI222 + "&version=2.2.2&uuid=" + uuid + "&format=json");
        assertTrue("statusCode = " + r.statusCode(), r.statusCode() == 200);
        System.out.println(r.jsonPath().get("result.items.name").toString());
        String[] mark = splitToArray(r.jsonPath().get("result.items.name").toString());
        String[] groups = splitToArray(r.jsonPath().get("result.groups.name").toString());
        assertThat(Arrays.asList(groups),
                containsInAnyOrder(equalToIgnoringWhiteSpace("Иномарки"), equalToIgnoringWhiteSpace("Любая марка"), (equalToIgnoringWhiteSpace("Отечественные"))));
        prt(r);
    }

    @Test // Сообщение об ошибке, method=api.service.feedback
    public void apiServiceFeedback() {
        assertThat(given().baseUri(api).headers("Accept-Encoding", "gzip", "Content-Type", "application/x-www-form-urlencoded").params("email", "yuioru@yandex.ru", "method", "api.service.feedback", "sid", sid, "uuid", uuid,
                "key", keyApi, "version", version, "format", "json", "message", "Test").expect().statusCode(200).post("/rest").jsonPath().get("result.message").toString(), equalTo("Спасибо! Ваше сообщение не останется без внимания!"));
    }

}





