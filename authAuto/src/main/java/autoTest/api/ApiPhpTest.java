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

    @Test // Авторизация пользователя
    public void login() {
        String r = new RestRequest().getRequest().baseUri(api).contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParams("login", username, "pass", password, "method", "users.auth.login").post("/rest").jsonPath().get("result.sid").toString();
        assertThat(r, not(containsString(sid)));
    }

    @Test // Ошибка авторизации пользователя
    public void loginFail() {
        String r = given().baseUri(api).contentType("application/x-www-form-urlencoded; charset=UTF-8").formParams("login", username, "pass", "123", "sid", sid, "method",
                "users.auth.login", "key", key, "version", API_2_2_2.getVersion(), "uuid", uuid, "format", "json").when().post("/rest").jsonPath().get("error.message").toString();
        assertThat(r, equalTo("Неверный логин или пароль."));

    }

    @Test
    public void markGetList() {
        String[] markList = splitToArray(markList().body().jsonPath().get("result.items.name").toString());
        String[] markListConst = { "LADA (ВАЗ)", "AC", "Acura", "Adler", "Alfa Romeo", "Alpina", "Alpine", "AM General", "AMC", "Ariel", "Aro", "Asia", "Aston Martin", "Audi",
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
                "Москвич", "СМЗ", "ТагАЗ", "УАЗ", "Think" };
        assertThat(markList, containsSubArray(markListConst));
    }

    @Test
    public void modelsIsGreaterThanZero() {
        assertThat(Arrays.asList(splitToArray(modelList().body().jsonPath().get("result.id").toString())), hasSize(greaterThan(0)));
    }

    @Test // my.review, они же отзывы
    public void myReview() {
        RestAssured.baseURI = api;
        Response r = given().header("Accept-Encoding", "gzip").when().get("/rest/?category_id=15&sid=" + sid
                + "&method=my.review.getBlocks&key="+key+"&version=2.2.2&uuid=" + uuid + "&format=json");
        assertTrue("statusCode = " + r.statusCode(), r.statusCode() == 200);
        String test = r.body().jsonPath().get("result").toString();
        System.out.println(r.body().jsonPath().get("result.new_opinions.id").toString());
        System.out.println(test);
        System.out.println(test.contains("current_search"));
    }

    @Test // получаем список телефонов авторизованного пользователя
    public void getPhones() {
        assertThat(new RestRequest().getRequest().baseUri(api)
                .params("sid", auth_sid, "method", "users.profile.getPhones", "uuid", uuid, "format", "json", "key", key, "version", API_2_2_2.getVersion()).when().get("/rest").jsonPath()
                .get("result.phone").toString(), containsString(username));

    }

    // Количиство ошибок валидации при размещении объявления 19
    @Test
    public void quantityErrorsCountAddForm() {
   Assert.assertThat(
           Arrays.asList(splitToArray(new RestRequest().getRequest().params("key", key, "method", "all.sale.add", "category_id", 15)
                   .expect().statusCode(200).post("/rest").jsonPath().get("result.errors").toString())),
           hasSize(19));
    }

    @Test
    public void getPresetsCom() {
        String [] presetsConst = {"Легкие коммерческие", "Седельные тягачи", "Грузовики", "Автобусы", "Прицепы"};
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequest()
                .params("category_id",COMM_CATEGORY, "method","all.sale.getPresets")
                .get("/rest").jsonPath().get("result.label").toString())),hasSize(5));
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequest()
                .params("category_id",COMM_CATEGORY, "method","all.sale.getPresets")
                .get("/rest").jsonPath().get("result.label").toString())),containsInAnyOrder(presetsConst));
    }

    @Test
    public void getPresetsMoto() {
        String[] presetsConst = { "Мотоциклы", "Скутеры", "Снегоходы", "Мотовездеходы" };
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequest()
                .params("category_id",MOTO_CATEGORY, "method","all.sale.getPresets")
                .get("/rest").jsonPath().get("result.label").toString())),hasSize(4));
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequest()
                .params("category_id",MOTO_CATEGORY, "method","all.sale.getPresets")
                .get("/rest").jsonPath().get("result.label").toString())),containsInAnyOrder(presetsConst));
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
                "text", text,"method", "all.sale.complain").expect().statusCode(200)
                .post("/rest").jsonPath().get("result.success").toString(),equalTo("true"));
    }

    @Test // Баланс
    public void fetchBalance() {
        // autorize();
        String method = "all.service.fetchBalance";
        Response r = given().baseUri(api).header("Accept-Encoding", "gzip")
                .get("/rest/?sid=" + auth_sid + "&method=" + method + "&key=" + key + "&version="+API_2_2_2.getVersion()+"&uuid=" + uuid + "&gateway=googleplay&format=json");
        assertTrue("Status code = " + r.statusCode(), r.statusCode() == 200);
        assertTrue("Balance = " + r.jsonPath().get("result").toString(), Integer.valueOf(r.jsonPath().get("result").toString()) > 0);

    }

     @Test // logout
     public void logout() {
    JsonPath r = given().baseUri(api).contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .formParams("login", username, "pass", password, "sid", sid, "method", "users.auth.login", "key", key, "version", "2.2.2", "uuid", uuid, "format", "json").when()
            .post("/rest").body().jsonPath();
         String auth_sid_for_logout = r.get("sid");
         String auth_sid_key_for_logout = r.get("sid_key");
         String auth_autoruuid_for_logout = r.get("autoruuid");
     String method = "users.auth.logout";
     Response out = given().baseUri(api).header("Accept-Encoding", "gzip")
     .get("/rest/?sid=" + auth_sid_for_logout + "&method=" + method + "&key=" + key +
     "&version=2.2.2&uuid=" + uuid + "&format=json");
     assertTrue("result.success = " + out.jsonPath().get("result").toString(),
     Integer.valueOf(out.jsonPath().get("result.success").toString()) == 1);
     }

    @Test //Поиск по фото
    public void uploadPhotoRecognize() {
        prt(new RestRequest().getRequest().baseUri(api).params("method", "all.sale.uploadphotorecognize").multiPart("files[0]", getFile("mazda3.png"), "application/image")
                .expect().statusCode(200).post("/rest"));
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

    @Test
    public void createAddv() {
        // autorize();
        RestAssured.baseURI = api;

        Response modelList = given().header("Accept-Encoding", "gzip").get(
                "/rest/?method=all.mark.getList&category_id=15&is_for_editform=1&sid=e20aa43ffeb30f96_89292543f6752add724431de44750aab&client_tz=120&method=all.mark.getList&client_version=3.12.0&key=b7bf0dfc8cc562c1bf2cffdd9e78fc181f97f6c82f85fbca16d62d3d3258963c&client_os=5.0&version=2.2.2&uuid=a0ffc282c65b7bfc707abd673a571611&device_name=asus%20ASUS_Z00AD&client_platform=android&format=json");

        String[] test1 = splitToArray(modelList.body().jsonPath().get("result.items.id").toString());
        System.out.println(test1.length);
        Random rand = new Random();
        int a = rand.nextInt(test1.length);

        System.out.println(test1[a].trim());
    }

}
