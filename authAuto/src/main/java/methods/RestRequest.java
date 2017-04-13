package methods;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;

import static methods.Constants.api;
import static methods.Constants.api2;
import static methods.FirstConnect.*;

public class RestRequest extends RestAssured {

    public RequestSpecification getRequest() {
        return given().baseUri(api).parameters("sid", sid, "uuid", uuid, "format", "json").header("Accept-Encoding", "gzip").header("Accept-Encoding", "gzip");
    }
    public RequestSpecification getRequestAddForm() {
        return given().baseUri(api).parameters("sid", sid, "uuid", uuid, "key", keyAPI222, "version", "2.2.2", "format", "json").header("Accept-Encoding", "gzip").header("Accept-Encoding", "gzip");
    }
    public RequestSpecification getRequestTest() {
        return given().baseUri(api).parameters("sid", sid, "uuid", uuid, "format", "json").header("Accept-Encoding", "gzip");
    }
    
    public RequestSpecification getRequestApi2() {
        return given().baseUri(api2).headers("Authorization", uuid_header, "X-Authorization", x_auth);
    }
    
    public RequestSpecification getRequestAuth() {
        return given().baseUri(api).parameters("sid", auth_sid, "uuid", uuid, "format", "json").header("Accept-Encoding", "gzip");
    }

}
