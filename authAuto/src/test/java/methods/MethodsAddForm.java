package methods;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static autoTest.AutoTest.uuid;
import static autoTest.AutoTest.sid;
import static autoTest.AutoTest.api;
import static com.jayway.restassured.RestAssured.given;

public class MethodsAddForm {
    public static Random rand = new Random();
    public static String randMarkID;
    public static String randModelID;
    public static String randYearID;
    public static String randGenerationsID;
    public static String randBodytypeID;

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
}
