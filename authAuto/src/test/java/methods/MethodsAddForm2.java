package methods;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import java.io.IOException;
import java.util.Random;

import static methods.FirstConnect.api;
import static methods.FirstConnect.sid;
import static methods.FirstConnect.uuid;
import static com.jayway.restassured.RestAssured.given;

public class MethodsAddForm2 {
    public static Random rand = new Random();
    public static String randMarkID;
    public static String randModelID;
    public static String randYearID;
    public static String randGenerationsID;
    public static String randBodytypeID;
    public static String randEnginetypeID;
    public static String randDriveID;
    public static String randGearboxID;


    //Список марок
    public static Response markList() {
        String method = "all.mark.getList";
        RestAssured.baseURI = api;
        Response markList = given().
                header("Accept-Encoding", "gzip").
                get("/rest/?method=" + method + "&category_id=15&" +
                        "is_for_editform=1&sid=" + sid + "&client_tz=120&key=b7bf0dfc8cc562c1bf2cffdd9e78fc181f97f6c82f85fbca16d62d3d3258963c" +
                        "&version=2.2.2&uuid=" + uuid + "&format=json");
        return markList;
    }

    //Список моделей
    public static Response modelList() {
        Response markList = markList();
        String[] markID = markList.body().jsonPath().get("result.items.id").toString().replace("[", "").replace("]", "").split(",");
        int a = rand.nextInt(markID.length);
        randMarkID = markID[a];
        String method = "catalog.folder.getEditModels";
        RestAssured.baseURI = api;
        Response modelList = given().
                header("Accept-Encoding", "gzip").
                get("/rest/?method=" + method + "&category_id=15&" +
                        "is_for_editform=1&sid=" + sid + "&mark_id=" + randMarkID + "&client_tz=120&key=b7bf0dfc8cc562c1bf2cffdd9e78fc181f97f6c82f85fbca16d62d3d3258963c" +
                        "&version=2.2.2&uuid=" + uuid + "&format=json");
        return modelList;
    }

    //Список годов выпуска
    public static Response yearList() throws IOException {
        Response modelList = modelList();
        String[] modelID = modelList.body().jsonPath().get("result.id").toString().replace("[", "").replace("]", "").split(",");
        int a = rand.nextInt(modelID.length);
        randModelID = modelID[a];
        String method = "catalog.year.getList";
        Response yearList = given().
                header("Accept-Encoding", "gzip").
                get("/rest/?method=" + method + "&category_id=15&" +
                        "is_for_editform=1&sid=" + sid + "&mark_id=" + randMarkID + "&model_id=" + randModelID + "&client_tz=120&key=b7bf0dfc8cc562c1bf2cffdd9e78fc181f97f6c82f85fbca16d62d3d3258963c" +
                        "&version=2.2.2&uuid=" + uuid + "&format=json");

        return yearList;
    }

    //Список поколений
    public static Response generationsList() throws IOException {
        Response yearList = yearList();
        String[] yearID = yearList.body().jsonPath().get("result.id").toString().replace("[", "").replace("]", "").split(",");
        int a = rand.nextInt(yearID.length);
        randYearID = yearID[a];
        String method = "catalog.folder.getEditGenerations";
        Response generationsList = given().
                header("Accept-Encoding", "gzip").
                get("/rest/?method=" + method + "&category_id=15&" +
                        "is_for_editform=1&sid=" + sid + "&mark_id=" + randMarkID + "&model_id=" + randModelID + "&year=" + randYearID + "&client_tz=120&key=b7bf0dfc8cc562c1bf2cffdd9e78fc181f97f6c82f85fbca16d62d3d3258963c" +
                        "&version=2.2.2&uuid=" + uuid + "&format=json");
        return generationsList;
    }

    //Список типов кузова
    public static Response bodytypeList() throws IOException {
        Response generationsList = generationsList();
        String[] generationsIDList = generationsList.body().jsonPath().get("result.id").toString().replace("[", "").replace("]", "").split(",");
        int a = rand.nextInt(generationsIDList.length);
        randGenerationsID = generationsIDList[a];
        String method = "catalog.bodytype.getList";
        Response bodytypeList = given().
                header("Accept-Encoding", "gzip").
                get("/rest/?method=" + method + "&category_id=15&" +
                        "is_for_editform=1&sid=" + sid + "&mark_id=" + randMarkID + "&model_id=" + randModelID +
                        "&year=" + randYearID + "&folder_id=" + randGenerationsID + "&client_tz=120&key=b7bf0dfc8cc562c1bf2cffdd9e78fc181f97f6c82f85fbca16d62d3d3258963c" +
                        "&version=2.2.2&uuid=" + uuid + "&format=json");
        return bodytypeList;
    }

