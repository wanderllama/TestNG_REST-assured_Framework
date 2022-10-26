package TestCases;

import Utilities.ConfigurationReader;
import Utilities.Logger;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utilities.Util.checkStatusCode;
import static Utilities.Util.deleteRequest;

public class DELETE_Tests {

    @Test(testName = "validate status code DELETE request")
    public void test01() {
        Logger.logMessage("validate status code DELETE request");
        String endpoint = ConfigurationReader.getProperties("skuEndpoint");
        String id = "2K5NO8FU4M803TPISH1EABVJOJVV4KQNSO5AEMVJF66Q9ASUAAJG";
        Response response = deleteRequest(endpoint , id);

        Logger.logMessage("DELETE request sent to " + endpoint + "/" + id);

        // delete response has no content -> why I used status code 204
        checkStatusCode(response , 204);
    }

}
