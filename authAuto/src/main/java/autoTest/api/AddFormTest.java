package autoTest.api;

import com.jayway.restassured.response.Response;
import methods.RestRequest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static methods.ArrayContainsSubArray.containsSubArray;
import static methods.FirstConnect.getUuidSidAuth;
import static methods.FirstConnect.key;
import static methods.MethodsAddForm.*;
import static methods.Utils.splitToArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;


public class AddFormTest {
    @BeforeClass
    public static void before() {
        getUuidSidAuth();
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
    }

    // Количиство ошибок валидации при размещении объявления 19
    @Test
    public void quantityErrorsCountAddForm() {
        Assert.assertThat(
                Arrays.asList(splitToArray(new RestRequest().getRequest().params("key", key, "method", "all.sale.add", "category_id", 15)
                        .expect().statusCode(200).post("/rest").jsonPath().get("result.errors").toString())),
                hasSize(19));
    }
}
