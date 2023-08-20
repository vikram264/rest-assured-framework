package product.api;

import org.testng.annotations.BeforeSuite;
import setup.PropertiesReader;
import setup.URLComponent;

public class BaseTest {
    @BeforeSuite
    public void beforeSuite() {
        PropertiesReader propertiesReader = new PropertiesReader();
        URLComponent url = new URLComponent(propertiesReader.getKey("base.uri"),propertiesReader.getKey("base.path"));
    }
}
