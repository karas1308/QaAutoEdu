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
import static methods.MethodsAddForm.*;
import static methods.Utils.prt;
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
        assertThat(splitToArray(generationsList().body().jsonPath().get("result.id").toString()).length, greaterThan(0));
    }

    @Test
    public void bodytype_list() throws IOException {
        assertThat(splitToArray(bodytypeList().body().jsonPath().get("result.id").toString()).length, greaterThan(0));
    }

    @Test
    public void enginetypeGetList() throws IOException {
        assertThat(splitToArray(enginetypeList().body().jsonPath().get("result.id").toString()).length, greaterThan(0));
    }

    @Test
    public void driveGetList() throws IOException {
        assertThat(splitToArray(driveList().body().jsonPath().get("result.id").toString()).length, greaterThan(0));
    }

    @Test
    public void gearboxGetLis() throws IOException {
         assertThat(splitToArray(gearboxList().body().jsonPath().get("result.id").toString()).length, greaterThan(0));
    }

    @Test
    public void modification_list() throws IOException {
        assertThat(splitToArray(modificationList().body().jsonPath().get("result.id").toString()).length, greaterThan(0));

    }

    // Количиство ошибок валидации при размещении объявления 19
    @Test
    public void quantityErrorsCountAddForm() {
        assertThat(Arrays.asList(splitToArray(new RestRequest().getRequestAddForm().params("method", "all.sale.add", "category_id", 15)
                        .expect().statusCode(200).post("/rest").jsonPath().get("result.errors").toString())),
                hasSize(19));
    }

//    @Test
//    public void quantityErrorsCountAddForm1() throws IOException {
//        modificationList();
//        prt ( randMarkID+ " / " +randModelID+ " / " +randYearID+ " / " +randGenerationsID+ " / " +randBodytypeID+ " / " +randEnginetypeID+ " / " +randDriveID+ " / " +randGearboxID );
//    }
}
