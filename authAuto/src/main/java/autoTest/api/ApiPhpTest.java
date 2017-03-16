package autoTest.api;

import static com.jayway.restassured.RestAssured.given;
import static methods.ApiVersion.API_2_2_2;
import static methods.Constants.AUTO_CATEGORY;
import static methods.FirstConnect.api;
import static methods.FirstConnect.auth_sid;
import static methods.FirstConnect.getUuidSidAuth;
import static methods.FirstConnect.key;
import static methods.FirstConnect.password;
import static methods.FirstConnect.sid;
import static methods.FirstConnect.username;
import static methods.FirstConnect.uuid;
import static methods.MethodsAddForm.bodytypeList;
import static methods.MethodsAddForm.driveList;
import static methods.MethodsAddForm.enginetypeList;
import static methods.MethodsAddForm.gearboxList;
import static methods.MethodsAddForm.generationsList;
import static methods.MethodsAddForm.markList;
import static methods.MethodsAddForm.modelList;
import static methods.MethodsAddForm.modificationList;
import static methods.MethodsAddForm.yearList;
import static methods.Utils.getFile;
import static methods.Utils.prt;
import static methods.Utils.splitToArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import autoTest.exp.RestRequest;
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

    @Test
    public void yearsListIsGreaterThanZero() {
        assertThat(Arrays.asList(splitToArray(yearList().body().jsonPath().get("result.id").toString())), hasSize(greaterThan(0)));
    }

    @Test
    public void getEditGenerations() throws IOException {
        Response generationsList = generationsList();
        assertTrue("catalog.folder.getEditGenerations fail, statusCode=" + generationsList.statusCode(), generationsList.statusCode() == 200);
        String[] generationsIDList = splitToArray(generationsList.body().jsonPath().get("result.id").toString());
        assertTrue("catalog.folder.getEditGenerations fail", generationsIDList.length > 0);
        // System.out.println(generationsList.body().asString());
    }

    @Test
    public void bodytype_list() throws IOException {
        Response bodytypeList = bodytypeList();
        assertTrue("catalog.bodytype.getList fail, statusCode=" + bodytypeList.statusCode(), bodytypeList.statusCode() == 200);
        String[] bodytypeIDList = splitToArray(bodytypeList.body().jsonPath().get("result.id").toString());
        assertTrue("catalog.bodytype.getList fail", bodytypeIDList.length > 0);
        // System.out.println(bodytypeList.body().asString());
    }

    @Test
    public void enginetypeGetList() throws IOException {
        Response enginetypeList = enginetypeList();
        assertTrue("catalog.enginetype.getList fail, statusCode=" + enginetypeList.statusCode(), enginetypeList.statusCode() == 200);
        String[] enginetypeIDList = splitToArray(enginetypeList.body().jsonPath().get("result.id").toString());
        assertTrue("catalog.enginetype.getList fail", enginetypeIDList.length > 0);
        // System.out.println(enginetypeList.body().asString());
    }

    @Test
    public void driveGetList() throws IOException {
        Response driveList = driveList();
        assertTrue("catalog.drive.getList fail, statusCode=" + driveList.statusCode(), driveList.statusCode() == 200);
        String[] driveIDList = splitToArray(driveList.body().jsonPath().get("result.id").toString());
        assertTrue("catalog.drive.getList fail", driveIDList.length > 0);
        // System.out.println(driveList.body().asString());
    }

    @Test
    public void gearboxGetLis() throws IOException {
        Response gearboxList = gearboxList();
        assertTrue("catalog.gearbox.getList fail, statusCode=" + gearboxList.statusCode(), gearboxList.statusCode() == 200);
        String[] gearboxIDList = splitToArray(gearboxList.body().jsonPath().get("result.id").toString());
        assertTrue("catalog.gearbox.getList fail", gearboxIDList.length > 0);
        // System.out.println(gearboxList.body().asString());

    }

    @Test
    public void modification_list() throws IOException {
        Response modificationList = modificationList();
        assertTrue("catalog.modification.getList fail, statusCode=" + modificationList.statusCode(), modificationList.statusCode() == 200);
        String[] modificationIDList = splitToArray(modificationList.body().jsonPath().get("result.id").toString());
        assertTrue("catalog.modification.getList fail", modificationIDList.length > 0);
        // System.out.println(modificationList.body().asString());
        // System.out.println(randGearboxID + " "+ randEnginetypeID);
    }

    @Test // my.review, они же отзывы
    public void myReview() {
        RestAssured.baseURI = api;
        Response r = given().header("Accept-Encoding", "gzip").when().get("/rest/?category_id=15&sid=" + sid
                + "&method=my.review.getBlocks&key=1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a&version=2.2.2&uuid=" + uuid + "&format=json");
        assertTrue("statusCode = " + r.statusCode(), r.statusCode() == 200);
        String test = r.body().jsonPath().get("result").toString();
        System.out.println(r.body().jsonPath().get("result.new_opinions.id").toString());
        System.out.println(test);
        System.out.println(test.contains("current_search"));
    }

    @Test // получаем список телефонов
    public void getPhones() {
        assertThat(new RestRequest().getRequest().baseUri(api)
                .params("sid", auth_sid, "method", "users.profile.getPhones", "uuid", uuid, "format", "json", "key", key, "version", "2.2.2").when().get("/rest").jsonPath()
                .get("result.phone").toString(), containsString(username));

    }

    // Ошибки размещения объявления
    @Test
    public void errorAddForm() {
        RestAssured.baseURI = api;
        Response r = given().header("Accept-Encoding", "gzip", "Content-Type", "application/x-www-form-urlencoded").parameter("key", key)
                .post("/rest/?category_id=15&color=28&custom=1&is_for_editform=1&phones_redirect=1&pts=1&sale_id=0&section_id=1&state=4&wheel=1&sid=" + sid
                        + "&method=all.sale.add&client_os=6.0.1&version=2.2.2&uuid=" + uuid + "&format=json");
        assertTrue("statusCode = " + r.statusCode(), r.statusCode() == 200);
        assertTrue("result.errors not equals 15", r.jsonPath().get("result.errors").toString().split(",").length == 15);
    }

    @Test
    public void getPresetsCom() {
        String[] presetsConst = { "Легкие коммерческие", "Седельные тягачи", "Грузовики", "Автобусы", "Прицепы" };
        String method = "all.sale.getPresets";
        Response r = given().baseUri(api).header("Accept-Encoding", "gzip")
                .get("/rest/?category_id=29&sid=" + sid + "&method=" + method + "&key=" + key + "&version=2.2.2&uuid=" + uuid + "&format=json");
        assertTrue("statusCode = " + r.statusCode(), r.statusCode() == 200);
        String[] presets = splitToArray(r.jsonPath().get("result.label").toString());
        assertTrue("Numb presents", presets.length == presetsConst.length);
        for (int i = 0; i < presetsConst.length; i++) {
            assertTrue("Where is preset " + presetsConst[i], r.body().jsonPath().get("result.label").toString().contains(presetsConst[i]));
        }
    }

    @Test
    public void getPresetsMoto() {
        String[] presetsConst = { "Мотоциклы", "Скутеры", "Снегоходы", "Мотовездеходы" };
        String method = "all.sale.getPresets";
        Response r = given().baseUri(api).header("Accept-Encoding", "gzip")
                .get("/rest/?category_id=17&sid=" + sid + "&method=" + method + "&key=" + key + "&version=2.2.2&uuid=" + uuid + "&format=json");
        assertTrue("statusCode = " + r.statusCode(), r.statusCode() == 200);
        String[] presets = splitToArray(r.jsonPath().get("result.label").toString());
        assertTrue("Numb presents", presets.length == presetsConst.length);
        for (int i = 0; i < presetsConst.length; i++) {
            assertTrue("Where is preset " + presetsConst[i], r.body().jsonPath().get("result.label").toString().contains(presetsConst[i]));
        }
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
        String txt = "Неверная модель/поколение";
        String method = "all.sale.complain";
        Response r = given().baseUri(api).header("Accept-Encoding", "gzip", "Content-Type", "application/x-www-form-urlencoded", "X-OTRS-platform", "Android")
                .post("/rest/?category_id=15&section_id=1&sale_id=1048427881&text=" + txt + "&sid=" + sid + "&client_tz=180&method=" + method + "&key=" + key
                        + "&version=2.2.2&uuid=" + uuid + "&format=json");
        assertTrue("statusCode = " + r.statusCode(), r.statusCode() == 200);
        assertTrue(r.jsonPath().get("result.success").toString() == "true");

    }

    @Test // Баланс
    public void fetchBalance() {
        // autorize();
        String method = "all.service.fetchBalance";
        Response r = given().baseUri(api).header("Accept-Encoding", "gzip")
                .get("/rest/?sid=" + auth_sid + "&method=" + method + "&key=" + key + "&version=2.2.2&uuid=" + uuid + "&gateway=googleplay&format=json");
        assertTrue("Status code = " + r.statusCode(), r.statusCode() == 200);
        assertTrue("Balance = " + r.jsonPath().get("result").toString(), Integer.valueOf(r.jsonPath().get("result").toString()) > 0);

    }

    // @Test // logout
    // public void logout() {
    // // autorize();
    // String method = "users.auth.logout";
    // Response r = given().baseUri(api).header("Accept-Encoding", "gzip")
    // .get("/rest/?sid=" + auth_sid + "&method=" + method + "&key=" + key +
    // "&version=2.2.2&uuid=" + uuid + "&format=json");
    // assertTrue("Status code = " + r.statusCode(), r.statusCode() == 200);
    // assertTrue("result.success = " + r.jsonPath().get("result").toString(),
    // Integer.valueOf(r.jsonPath().get("result.success").toString()) == 1);
    // }

    @Test
    public void sendFile() {
        prt(new RestRequest().getRequest().baseUri(api).params("method", "all.sale.uploadphotorecognize").multiPart("files[0]", getFile("LEO_3260.JPG"), "application/image")
                .expect().statusCode(200).post("/rest"));
    }

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
