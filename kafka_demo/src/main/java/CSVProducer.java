import com.opencsv.CSVReader;
import kafka.security.auth.Read;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class CSVProducer {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "demo");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.17.80.20:9092"); // 172.17.80.20:9092
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String, String>(props);
        String topicName = "customer_phannt8";
        logger.info("Starting load...");
        try {
            File file = new File("/home/phannt8/Documents/masterdev/kafka_demo/data/customer.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while((line = br.readLine()) != null) {
                kafkaProducer.send(new ProducerRecord<>(topicName,null,line));
                logger.info(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        kafkaProducer.close();
        logger.info("Finish");
    }
}
