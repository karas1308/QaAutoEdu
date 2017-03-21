package autoTest.api;

import com.jayway.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static methods.FirstConnect.getUuidSidAuth;
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

}
