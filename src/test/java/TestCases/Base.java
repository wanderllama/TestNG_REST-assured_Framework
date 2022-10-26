package TestCases;

import Utilities.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Base {
    @BeforeMethod
    public void setup() {
        Logger.logMessage("----- start -----");
    }

    @AfterMethod
    public void teardown() {
        Logger.logMessage("------ end ------\n");
    }
}
