package setup;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private Properties properties;


    public PropertiesReader() {
        if(properties == null) {
            properties = new Properties();
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public String getKey(String key) {
        if(properties!=null) return (String) properties.get(key);
        return null;
    }

}
