package autoTest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.hamcrest.core.Every;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import static autoTest.FirstConnectJson.*;
import static autoTest.AutoTest.millis;
import static autoTest.FirstConnectJson.beforeClass;
import static autoTest.FirstConnectJson.sid;
import static autoTest.FirstConnectJson.uuid;
import static com.jayway.restassured.RestAssured.given;
import static methods.Utils.replaceSome;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MotoTest {
    @BeforeClass
    public static void before() throws IOException {
        beforeClass();
    }
    @Test
    public void markListMoto() throws IOException {
        String method = "all.mark.getList";
        String[] groups_name_const = {"Любая марка", "Отечественные", "Иномарки"};
        String[] mark_name_const = {"ABM","Aermacchi","American IronHorse","Apollo","Aprilia","Arlen Ness","Bajaj","Bars","Benelli","Beta","Big Bear Choppers","Big Dog Motorcycles","Bimota","BM","BMW","Boom Trikes","BRP","BSE","Buell","Cagiva","Campagna","Centurion","CFMoto","Cobra","Confederate","CZ","Derbi","Desert Raven","DM Telai","Dnepr (Днепр)","Ducati","Falcon","Fine Custom Mechanics","Forsage","Futong","GAS GAS","GX moto","Harley-Davidson","Honda","Husaberg","Husqvarna","Hyosung","Indian","IRBIS","Jawa","JMC","Johnny Pag","Kawasaki","Kaxa Motos","KAYO","Keeway","KTM","KXD","Kymco","Lifan","Mikilon","Minsk (Минск)","Moto Guzzi","Moto Morini","Motoland","MV Agusta","MZ","Nexus","Omaks Motors","Orion","Pannonia","Patron","PCW","Pitsterpro","Polini","PUCH","Racer","Regal Raptor","Reggy","Rewaco","Rieju","Rokon","Royal Enfield","S2","Sachs","Sagitta","Saxon","Sherco","Stels","Stingray","Suzuki","Sym","TM Racing","Triumph","TVS","UM","Upbeat (ABT)","Vento","Victory","Virus","Wels","Xmotos","Yamaha","Yamasaki","YCF","Zongshen","Zundapp","Верховина","Восход","Десна","ЗиД","ИЖ","Китай (NoName)","Ковровец","ММЗ","ТМЗ (Туламашзавод)","Урал","Эксклюзив"};
        RestAssured.baseURI = api;
        Response r = given().
                header("Accept-Encoding", "gzip").
                get("/rest/?method=" + method + "&category_id=1" +
                        "&sid=" + sid + "&key=" + key +
                        "&version=2.2.2&uuid=" + uuid + "&format=json");
        String groups_name = r.body().jsonPath().get("result.groups.name").toString();
        for (int i = 0; i < groups_name_const.length; i++) {
            assertTrue("Where is group " + groups_name_const[i], r.body().jsonPath().get("result.groups.name").toString().contains(groups_name_const[i]));
        }
        for (int i = 0; i < mark_name_const.length; i++) {
            assertTrue("Where is mark " + mark_name_const[i], r.body().jsonPath().get("result.items.name").toString().contains(mark_name_const[i]));
        }
    }

    @Test //geo_id = "213" МСК
    public void filterGeoCityCountTotalMoto() throws IOException {
        String geo_id = "213";
        String method =  "all.sale.countTotal";
        RestAssured.baseURI = api;
        Response r = given().
                header("Accept-Encoding", "gzip").
                get("rest/?category_id=1&geo_id="+geo_id+"&photo=1&prepend_empty_option=1&used_key=5&sid="+sid+"" +
                        "&method="+method+"&key="+key+"&version=2.2.2&uuid="+uuid+"&format=json");
        assertTrue("countTotal = 0",Integer.valueOf(r.jsonPath().get("result.total_found").toString())>0);

    }
    @Test //geo_id=1 - МО
    public void filterGeoRegCountTotalMoto() throws IOException {
        String geo_id = "1";
        String method =  "all.sale.countTotal";
        RestAssured.baseURI = api;
        Response r = given().
                header("Accept-Encoding", "gzip").
                get("rest/?category_id=1&geo_id="+geo_id+"&photo=1&prepend_empty_option=1&used_key=5&sid="+sid+"" +
                        "&method="+method+"&key="+key+"&version=2.2.2&uuid="+uuid+"&format=json");
        assertTrue("countTotal = 0",Integer.valueOf(r.jsonPath().get("result.total_found").toString())>0);
    }

    @Test //geo_id = "213" МСК
    public void filterGeoCitySearchMoto() throws IOException {
        String geo_id = "213";
        String method =  "all.sale.search";
        RestAssured.baseURI = api;
        Response r = given().
                header("Accept-Encoding", "gzip").
                get("/rest/?offset=0&category_id=1&creation_date_to="+millis+"&geo_id="+geo_id+"&limit=20&photo=1" +
                        "&prepend_empty_option=1&sort[price]=asc&used_key=5&sid="+sid+"&method="+method+"&key="+key+"" +
                        "&version=2.2.2&uuid="+uuid+"&format=json");
//        print(r.jsonPath().get("result.sales.poi.region").toString());
//        print(r.jsonPath().get("result").toString());

        String [] city_search = replaceSome(r.jsonPath().get("result.sales.poi.region").toString());
        assertThat(Arrays.asList(city_search),Every.everyItem(anyOf(equalToIgnoringWhiteSpace("Москва"))));

//        for (int i=0; i<city_search.length; i++){
//            assertTrue("not only Moskow "  + city_search[i],city_search[i].trim().equals("Москва"));
//        }
//

    }
    @Test //geo_id=1 - МО
    public void filterGeoRegSearchMoto() throws IOException {
        String geo_id = "1";
        String method =  "all.sale.search";
        RestAssured.baseURI = api;
        Response r = given().
                header("Accept-Encoding", "gzip").
                get("/rest/?offset=0&category_id=1&creation_date_to="+millis+"&geo_id="+geo_id+"&limit=20&photo=1" +
                        "&prepend_empty_option=1&sort[price]=asc&used_key=5&sid="+sid+"&method="+method+"&key="+key+"" +
                        "&version=2.2.2&uuid="+uuid+"&format=json");
//        print(r.jsonPath().get("result.sales.poi.region").toString());
//        print(r.asString());
        String [] city_search = replaceSome(r.jsonPath().get("result.sales.poi.region").toString());
        assertThat(Arrays.asList(city_search),Every.everyItem(anyOf(equalToIgnoringWhiteSpace("Москва"),
                (equalToIgnoringWhiteSpace("Московская обл.")))));
//        for (int i=0; i<city_search.length; i++){
//            assertTrue("not only Moskow and MO " + city_search[i],city_search[i].trim().equals("Москва") ||
//                    city_search[i].trim().equals("Московская обл."));
//        }
    }
}
