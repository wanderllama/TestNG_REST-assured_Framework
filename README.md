# API Testing For Maintenance of Stock Keeping Unit Identifiers (SKUs)  

### How To Run From Command Line
navigate to the directory of the project  
__cd /path/of/project__

use following command to execute test suite defined in Runner class  
__mvn test -Dsurefire.suiteXmlFiles=testng.xml__  

### Description of framework
This is a __testNG__ framework using __REST-assured__ libraries to create test cases to properly test a REST API  

The frameworks test cases are broken into categories depending on the HTTP method used in the request. There is a hooks class making use of testNG __@BeforeMethod__ and __@AfterMethod__ and run before and after each test case. These are located in the Base class  

The Utilities package has a __ConfigurationReader__ class. This class is responsible for accessing data stored in the config.properties file. Dynamic data that is used in multiple test cases and has a chance of being changed can be stored here in key value pairs and accessed using the __getProperties()__ method located in the ConfigurationReader class.  

The framework is using __log4j2__ to generate log messages to be displayed in the terminal. The Logger class is where the Logger object and __logMessage()__ method is located. This method takes a single argument in the form of a String representing a message to be displayed in the terminal. The properties for log4j2 are stored in the resources folder in __log4j2.properties__.  

The __surefire plugin__ is used to display these messages in the terminal and is responsible for the command to execute the test cases from the command line (__mvn test -Dsurefire.suiteXmlFiles=testng.xml__).

The __Util__ class is where reusable methods are saved to be used in the test cases. This makes the test cases easier to read and improves the usability of the REST-assured libraries.  

### Description of API
The development team for a retail organization has built an API intended to be used for the
maintenance of Stock Keeping Unit identifiers (__SKUs__) which are used to identify and track the
items the company has for sale.

This API implements the basic __CRUD__ operations

Create/Update operations are handled through HTTP __POST__
- Expected __POST__ body has __sku__, __description__, and __price__  
  {  
  "sku": "berliner",  
  "description": "Jelly donut",  
  "price": "2.99"  
  }

Read operations are handled through HTTP __GET__

Delete operations are handled through HTTP __DELETE__