package TestCases;

import Utilities.ConfigurationReader;
import Utilities.Logger;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utilities.Util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GET_Tests extends Base {

    static org.apache.logging.log4j.Logger log = LogManager.getLogger();

    @Test(testName = "validate status code GET all items")
    public void test01() {
        Logger.logMessage("validate status code GET all items");
        String endpoint = ConfigurationReader.getProperties("skuEndpoint");
        Response response = getGiven().get(endpoint);
        Logger.logMessage("GET request sent to " + endpoint + " endpoint");

        checkStatusCode(response , 200);
    }

    @Test(testName = "validate status code GET by {id}")
    public void test02() {
        Logger.logMessage("validate status code GET by {id}");
        String endpoint = ConfigurationReader.getProperties("skuEndpoint");
        Response response = getGiven()
                .pathParam("id" , "JB7RKM52IHBC0I99M6KVHLKC9NVV4KQNSO5AEMVJF66Q9ASUAAJG")
                .get(endpoint + "/{id}");
        Logger.logMessage("GET request sent to " + endpoint + " endpoint");

        checkStatusCode(response , 200);

    }

    @Test(testName = "validate response body path \"HTTPHeaders.server\" GET by {id}")
    public void test03() {
        Logger.logMessage("validate response body path \"HTTPHeaders.server\" GET by {id}");
        String endpoint = ConfigurationReader.getProperties("skuEndpoint");
        Response response = getGiven()
                .pathParams("id" , "JB7RKM52IHBC0I99M6KVHLKC9NVV4KQNSO5AEMVJF66Q9ASUAAJG")
                .get(endpoint + "/{id}");
        Logger.logMessage("GET request sent to " + endpoint + " endpoint");

        bodyMatchIs(response , "ResponseMetadata.HTTPHeaders.server" , "Server");
    }

    @Test(testName = "validate number of entries with jelly donut as description is 169")
    public void test04() {
        Logger.logMessage("validate number of entries with jelly donut as description is 169");
        String endpoint = ConfigurationReader.getProperties("skuEndpoint");
        Response response = getRequest(endpoint);
        Logger.logMessage("GET request sent to " + endpoint + " endpoint");

        JsonPath jsonPath = response.jsonPath();

        List<Map<String , String>> allItems = jsonPath.getList("");
        List<Map<String , String>> jellyDonuts = new ArrayList<>();

        for (int i = 0; i < allItems.size(); i++) {
            try {
                if (allItems.get(i).get("description").equalsIgnoreCase("jelly donut")) {
                    jellyDonuts.add(allItems.get(i));
                }
            } catch (java.lang.ClassCastException e) {
                Logger.logMessage("invalid data type stored for description of item createdAt: " + allItems.get(i).get("createdAt"));
                allItems.remove(i--);
            }
        }

        long count = allItems.stream().filter(item -> item.get("description").equalsIgnoreCase("jelly donut")).count();

        Assert.assertTrue(jellyDonuts.stream().allMatch(item -> item.get("description").equalsIgnoreCase("jelly donut")));

        Assert.assertEquals(jellyDonuts.size() , count);
    }

    @Test(testName = "print the price of all the jelly donuts")
    public void test05() {
        Logger.logMessage("print the price of all the jelly donuts");
        String endpoint = ConfigurationReader.getProperties("skuEndpoint");
        Response response = getRequest(endpoint);
        Logger.logMessage("GET request sent to " + endpoint + " endpoint");

        JsonPath jsonPath = response.jsonPath();

        List<Map<String , String>> allItems = jsonPath.getList("");

        for (int i = 0; i < allItems.size(); i++) {
            try {
                if (allItems.get(i).get("description").equalsIgnoreCase("jelly donut")) {
                    Logger.logMessage(allItems.get(i).get("price"));
                }
            } catch (java.lang.ClassCastException e) {
                Logger.logMessage("invalid data type stored for description of item createdAt: " + allItems.get(i).get("createdAt"));
            }
        }
    }

}
