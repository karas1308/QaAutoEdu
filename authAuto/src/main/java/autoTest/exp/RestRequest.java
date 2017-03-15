package autoTest.exp;

import static methods.FirstConnect.key;
import static methods.FirstConnect.password;
import static methods.FirstConnect.sid;
import static methods.FirstConnect.username;
import static methods.FirstConnect.uuid;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;

public class RestRequest extends RestAssured {

    RequestSpecification r = given();
    
    public RequestSpecification getRequest() {
        return r.parameters("sid", sid, "key", key, "version", "2.2.2", "uuid", uuid, "format", "json").header("Accept-Encoding", "gzip");
    }
    
    public RequestSpecification getRequestApi2() {
        return r.parameters("sid", sid, "key", key, "version", "2.2.2", "uuid", uuid, "format", "json").header("Accept-Encoding", "gzip");
    }
    
    public RequestSpecification getRequestAuth() {
        return r.parameters("sid", sid, "key", key, "version", "2.2.2", "uuid", uuid, "format", "json").header("Accept-Encoding", "gzip");
    }

}
