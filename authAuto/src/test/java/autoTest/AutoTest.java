package autoTest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
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
import java.util.List;
import java.util.Random;

import static com.jayway.restassured.RestAssured.given;
import static java.nio.charset.StandardCharsets.UTF_8;
import static methods.MethodsAddForm.*;
import static methods.MethodsAddForm.generationsList;
import static org.junit.Assert.assertTrue;

public class AutoTest {
    public static final String ACCESS_KEY = "";

    public static String api = "https://api.auto.ru";
    public static String api2 = "https://api2.auto.ru";
    public static JSONObject json;
    public static String sid;
    public static String uuid_header;
    public static String uuid;
    public static String autoruuid;
    public static String x_auth;
    public static String auth_sid;
    public static String auth_sid_key;
    public static String auth_autoruuid;
    public static String markID;
    public static long millis = System.currentTimeMillis();
    public static String url_api2_search = "https://api2.auto.ru/1.1/search?category_id=15&page_num=1&page_size=50&creation_date_to=" + millis;
    String username = "yuioru@yandex.ru";
    String password = "111111";

    static CloseableHttpClient client = HttpClients.createDefault();


    @BeforeClass
    public static void beforeClass() throws IOException {
        json = FirstConnectJson.json();
        sid = String.valueOf(json.get("sid"));
        uuid_header = "OAuth" + " " + String.valueOf(json.getJSONObject("result").get("uuid"));
        uuid = String.valueOf(json.getJSONObject("result").get("uuid"));
        x_auth = "Vertis" + " " + String.valueOf(json.getJSONObject("result").get("uuid")) + " " + "5c27f9e8-2b90-433e-a0bf-b5222bbd97d0";
        autoruuid = String.valueOf(json.get("autoruuid"));
    }

    @Test
    public void NumberRegions() throws IOException {

        HttpGet get = new HttpGet("https://api2.auto.ru/1.1/regions");
        get.setHeader("Authorization", uuid_header);
        get.setHeader("X-Authorization", x_auth);
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONArray regions = new JSONArray(EntityUtils.toString(entity, UTF_8));
    }

    @Test
    public void NumberRegions10841() throws IOException {

//        System.out.println(json);
//        System.out.println(sid);
//        System.out.println(uuid);
        HttpGet get = new HttpGet("https://api2.auto.ru/1.1/regions/10841");
        get.setHeader("Authorization", uuid_header);
        get.setHeader("X-Authorization", x_auth);
        get.setHeader("Accept-Encoding", "gzip, deflate, identity");
        get.setHeader("Accept-Language", "en-RU;q=1, ru-RU;q=0.9, uk-RU;q=0.8, it-RU;q=0.7, de-RU;q=0.6");
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONArray regions = new JSONArray(EntityUtils.toString(entity, UTF_8));
        System.out.println(regions.get(1));

    }

    @Test
    public void test() throws IOException {

        HttpGet get = new HttpGet("https://api.auto.ru/rest/?sid=&client_tz=180&method=api.service.getUuid&client_version=3.10.1&key=1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a&client_os=6.0.1&version=2.2.2&device_name=motorola%20XT1562&client_platform=android&format=json");
        get.setHeader("Authorization", uuid_header);
        get.setHeader("X-Authorization", x_auth);
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONObject jsonTets = new JSONObject(EntityUtils.toString(entity, UTF_8));
        System.out.println(response);
    }

