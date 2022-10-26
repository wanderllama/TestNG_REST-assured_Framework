package Utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.ResourceBundle;

import static org.hamcrest.Matchers.*;

public class Util {

    public static RequestSpecification getGiven() {
        RestAssured.baseURI = ConfigurationReader.getProperties("baseURI");
        RestAssured.basePath = ConfigurationReader.getProperties("basePath");
        return RestAssured.given();
    }

    public static void checkStatusCode(Response response , int statusCode) {
        Logger.logMessage("expected status code " + statusCode);
        Logger.logMessage("actual status code " + response.getStatusCode());
        response.then().assertThat().statusCode(statusCode);
    }

    public static Response getRequest(String endpoint) {
        return getGiven().get(endpoint);
    }

    public static Response postRequest(String endpoint , Map<String , String> requestBody) {
        return getGiven()
                .accept(ContentType.JSON)
                .body(requestBody)
                .post(endpoint);
    }

    public static Response deleteRequest(String endpoint , String id) {
        return getGiven()
                .pathParam("id" , id)
                .delete(endpoint + "/{id}");
    }

    public static void bodyMatchIs(Response response , String path , String matcher) {
        response.then().assertThat().body(path , is(matcher));
        Logger.logMessage("the value of " + path + " equals " + matcher);
    }

}
