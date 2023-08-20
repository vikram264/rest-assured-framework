package kafka.serializer;

import bean.Product;

import org.apache.kafka.common.serialization.Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class ProductValueSerializer implements Serializer<Product> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Configure the serializer if needed
    }

    @Override
    public byte[] serialize(String topic, Product data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing product", e);
        }
    }

    @Override
    public void close() {
        // Clean up resources
    }
}