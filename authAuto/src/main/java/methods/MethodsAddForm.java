package methods;

import static methods.Constants.api;
import static methods.Utils.splitToArray;
import static methods.Utils.getRndInt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.jayway.restassured.response.Response;

public class MethodsAddForm {

    public static String randMarkID;
    public static String randModelID;
    public static String randYearID;
    public static String randGenerationsID;
    public static String randBodytypeID;
    public static String randEnginetypeID;
    public static String randDriveID;
    public static String randGearboxID;

    private static Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("category_id", 15);
        params.put("is_for_editform", 1);
        return params;
    }

    // Список марок
    public static Response markList() {
        return new RestRequest().getRequestAddForm().baseUri(api).parameters(getParams()).parameter("method", "all.mark.getList")
                .when().expect().statusCode(200).get("/rest");
    }

    // Список моделей
    public static Response modelList() {
        String[] markID = splitToArray(markList().body().jsonPath().get("result.items.id").toString());
        randMarkID = markID[getRndInt(markID.length)];
        return new RestRequest().getRequestAddForm().baseUri(api).parameters(getParams())
                .parameters("method", "catalog.folder.getEditModels", "mark_id", randMarkID)
                .when().expect().statusCode(200).get("/rest");
    }

    // Список годов выпуска
    public static Response yearList() {
        String[] modelID = splitToArray(modelList().body().jsonPath().get("result.id").toString());
        randModelID = modelID[getRndInt(modelID.length)];
        return new RestRequest().getRequestAddForm().baseUri(api).parameters(getParams()).parameters("method", "catalog.year.getList", "mark_id", randMarkID, "model_id", randModelID)
                .when().expect().statusCode(200).get("/rest");
    }

    // Список поколений
    public static Response generationsList() {
        String[] yearID = splitToArray(yearList().body().jsonPath().get("result.id").toString());
        randYearID = yearID[getRndInt(yearID.length)];
        return new RestRequest().getRequestAddForm().baseUri(api).parameters(getParams())
                .parameters("method", "catalog.folder.getEditGenerations", "mark_id", randMarkID, "model_id", randModelID, "year", randYearID).
                        when().expect().statusCode(200).get("/rest");
    }

    // Список типов кузова
    public static Response bodytypeList() {
        String[] generationsIDList = splitToArray(generationsList().body().jsonPath().get("result.id").toString());
        randGenerationsID = generationsIDList[getRndInt(generationsIDList.length)];
        return new RestRequest().getRequestAddForm().baseUri(api).parameters(getParams())
                .parameters("method", "catalog.bodytype.getList", "mark_id", randMarkID, "model_id", randModelID, "year", randYearID, "folder_id", randGenerationsID)
                .when().expect().statusCode(200).get("/rest");
    }

    // Список типов двигателя
    public static Response enginetypeList() {
        String[] bodytypeIDList = splitToArray(bodytypeList().body().jsonPath().get("result.id").toString());
        randBodytypeID = bodytypeIDList[getRndInt(bodytypeIDList.length)];
        return new RestRequest().getRequestAddForm().baseUri(api).parameters(getParams()).parameters("method", "catalog.enginetype.getList", "mark_id", randMarkID,
                "model_id", randModelID, "year", randYearID, "folder_id", randGenerationsID, "body_type", randBodytypeID)
                .when().expect().statusCode(200).get("/rest");
    }

    // Список типов приводов
    public static Response driveList() {
        String[] enginetypeIDList = splitToArray(enginetypeList().body().jsonPath().get("result.id").toString());
        randEnginetypeID = enginetypeIDList[getRndInt(enginetypeIDList.length)];
        return new RestRequest().getRequestAddForm().baseUri(api).parameters(getParams()).parameters("method", "catalog.drive.getList", "mark_id", randMarkID, "model_id", randModelID,
                "year", randYearID, "folder_id", randGenerationsID, "body_type", randBodytypeID, "engine_type", randEnginetypeID)
                .when().expect().statusCode(200).get("/rest");
    }

    // Список типов КПП
    public static Response gearboxList() throws IOException {
        String[] driveIDList = splitToArray(driveList().body().jsonPath().get("result.id").toString());
        randDriveID = driveIDList[getRndInt(driveIDList.length)];
        return new RestRequest().getRequestAddForm().baseUri(api).parameters(getParams()).parameters("method", "catalog.gearbox.getList", "mark_id", randMarkID, "model_id", randModelID,
                "year", randYearID, "folder_id", randGenerationsID, "body_type", randBodytypeID, "engine_type", randEnginetypeID, "drive", randDriveID)
                .when().expect().statusCode(200).get("/rest");
    }

    // Список модификаций
    public static Response modificationList() throws IOException {
        String[] gearboxIDList = splitToArray(gearboxList().body().jsonPath().get("result.id").toString());
        randGearboxID = gearboxIDList[getRndInt(gearboxIDList.length)];
        return new RestRequest().getRequestAddForm()
                .baseUri(api).parameters(getParams()).parameters("method", "catalog.modification.getList", "mark_id", randMarkID, "model_id", randModelID, "year", randYearID,
                        "folder_id", randGenerationsID, "body_type", randBodytypeID, "engine_type", randEnginetypeID, "drive", randDriveID, "gearbox", randGearboxID)
                .when().expect().statusCode(200).get("/rest");
    }
}
