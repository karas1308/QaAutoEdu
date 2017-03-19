package autoTest.api;

import autoTest.exp.RestRequest;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.hamcrest.core.Every;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static methods.ArrayContainsSubArray.containsSubArray;
import static methods.Constants.*;
import static methods.FirstConnect.*;
import static methods.FirstConnect.getUuidSidAuth;
import static methods.FirstConnect.sid;
import static methods.FirstConnect.uuid;
import static com.jayway.restassured.RestAssured.given;
import static methods.Utils.prt;
import static methods.Utils.splitToArray;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(value = Parameterized.class)

public class MotoTest {
    @BeforeClass
    public static void before() throws IOException {
        getUuidSidAuth();
    }

    private Integer id;
    String[] marksConstListMotorcycle = {"ABM", "Aermacchi", "American IronHorse", "Apollo", "Aprilia", "Arlen Ness", "Bajaj", "Bars", "Benelli", "Beta", "Big Bear Choppers", "Big Dog Motorcycles", "Bimota", "BM", "BMW", "Boom Trikes", "BRP", "BSE", "Buell", "Cagiva", "Campagna", "Centurion", "CFMoto", "Cobra", "Confederate", "CZ", "Derbi", "Desert Raven", "DM Telai", "Dnepr (Днепр)", "Ducati", "Falcon", "Fine Custom Mechanics", "Forsage", "Futong", "GAS GAS", "GX moto", "Harley-Davidson", "Honda", "Husaberg", "Husqvarna", "Hyosung", "Indian", "IRBIS", "Jawa", "JMC", "Johnny Pag", "Kawasaki", "Kaxa Motos", "KAYO", "Keeway", "KTM", "KXD", "Kymco", "Lifan", "Mikilon", "Minsk (Минск)", "Moto Guzzi", "Moto Morini", "Motoland", "MV Agusta", "MZ", "Nexus", "Omaks Motors", "Orion", "Pannonia", "Patron", "PCW", "Pitsterpro", "Polini", "Racer", "Regal Raptor", "Reggy", "Rewaco", "Rokon", "Royal Enfield", "S2", "Sachs", "Saxon", "Sherco", "Stels", "Stingray", "Suzuki", "Sym", "TM Racing", "Triumph", "TVS", "UM", "Upbeat (ABT)", "Vento", "Victory", "Virus", "Wels", "Xmotos", "Yamaha", "Yamasaki", "YCF", "Zongshen", "Zundapp", "Верховина", "Восход", "Десна", "ЗиД", "ИЖ", "Китай (NoName)", "ММЗ", "ТМЗ (Туламашзавод)", "Урал", "Эксклюзив"};
    String[] marksConstListScooters = {"BMW"};
    String[] marksConstListSnowmobile = {"Apollo"};
    String[] marksConstListAtv = {"ГАЗ"};

    String params = "&photo=1&prepend_empty_option=1&used_key=5&sid=" + sid + "" +
            "&key=" + key + "&version=2.2.2&uuid=" + uuid + "&format=json";

    @Parameterized.Parameters
    public static Collection categoryId1() {
        return Arrays.asList(
                new Object[][]{{MOTORCYCLE}, {SCOOTERS}, {SNOWMOBILE}, {ATV}});
    }

    public MotoTest(Integer id) {
        this.id = id;
    }

       @Test
    public void markGroupsListIsDsiplayed() {
               assertThat(splitToArray(new RestRequest().getRequest().params("method", "all.mark.getList", "category_id", id).expect().statusCode(200).get("/rest").jsonPath()
                .get("result.groups.name").toString()), arrayContainingInAnyOrder(GROUPS_CONST_LIST));
    }
    @Test
    public void marksListIsDsiplayed() {
        if (id == MOTORCYCLE) {
            assertThat(splitToArray(new RestRequest().getRequest().params("method", "all.mark.getList", "category_id", id).expect().statusCode(200).get("/rest").jsonPath()
                    .get("result.items.name").toString()), containsSubArray(marksConstListMotorcycle));
        }
        if (id == SCOOTERS) {
            assertThat(splitToArray(new RestRequest().getRequest().params("method", "all.mark.getList", "category_id", id).expect().statusCode(200).get("/rest").jsonPath()
                    .get("result.items.name").toString()), containsSubArray(marksConstListScooters));
        }
        if (id == SNOWMOBILE) {
            assertThat(splitToArray(new RestRequest().getRequest().params("method", "all.mark.getList", "category_id", id).expect().statusCode(200).get("/rest").jsonPath()
                    .get("result.items.name").toString()), containsSubArray(marksConstListSnowmobile));
        }
        if (id == ATV) {
            assertThat(splitToArray(new RestRequest().getRequest().params("method", "all.mark.getList", "category_id", id).expect().statusCode(200).get("/rest").jsonPath()
                    .get("result.items.name").toString()), containsSubArray(marksConstListAtv));
        }
    }

    @Test // geo_id = "213" МСК
    public void citySearchResultsCountGreaterThanZero() {
        assertThat(Integer.valueOf(
                new RestRequest().getRequest().params("method", "all.sale.countTotal", "category_id", id, "geo_id", "213")
                        .expect().statusCode(200).get("/rest").jsonPath().get("result.total_found").toString()),
                greaterThan(0));
    }

    @Test // geo_id=1 - МО
    public void regionSearchResultsCountGreaterThanZero() {
        assertThat(Integer.valueOf(
                new RestRequest().getRequest().params("method", "all.sale.countTotal", "category_id", id, "geo_id", "1")
                        .expect().statusCode(200).get("/rest").jsonPath().get("result.total_found").toString()),
                greaterThan(0));
    }

    @Test // geo_id = "213" МСК
    public void filteringSearchResultsByCity() {
        assertThat(
                Arrays.asList(splitToArray(new RestRequest().getRequest()
                        .params("method", "all.sale.search", "category_id", id, "geo_id", "213", "creation_date_to", cutTime)
                        .expect().statusCode(200).get("/rest").jsonPath().get("result.sales.poi.region").toString())),
                Every.everyItem(equalToIgnoringWhiteSpace("Москва")));
    }

    @Test // geo_id=1 - МО
    public void filteringSearchResultsByRegion() {
        assertThat(
                Arrays.asList(splitToArray(new RestRequest().getRequest()
                        .params("method", "all.sale.search", "category_id", id, "geo_id", "1", "creation_date_to", cutTime
                        )
                        .expect().statusCode(200).get("/rest").jsonPath().get("result.sales.poi.region").toString())),
                Every.everyItem(anyOf(equalToIgnoringWhiteSpace("Москва"), equalToIgnoringWhiteSpace("Московская обл."))));
    }
}