package kafka.publisher;

import bean.Product;
import kafka.serializer.ProductValueSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import setup.PropertiesReader;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class KafkaMessagePublisher {

    public RecordMetadata sendMessage(Product p) {
        PropertiesReader propertiesReader = new PropertiesReader();
        String bootstrapServers = propertiesReader.getKey("kafka.bootstrap.servers");
        String topic = propertiesReader.getKey("kafka.topic");

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ProductValueSerializer.class.getName()); // Set your custom value serializer class

        KafkaProducer<String, Product> producer = new KafkaProducer<>(properties);

        try {
            ProducerRecord<String, Product> record = new ProducerRecord<>(topic, p);
            Future<RecordMetadata> recordMetadataFuture = producer.send(record);
            return recordMetadataFuture.get(1, TimeUnit.MINUTES);


        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        } finally {
            producer.close();
        }

    }
}