    @Test //Тест проверяет корректность выдачи по фильтру Цена
    public void testPrice() throws IOException {
        int minprice = 500000;
        int maxprice = 600000;
        HttpGet get = new HttpGet(url_api2_search + "&price_from=" + minprice + "&price_to=" + maxprice);
        get.setHeader("Authorization", uuid_header);
        get.setHeader("X-Authorization", x_auth);
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONObject jsonTets = new JSONObject(EntityUtils.toString(entity, UTF_8));
        int lenght = jsonTets.getJSONArray("list").length();
        for (int i = 0; i < lenght; i++) {
            String[] priceList = (String.valueOf(jsonTets.getJSONArray("list").getJSONObject(i).get("price")).replace("}", "").split(":"));
            int price = Integer.valueOf(priceList[3]);
            assertTrue("Цена выходит за параметры фильтров - " + price, price >= minprice & price <= maxprice);
            //System.out.println(jsonTets.getJSONArray("list").getJSONObject(i).get("price"));
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
        // System.out.println(jsonTets.getJSONArray("list").getJSONObject(0).get("year"));
        int lenght = jsonTets.getJSONArray("list").length();
        for (int i = 0; i < lenght; i++) {
            String yearStr = String.valueOf(jsonTets.getJSONArray("list").getJSONObject(i).get("year"));
            int year = Integer.valueOf(yearStr);
            assertTrue("Год выпуска выходит за параметры фильтров - " + year, year >= year_from & year <= year_to);
        }
    }

    @Test //Тест проверяет корректность выдачи по фильтру Пробег
    public void km_age() throws IOException {
        int km_age_from = 40000;
        int km_age_to = 42000;
        HttpGet get = new HttpGet(url_api2_search + "&km_age_from=" + km_age_from + "&km_age_to=" + km_age_to);
        get.setHeader("Authorization", uuid_header);
        get.setHeader("X-Authorization", x_auth);
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        JSONObject jsonTets = new JSONObject(EntityUtils.toString(entity, UTF_8));
        int lenght = jsonTets.getJSONArray("list").length();
        for (int i = 0; i < lenght; i++) {
            String km_ageStr = String.valueOf(jsonTets.getJSONArray("list").getJSONObject(i).get("km_age"));
            int km_age = Integer.valueOf(km_ageStr);
            assertTrue("Пробег выходит за параметры фильтров - " + km_age, km_age >= km_age_from & km_age <= km_age_to);
        }
    }

    @Test
    public void mark_list() throws IOException {
        Response markList = markList();
        String[] markID = markList.body().jsonPath().get("result.items.id").toString().replace("[", "").replace("]", "").split(",");
        assertTrue("method=all.mark.getList fail, statusCode=" + markList.statusCode(), markList.statusCode() == 200);
        String[] markName = markList.body().jsonPath().get("result.items.name").toString().replace("[", "").replace("]", "").split(",");
        String[] markListConst = {"LADA (ВАЗ)", "AC", "Acura", "Adler", "Alfa Romeo", "Alpina", "Alpine", "AM General", "AMC", "Ariel", "Aro", "Asia", "Aston Martin", "Audi", "Austin", "Autobianchi", "Baltijas Dzips", "Batmobile", "Beijing", "Bentley", "Bertone", "Bilenkin", "Bitter", "BMW", "Borgward", "Brabus", "Brilliance", "Bristol", "Bufori", "Bugatti", "Buick", "BYD", "Byvin", "Cadillac", "Callaway", "Carbodies", "Caterham", "Changan", "ChangFeng", "Chery", "Chevrolet", "Chrysler", "Citroen", "Cizeta", "Coggiola", "Dacia", "Dadi", "Daewoo", "Daihatsu", "Daimler", "Datsun", "De Tomaso", "Delage", "DeLorean", "Derways", "DeSoto", "Dodge", "DongFeng", "Doninvest", "Donkervoort", "DS", "E-Car", "Eagle", "Eagle Cars", "Ecomotors", "Excalibur", "FAW", "Ferrari", "Fiat", "Fisker", "Ford", "Foton", "FSO", "Fuqi", "Geely", "Genesis", "Geo", "GMC", "Gonow", "Gordon", "Great Wall", "Hafei", "Haima", "Hanomag", "Haval", "Hawtai", "Hindustan", "Holden", "Honda", "HuangHai", "Hudson", "Hummer", "Hyundai", "Infiniti", "Innocenti", "Invicta", "Iran Khodro", "Isdera", "Isuzu", "JAC", "Jaguar", "Jeep", "Jensen", "JMC", "Kia", "Koenigsegg", "KTM AG", "Lamborghini", "Lancia", "Land Rover", "Landwind", "Lexus", "Liebao Motor", "Lifan", "Lincoln", "Lotus", "LTI", "Luxgen", "Mahindra", "Marcos", "Marlin", "Marussia", "Maruti", "Maserati", "Maybach", "Mazda", "McLaren", "Mega", "Mercedes-Benz", "Mercury", "Metrocab", "MG", "Microcar", "Minelli", "MINI", "Mitsubishi", "Mitsuoka", "Morgan", "Morris", "Nissan", "Noble", "Oldsmobile", "Opel", "Osca", "Packard", "Pagani", "Panoz", "Perodua", "Peugeot", "PGO", "Piaggio", "Plymouth", "Pontiac", "Porsche", "Premier", "Proton", "PUCH", "Puma", "Qoros", "Qvale", "Ravon", "Reliant", "Renaissance", "Renault", "Renault Samsung", "Rezvani", "Rimac", "Rolls-Royce", "Ronart", "Rover", "Saab", "Saleen", "Santana", "Saturn", "Scion", "SEAT", "Shanghai Maple", "ShuangHuan", "Skoda", "Smart", "Soueast", "Spectre", "Spyker", "SsangYong", "Steyr", "Subaru", "Suzuki", "Talbot", "TATA", "Tatra", "Tazzari", "Tesla", "Tianma", "Tianye", "Tofas", "Toyota", "Trabant", "Tramontana", "Triumph", "TVR", "Ultima", "Vauxhall", "Vector", "Venturi", "Volkswagen", "Volvo", "Vortex", "W Motors", "Wanderer", "Wartburg", "Westfield", "Wiesmann", "Willys", "Xin Kai", "Zastava", "Zenos", "Zenvo", "Zibar", "Zotye", "ZX", "Автокам", "Бронто", "ГАЗ", "Ё-мобиль", "ЗАЗ", "ЗИЛ", "ЗиС", "ИЖ", "Канонир", "Комбат", "ЛуАЗ", "Москвич", "СМЗ", "ТагАЗ", "УАЗ"};

        for (int i = 0; i < markListConst.length; i++) {
            assertTrue("all.mark.getList fail, " + markListConst[i], markList.body().jsonPath().get("result.items.name").toString().contains(markListConst[i]));
        }
    }

    @Test
    public void model_list() throws IOException {
        Response modelList = modelList();
//        System.out.println(modelList.body().asString());
        assertTrue("catalog.folder.getEditModels fail, statusCode=" + modelList.statusCode(), modelList.statusCode() == 200);
        String[] modelIdList = modelList.body().jsonPath().get("result.id").toString().replace("[", "").replace("]", "").split(",");
        assertTrue("", modelIdList.length > 0);
    }

    @Test
    public void year_list() throws IOException {
        Response yearList = yearList();
        assertTrue("ccatalog.year.getList fail, statusCode=" + yearList.statusCode(), yearList.statusCode() == 200);
        String[] yearIDList = yearList.body().jsonPath().get("result.id").toString().replace("[", "").replace("]", "").split(",");
        assertTrue("", yearIDList.length > 0);
//        System.out.println(yearList.body().asString());
    }

    @Test
    public void generations_list() throws IOException {
        Response generationsList = generationsList();
        assertTrue("catalog.folder.getEditGenerations fail, statusCode=" + generationsList.statusCode(), generationsList.statusCode() == 200);
        String[] generationsIDList = generationsList.body().jsonPath().get("result.id").toString().replace("[", "").replace("]", "").split(",");
        assertTrue("catalog.folder.getEditGenerations fail", generationsIDList.length > 0);
       // System.out.println(generationsList.body().asString());
    }

    @Test
    public void bodytype_list() throws IOException {
        Response bodytypeList = bodytypeList();
        assertTrue("catalog.bodytype.getList fail, statusCode=" + bodytypeList.statusCode(), bodytypeList.statusCode() == 200);
        String[] bodytypeIDList = bodytypeList.body().jsonPath().get("result.id").toString().replace("[", "").replace("]", "").split(",");
        assertTrue("catalog.bodytype.getList fail", bodytypeIDList.length > 0);
        System.out.println(bodytypeList.body().asString());
    }
        @Test
        public void enginetype_list() throws IOException {
            Response enginetypeList = enginetypeList();
            assertTrue("catalog.bodytype.getList fail, statusCode=" + enginetypeList.statusCode(), enginetypeList.statusCode() == 200);
            String[] enginetypeIDList = enginetypeList.body().jsonPath().get("result.id").toString().replace("[", "").replace("]", "").split(",");
            assertTrue("catalog.bodytype.getList fail", enginetypeIDList.length > 0);
            System.out.println(enginetypeList.body().asString());
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
        //  System.out.println(r.statusCode());
        // System.out.print(r.body().asString());
    }

    @Test //Авторизация пользователя
    public void autorize() {
        RestAssured.baseURI = api;
        Response r =
                given().
                        headers("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8").
                        body("login=" + username + "&pass=" + password + "&sid=" + sid +
                                "&method=users.auth.login&key=1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a" +
                                "&version=2.2.2&uuid=" + uuid + "&format=json").
                        when().post("/rest/");
        assertTrue(r.statusCode() == 200);
        // System.out.println(r.body().asString());
        auth_sid = r.body().jsonPath().get("sid");
        auth_sid_key = r.body().jsonPath().get("sid_key");
        auth_autoruuid = r.body().jsonPath().get("autoruuid");

    }

    @Test //Ошибка авторизации пользователя
    public void autorizeFail() {
        RestAssured.baseURI = api;
        Response r =
                given().
                        headers("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8").
                        body("login=" + username + "&pass=" + password + "1&sid=" + sid +
                                "&method=users.auth.login&key=1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a" +
                                "&version=2.2.2&uuid=" + uuid + "&format=json").
                        when().post("/rest/");
        assertTrue(r.statusCode() == 200);
        assertTrue(r.body().jsonPath().get("error.message").equals("Неверный логин или пароль."));
//        System.out.println(r.body().asString());
        //  System.out.println(r.body().jsonPath().get("error.message"));
//        auth_sid = r.body().jsonPath().get("sid");
//        auth_sid_key = r.body().jsonPath().get("sid_key");
//        auth_autoruuid = r.body().jsonPath().get("autoruuid");

    }


    @Test //Избранные
    public void favorites() {
        autorize();
        RestAssured.baseURI = api2;
        Response r1 =
                given().
                        headers("X-Authorization", x_auth).
                        when().get("/1.1/user/favorites?sid=" + auth_sid);
        assertTrue(r1.statusCode() == 200);
        assertTrue(r1.body().asString().contains("active"));
    }

    @Test //my.review, они же отзывы
    public void myReview() {
        RestAssured.baseURI = api;
        Response r =
                given().
                        header("Accept-Encoding", "gzip").
                        when().get("/rest/?category_id=15&sid=" + sid + "&method=my.review.getBlocks&key=1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a&version=2.2.2&uuid=" + uuid + "&format=json");
        assertTrue(r.statusCode() == 200);
        String test = r.body().jsonPath().get("result").toString();
        System.out.println(r.body().jsonPath().get("result.new_opinions.id").toString());
        System.out.println(test);
        System.out.println(test.contains("current_search"));
    }

    @Test //получаем список телефонов
    public void prof() {
        autorize();
        RestAssured.baseURI = api;
        Response r =
                given().
                        header("Accept-Encoding", "gzip").
                        get("/rest/?sid=" + auth_sid + "&client_tz=120&method=users.profile.getPhones&client_version=3.11.0&key=1d2b14555a83699f57fd77d17aa2d5ce9431cd7d9f3edea14186b044e76b606a&client_os=5.0&version=2.2.2&uuid=" + uuid + "&device_name=asus%20ASUS_Z00AD&client_platform=android&format=json");
        assertTrue(r.statusCode() == 200);
        String test = r.body().jsonPath().get("result").toString();
        System.out.println(r.body().jsonPath().get("result.phone").toString());
//        System.out.println(test);
//        System.out.println(test.contains("current_search"));
    }

    @Test
    public void createAddv() {
        autorize();
        RestAssured.baseURI = api;

        Response modelList = given().
                header("Accept-Encoding", "gzip").
                get("/rest/?method=all.mark.getList&category_id=15&is_for_editform=1&sid=e20aa43ffeb30f96_89292543f6752add724431de44750aab&client_tz=120&method=all.mark.getList&client_version=3.12.0&key=b7bf0dfc8cc562c1bf2cffdd9e78fc181f97f6c82f85fbca16d62d3d3258963c&client_os=5.0&version=2.2.2&uuid=a0ffc282c65b7bfc707abd673a571611&device_name=asus%20ASUS_Z00AD&client_platform=android&format=json");

        String[] test1 = modelList.body().jsonPath().get("result.items.id").toString().split(",");
        System.out.println(test1.length);
        Random rand = new Random();
        int a = rand.nextInt(test1.length);

        System.out.println(test1[a].trim());
    }

    @Test
    public void readFile() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("test.txt"));
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
                System.out.println(km_age);
                assertTrue("Пробег выходит за параметры фильтров - " + km_age, km_age >= km_age_from & km_age <= km_age_to);
//                    System.out.println(km_age_from);
//                    System.out.println(km_age_to);
            }
        }
    }
}


