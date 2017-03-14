package autoTest.exp;

import static autoTest.FirstConnectJson.key;
import static autoTest.FirstConnectJson.sid;
import static autoTest.FirstConnectJson.uuid;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;

public class RestRequest extends RestAssured {

    RequestSpecification r = given();

    public RestRequest() {
        r.parameters("sid", sid, "key", key, "version", "2.2.2", "uuid", uuid, "format", "json").header("Accept-Encoding", "gzip");
    }

    public RequestSpecification getRequest() {
        return r;
    }

}
