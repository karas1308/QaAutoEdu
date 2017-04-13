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

//@Features("Api PHP")
//@Stories("Filtering search results by different regions and categories")
@RunWith(value = Parameterized.class)
public class CommTest {

    @BeforeClass
    public static void before() throws IOException {
        getUuidSidAuth();
    }

    static String[] marksConstListLightTrucks = {"BAW", "Changan", "Citroen", "DFSK", "Dodge", "FAW", "Fiat", "Ford", "Foton", "Hyundai", "Isuzu", "IVECO", "JAC",
            "JBC", "JMC", "Kia", "LDV", "Mazda", "Mercedes-Benz", "Mitsubishi", "Nissan", "Opel", "Peugeot", "Renault", "Ssang Yong", "Toyota", "Volkswagen", "YueJin",
            "Богдан", "ВАЗ", "ВИС", "ГАЗ", "ГАЗ-САЗ", "ИЖ", "РАФ", "ТагАЗ", "УАЗ"};
    static String[] marksConstListBus = {"Volkswagen"};
    static String[] marksConstListArtic = {"MAN"};
    static String[] marksConstListDrags = {"МАЗ"};
    static String[] marksConstListTrucks = {"МАЗ"};
    static String[] marksConstListMotorcycle = {"BMW", "CZ", "Derbi", "Dnepr (Днепр)", "Ducati", "Harley-Davidson", "Honda", "Kawasaki", "Minsk (Минск)", "Восход", "Десна", "ИЖ",  "Урал", "Эксклюзив"};
    static String[] marksConstListScooters = {"BMW"};
    static String[] marksConstListSnowmobile = {"Stels"};
    static String[] marksConstListAtv = {"IRBIS"};

    @Parameter
    private Integer id;

    @Parameter
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
