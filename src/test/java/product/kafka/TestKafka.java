package product.kafka;

import bean.Product;
import kafka.consumer.KafkaMessageConsumer;
import kafka.publisher.KafkaMessagePublisher;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestKafka {
    @Test
    public void sendKafkaMessage() {
        Product p = Product.builder().id(1).name("iphone 14 Pro").price(30L).build();

        KafkaMessagePublisher kafkaMessagePublisher = new KafkaMessagePublisher();
        RecordMetadata recordMetadata = kafkaMessagePublisher.sendMessage(p);
        Assert.assertTrue(recordMetadata.offset()>0,"Offset has not moved");

    }

   @Test
    public  void consumeMessages() {
       KafkaMessageConsumer kafkaMessageConsumer = new KafkaMessageConsumer();
       kafkaMessageConsumer.consume();
       Assert.assertTrue(kafkaMessageConsumer.receivedMessages()!=null, "No Mesages Received");
   }
}
