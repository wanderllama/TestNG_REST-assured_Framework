package TestCases;

import Utilities.ConfigurationReader;
import Utilities.Logger;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static Utilities.Util.*;

public class POST_Tests extends Base {

    @Test(testName = "validate status code POST item")
    public void test01() {
        Logger.logMessage("validate status code POST item");
        String endpoint = ConfigurationReader.getProperties("skuEndpoint");

        // using map as the request body, can use json file or POJO to send body in POST request
        Map<String , String> requestBody = new HashMap<>();
        requestBody.put("sku" , "berliner");
        requestBody.put("description" , "jelly donut");
        requestBody.put("price" , "2.99");

        Response response = postRequest(endpoint , requestBody);

        Logger.logMessage("POST request sent to " + endpoint + " endpoint");
        checkStatusCode(response , 201);
    }

}