    //Список типов двигателя
    public static Response enginetypeList() throws IOException {
        Response bodytypeList = bodytypeList();
        String[] bodytypeIDList = bodytypeList.body().jsonPath().get("result.id").toString().replace("[", "").replace("]", "").split(",");
        int a = rand.nextInt(bodytypeIDList.length);
        randBodytypeID = bodytypeIDList[a];
        String method = "catalog.enginetype.getList";
        Response enginetypeList = given().
                header("Accept-Encoding", "gzip").
                get("/rest/?method=" + method + "&category_id=15&" +
                        "is_for_editform=1&sid=" + sid + "&mark_id=" + randMarkID + "&model_id=" + randModelID +
                        "&year=" + randYearID + "&folder_id=" + randGenerationsID + "&body_type="+randBodytypeID+"&client_tz=120&key=b7bf0dfc8cc562c1bf2cffdd9e78fc181f97f6c82f85fbca16d62d3d3258963c" +
                        "&version=2.2.2&uuid=" + uuid + "&format=json");
        return enginetypeList;
    }
    //Список типов приводов
    public static Response driveList() throws IOException {
        Response enginetypeList = enginetypeList();
        String[] enginetypeIDList = enginetypeList.body().jsonPath().get("result.id").toString().replace("[", "").replace("]", "").split(",");
        int a = rand.nextInt(enginetypeIDList.length);
        randEnginetypeID = enginetypeIDList[a];
        String method = "catalog.drive.getList";
        Response driveList = given().
                header("Accept-Encoding", "gzip").
                get("/rest/?method=" + method + "&category_id=15&" +
                        "is_for_editform=1&sid=" + sid + "&mark_id=" + randMarkID + "&model_id=" + randModelID +
                        "&year=" + randYearID + "&folder_id=" + randGenerationsID + "&body_type="+randBodytypeID+"&engine_type="+randEnginetypeID+"&client_tz=120&key=b7bf0dfc8cc562c1bf2cffdd9e78fc181f97f6c82f85fbca16d62d3d3258963c" +
                        "&version=2.2.2&uuid=" + uuid + "&format=json");
        return driveList;
    }
    //Список типов КПП
    public static Response gearboxList() throws IOException {
        Response driveList = driveList();
        String[] driveIDList = driveList.body().jsonPath().get("result.id").toString().replace("[", "").replace("]", "").split(",");
        int a = rand.nextInt(driveIDList.length);
        randDriveID = driveIDList[a];
        String method = "catalog.gearbox.getList";
        Response gearboxList = given().
                header("Accept-Encoding", "gzip").
                get("/rest/?method=" + method + "&category_id=15&" +
                        "is_for_editform=1&sid=" + sid + "&mark_id=" + randMarkID + "&model_id=" + randModelID +
                        "&year=" + randYearID + "&folder_id=" + randGenerationsID + "&body_type="+randBodytypeID+"&engine_type="+randEnginetypeID+"&drive="+randDriveID+"&client_tz=120&key=b7bf0dfc8cc562c1bf2cffdd9e78fc181f97f6c82f85fbca16d62d3d3258963c" +
                        "&version=2.2.2&uuid=" + uuid + "&format=json");
        return gearboxList;
    }

    //Список модификаций
    public static Response modificationList() throws IOException {
        Response gearboxList = gearboxList();
        String[] gearboxIDList = gearboxList.body().jsonPath().get("result.id").toString().replace("[", "").replace("]", "").split(",");
        int a = rand.nextInt(gearboxIDList.length);
        randGearboxID = gearboxIDList[a];
        String method = "catalog.modification.getList";
        Response modificationList = given().
                header("Accept-Encoding", "gzip").
                get("/rest/?method=" + method + "&category_id=15&" +
                        "is_for_editform=1&sid=" + sid + "&mark_id=" + randMarkID + "&model_id=" + randModelID +
                        "&year=" + randYearID + "&folder_id=" + randGenerationsID + "&body_type="+randBodytypeID+"&engine_type="+randEnginetypeID+"&drive="+randDriveID+
                        "&gearbox="+randGearboxID+"&client_tz=120&key=b7bf0dfc8cc562c1bf2cffdd9e78fc181f97f6c82f85fbca16d62d3d3258963c" +
                        "&version=2.2.2&uuid=" + uuid + "&format=json");
        return modificationList;
    }
}
