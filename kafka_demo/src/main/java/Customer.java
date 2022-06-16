import com.opencsv.CSVWriter;
import kafka.security.auth.Read;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class Customer {

    public static void main(String[] args) throws IOException {

        Properties props = new Properties();
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "demo");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.17.80.20:9092"); // 172.17.80.20:9092
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
        File outputFile = new File("/home/phannt8/Documents/masterdev/kafka_demo/data/output.csv");
        FileWriter fw = new FileWriter(outputFile);
        CSVWriter writer = new CSVWriter(fw);


        try (kafkaConsumer) {
            kafkaConsumer.subscribe(Arrays.asList("customer_phannt8"));
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    String[] col = record.value().split(",");

                    try {

                        if (Integer.parseInt(col[1]) > 100 && Integer.parseInt(col[2]) < 20) {
                            System.out.println(record.value());
                            writer.writeNext(col);
                            writer.flush();
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }


            }
        }


    }
}


