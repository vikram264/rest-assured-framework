package kafka.consumer;

import bean.Product;
import kafka.deserializer.ProductValueDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import setup.PropertiesReader;

import java.time.Duration;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

@Slf4j
public class KafkaMessageConsumer {
    Queue<Product> queue;

    public KafkaMessageConsumer() {
        this.queue = new LinkedList<>();
    }

    public void consume() {
        PropertiesReader propertiesReader = new PropertiesReader();
        String bootstrapServers = propertiesReader.getKey("kafka.bootstrap.servers");
        String groupId = propertiesReader.getKey("kafka.consumer.groupId");
        String topic = propertiesReader.getKey("kafka.topic ");

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ProductValueDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, Product> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singleton(topic));

        while (true) {
            ConsumerRecords<String, Product> record = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, Product> r : record) {
                Product product = r.value();
                log.info("Received product id {} , product name {} , product price {} ", product.getId(), product.getName(), product.getPrice() );
                queue.offer(product);
            }
        }
    }

    public Queue<Product> receivedMessages() {
        return queue;
    }
}

