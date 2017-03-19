package autoTest.api;

import static methods.ArrayContainsSubArray.containsSubArray;
import static methods.FirstConnect.getUuidSidAuth;
import static methods.FirstConnect.cutTime;
import static methods.Utils.splitToArray;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.Assert.assertThat;
import static methods.Constants.LIGHT_TRUCKS;
import static methods.Constants.TRUCKS;
import static methods.Constants.BUS;
import static methods.Constants.DRAGS;
import static methods.Constants.ARTIC;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.hamcrest.core.Every;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import autoTest.exp.RestRequest;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Api PHP")
@Stories("Filtering search results by different regions and categories")
@RunWith(value = Parameterized.class)
public class CommTest {

    @BeforeClass
    public static void before() throws IOException {
        getUuidSidAuth();
    }

    String[] marksConstListLightTrucks = {"BAW", "Changan", "Chevrolet", "Citroen", "DFSK", "Dodge", "FAW", "Fiat", "Ford", "Foton", "Freightliner", "Hyundai", "Isuzu", "IVECO", "JAC",
            "JBC", "JMC", "Kia", "LDV", "Mazda", "Mercedes-Benz", "Mitsubishi", "Nissan", "Opel", "Peugeot", "Renault", "Skoda", "Ssang Yong", "Toyota", "Volkswagen", "YueJin",
            "Богдан", "ВАЗ", "ВИС", "ГАЗ", "ГАЗ-САЗ", "ИЖ", "РАФ", "ТагАЗ", "УАЗ"};
    String[] marksConstListBus = {"Volkswagen"};
    String[] marksConstListArtic = {"MAN"};
    String[] marksConstListDrags = {"МАЗ"};
    String[] marksConstListTrucks = {"МАЗ"};

    private Integer id;

    @Parameterized.Parameters
    public static Collection<Object[]> categoryId() {
        return Arrays.asList(new Object[][]{{ARTIC}, {DRAGS}, {BUS}, {TRUCKS}, {LIGHT_TRUCKS}});
    }

    public CommTest(Integer id) {
        this.id = id;
    }

    @Test
    public void marksListIsDsiplayed() {
        if (id == ARTIC) {
            assertThat(splitToArray(new RestRequest().getRequest().params("method", "all.mark.getList", "category_id", id).expect().statusCode(200).get("/rest").jsonPath()
                    .get("result.items.name").toString()), containsSubArray(marksConstListArtic));
        }
        if (id == DRAGS) {
            assertThat(splitToArray(new RestRequest().getRequest().params("method", "all.mark.getList", "category_id", id).expect().statusCode(200).get("/rest").jsonPath()
                    .get("result.items.name").toString()), containsSubArray(marksConstListDrags));
        }
        if (id == BUS) {
            assertThat(splitToArray(new RestRequest().getRequest().params("method", "all.mark.getList", "category_id", id).expect().statusCode(200).get("/rest").jsonPath()
                    .get("result.items.name").toString()), containsSubArray(marksConstListBus));
        }
        if (id == TRUCKS) {
            assertThat(splitToArray(new RestRequest().getRequest().params("method", "all.mark.getList", "category_id", id).expect().statusCode(200).get("/rest").jsonPath()
                    .get("result.items.name").toString()), containsSubArray(marksConstListTrucks));
        }
        if (id == LIGHT_TRUCKS) {
            assertThat(splitToArray(new RestRequest().getRequest().params("method", "all.mark.getList", "category_id", id).expect().statusCode(200).get("/rest").jsonPath()
                    .get("result.items.name").toString()), containsSubArray(marksConstListLightTrucks));
        }
    }

    @Test
    public void markGroupsListIsDsiplayed() {
        String[] groupsConstList = {"Любая марка", "Отечественные", "Иномарки"};
        assertThat(splitToArray(new RestRequest().getRequest().params("method", "all.mark.getList", "category_id", id).expect().statusCode(200).get("/rest").jsonPath()
                .get("result.groups.name").toString()), arrayContainingInAnyOrder(groupsConstList));
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
