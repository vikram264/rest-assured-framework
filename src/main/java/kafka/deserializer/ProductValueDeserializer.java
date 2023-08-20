package kafka.deserializer;

import bean.Product;
import org.apache.kafka.common.serialization.Deserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class ProductValueDeserializer implements Deserializer<Product> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Additional configuration if needed
    }

    @Override
    public Product deserialize(String topic, byte[] data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(data, Product.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing product", e);
        }
    }

    @Override
    public void close() {
        // Clean up resources if needed
    }
}
