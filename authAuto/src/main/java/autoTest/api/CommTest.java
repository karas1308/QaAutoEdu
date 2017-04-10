package autoTest.api;

import static methods.ArrayContainsSubArray.containsSubArray;
import static methods.Constants.*;
import static methods.FirstConnect.getUuidSidAuth;
import static methods.FirstConnect.cutTime;
import static methods.FirstConnect.keyAPI222;
import static methods.Utils.splitToArray;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.hamcrest.core.Every;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import methods.RestRequest;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Api PHP")
@Stories("Filtering search results by different regions and categories")
@RunWith(value = Parameterized.class)
public class CommTest {

    @BeforeClass
    public static void before() throws IOException {
        getUuidSidAuth();
    }

    static String[] marksConstListLightTrucks = {"BAW", "Changan", "Citroen", "DFSK", "Dodge", "FAW", "Fiat", "Ford", "Foton", "Freightliner", "Hyundai", "Isuzu", "IVECO", "JAC",
            "JBC", "JMC", "Kia", "LDV", "Mazda", "Mercedes-Benz", "Mitsubishi", "Nissan", "Opel", "Peugeot", "Renault", "Skoda", "Ssang Yong", "Toyota", "Volkswagen", "YueJin",
            "Богдан", "ВАЗ", "ВИС", "ГАЗ", "ГАЗ-САЗ", "ИЖ", "РАФ", "ТагАЗ", "УАЗ"};
    static String[] marksConstListBus = {"Volkswagen"};
    static String[] marksConstListArtic = {"MAN"};
    static String[] marksConstListDrags = {"МАЗ"};
    static String[] marksConstListTrucks = {"МАЗ"};
    static String[] marksConstListMotorcycle = {"ABM", "Aermacchi", "American IronHorse", "Apollo", "Aprilia", "Arlen Ness", "Bajaj", "Bars", "Benelli", "Beta", "Big Bear Choppers", "Big Dog Motorcycles", "Bimota", "BM", "BMW", "Boom Trikes", "BRP", "BSE", "Buell", "Cagiva", "Campagna", "Centurion", "CFMoto", "Cobra", "Confederate", "CZ", "Derbi", "Desert Raven", "DM Telai", "Dnepr (Днепр)", "Ducati", "Falcon", "Fine Custom Mechanics", "Forsage", "Futong", "GAS GAS", "GX moto", "Harley-Davidson", "Honda", "Husaberg", "Husqvarna", "Hyosung", "Indian", "IRBIS", "Jawa", "JMC", "Johnny Pag", "Kawasaki", "Kaxa Motos", "KAYO", "Keeway", "KTM", "KXD", "Kymco", "Lifan", "Mikilon", "Minsk (Минск)", "Moto Guzzi", "Moto Morini", "Motoland", "MV Agusta", "MZ", "Nexus", "Omaks Motors", "Orion", "Pannonia", "Patron", "PCW", "Pitsterpro", "Polini", "Racer", "Regal Raptor", "Reggy", "Rewaco", "Rokon", "Royal Enfield", "S2", "Sachs", "Saxon", "Sherco", "Stels", "Stingray", "Suzuki", "Sym", "TM Racing", "Triumph", "TVS", "UM", "Upbeat (ABT)", "Vento", "Victory", "Virus", "Wels", "Xmotos", "Yamaha", "Yamasaki", "YCF", "Zongshen", "Zundapp", "Верховина", "Восход", "Десна", "ЗиД", "ИЖ", "Китай (NoName)", "ММЗ", "ТМЗ (Туламашзавод)", "Урал", "Эксклюзив"};
    static String[] marksConstListScooters = {"BMW"};
    static String[] marksConstListSnowmobile = {"Stels"};
    static String[] marksConstListAtv = {"IRBIS"};

    private Integer id;
    private String[] makrList;
    @Parameter
    private String version;

    @Parameter
    private String keyApi;

