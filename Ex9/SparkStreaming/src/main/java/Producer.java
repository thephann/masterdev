import com.github.javafaker.Faker;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Producer {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.17.80.26:9092");
        props.put(KafkaProtobufSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://172.17.80.26:8081");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaProtobufSerializer.class);

        KafkaProducer<String,Message.DataTracking> kafkaProducer = new KafkaProducer<>(props);

        String topic = "random-data";
        logger.info("Starting load data ...");
        Faker faker = new Faker(new Locale("vi"));
        for (int i=0;i<100;i++) {
            String version = "version "+faker.number().numberBetween(1,10)+"."+faker.number().numberBetween(1,10);
            String name = faker.name().fullName();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            Timestamp tp = new Timestamp(System.nanoTime());
            long timest = Long.parseLong(sdf1.format(tp));

            Message.DataTracking dataTracking = Message.DataTracking.newBuilder()
                    .setVersion(version)
                    .setName(name)
                    .setTimestamp(timest)
                    .setPhoneId(String.valueOf(faker.number().randomNumber()))
                    .setLon(128670541)
                    .setLat(286705410)
                    .build();
            ProducerRecord<String, Message.DataTracking> record
                    = new ProducerRecord<>(topic, null, dataTracking);
            kafkaProducer.send(record);

        }
        kafkaProducer.flush();
        kafkaProducer.close();
        System.out.println("Successfully");

    }
}