    @Parameterized.Parameters
    public static Collection<Object[]> categoryId() {
        return Arrays.asList(new Object[][]{
                {ARTIC, marksConstListArtic, "2.2.2", keyAPI222},
                {DRAGS, marksConstListDrags, "2.2.2", keyAPI222},
                {BUS, marksConstListBus, "2.2.2", keyAPI222},
                {TRUCKS, marksConstListTrucks, "2.2.2", keyAPI222},
                {LIGHT_TRUCKS, marksConstListLightTrucks, "2.2.2", keyAPI222},
                {MOTORCYCLE, marksConstListMotorcycle, "2.2.2", keyAPI222},
                {SCOOTERS, marksConstListScooters, "2.2.2", keyAPI222},
                {SNOWMOBILE, marksConstListSnowmobile, "2.2.2", keyAPI222},
                {ATV, marksConstListAtv, "2.2.2", keyAPI222}});
    }

    public CommTest(Integer id, String[] makrList, String version, String keyApi) {
        this.id = id;
        this.makrList = makrList;
        this.version = version;
        this.keyApi = keyApi;
    }

    @Test
    public void marksListIsDsiplayed() {
        assertThat(splitToArray(new RestRequest().getRequest().params("method", "all.mark.getList", "category_id", id).params("key", keyApi, "version", version).expect().statusCode(200).get("/rest").jsonPath()
                .get("result.items.name").toString()), containsSubArray(makrList));
//
    }

    @Test
    public void markGroupsListIsDsiplayed() {
        String[] groupsConstList = {"Любая марка", "Отечественные", "Иномарки"};
        assertThat(splitToArray(new RestRequest().getRequest().params("method", "all.mark.getList", "category_id", id).params("key", keyApi, "version", version).expect().statusCode(200).get("/rest").jsonPath()
                .get("result.groups.name").toString()), arrayContainingInAnyOrder(GROUPS_CONST_LIST));
    }

    @Test // geo_id = "213" МСК
    public void citySearchResultsCountGreaterThanZero() {
        assertThat(Integer.valueOf(
                new RestRequest().getRequest().params("method", "all.sale.countTotal", "category_id", id, "geo_id", "213").params("key", keyApi, "version", version)
                        .expect().statusCode(200).get("/rest").jsonPath().get("result.total_found").toString()),
                greaterThan(0));
    }

    @Test // geo_id=1 - МО
    public void regionSearchResultsCountGreaterThanZero() {
        assertThat(Integer.valueOf(
                new RestRequest().getRequest().params("method", "all.sale.countTotal", "category_id", id, "geo_id", "1").params("key", keyApi, "version", version)
                        .expect().statusCode(200).get("/rest").jsonPath().get("result.total_found").toString()),
                greaterThan(0));
    }

    @Test // geo_id = "213" МСК
    public void filteringSearchResultsByCity() {
        assertThat(
                Arrays.asList(splitToArray(new RestRequest().getRequest()
                        .params("method", "all.sale.search", "category_id", id, "geo_id", "213", "creation_date_to", cutTime).params("key", keyApi, "version", version)
                        .expect().statusCode(200).get("/rest").jsonPath().get("result.sales.poi.region").toString())),
                Every.everyItem(equalToIgnoringWhiteSpace("Москва")));
    }

    @Test // geo_id=1 - МО
    public void filteringSearchResultsByRegion() {
        assertThat(
                Arrays.asList(splitToArray(new RestRequest().getRequest()
                        .params("method", "all.sale.search", "category_id", id, "geo_id", "1", "creation_date_to", cutTime
                        ).params("key", keyApi, "version", version)
                        .expect().statusCode(200).get("/rest").jsonPath().get("result.sales.poi.region").toString())),
                Every.everyItem(anyOf(equalToIgnoringWhiteSpace("Москва"), equalToIgnoringWhiteSpace("Московская обл."))));
    }
}
