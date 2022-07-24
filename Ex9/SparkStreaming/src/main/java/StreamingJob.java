import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.kafka.common.errors.TimeoutException;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StreamingJob {
    public static void main(String[] args) throws StreamingQueryException, TimeoutException, java.util.concurrent.TimeoutException {
        SparkSession spark = SparkSession
                .builder()
                .appName("Spark streaming")
                //.master("local")
                .getOrCreate();

        Dataset<Row> df = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "172.17.80.26:9092")
                .option("subscribe", "data_tracking_phannt8")
                .option("group.id", "phannt8")
                .option("startingOffsets", "earliest")
                .option("auto.offset.reset", "true")
                .load();

        Dataset<String> df1 = df.select("value").as(Encoders.BINARY()).map((MapFunction<byte[], String>)
                        s -> Message.DataTracking.parseFrom(s).getVersion() + ","
                                + Message.DataTracking.parseFrom(s).getName() + ","
                                + Message.DataTracking.parseFrom(s).getTimestamp() + ","
                                + Message.DataTracking.parseFrom(s).getPhoneId() + ","
                                + Message.DataTracking.parseFrom(s).getLon() + ","
                                + Message.DataTracking.parseFrom(s).getLat() + ","

                                + getTime(Message.DataTracking.parseFrom(s).getTimestamp(), "yyyy") + ","
                                + getTime(Message.DataTracking.parseFrom(s).getTimestamp(), "MM") + ","
                                + getTime(Message.DataTracking.parseFrom(s).getTimestamp(), "dd") + ","
                                + getTime(Message.DataTracking.parseFrom(s).getTimestamp(), "HH")
                , Encoders.STRING());

        Dataset<Row> df2 = df1.withColumn("version", functions.split(df1.col("value"), ",").getItem(0))
                .withColumn("name", functions.split(df1.col("value"), ",").getItem(1))
                .withColumn("timestamp", functions.split(df1.col("value"), ",").getItem(2))
                .withColumn("phone_id", functions.split(df1.col("value"), ",").getItem(3))
                .withColumn("lon", functions.split(df1.col("value"), ",").getItem(4))
                .withColumn("lat", functions.split(df1.col("value"), ",").getItem(5))
                .withColumn("year", functions.split(df1.col("value"), ",").getItem(6))
                .withColumn("day", functions.split(df1.col("value"), ",").getItem(7))
                .withColumn("hour", functions.split(df1.col("value"), ",").getItem(8))
                .drop("value");

        StreamingQuery query = df2
                .writeStream()
                .outputMode("append")
                .format("parquet")
                .option("compression", "snappy")
                .option("checkpointLocation", "hdfs://172.17.80.21:9000/user/phannt8/data_tracking/checkpoint")
                .option("path", "/user/phannt8/data_tracking")
                .partitionBy("year", "day", "hour")
                .option("path", "hdfs://172.17.80.21:9000/user/phannt8/data_tracking/data")
                .start();

        query.awaitTermination();
        spark.streams().awaitAnyTermination(20000);

    }

    public static String getTime(long timestamp, String getBy) {
        long dateTimestamp = timestamp * 1000L;
        Date date = new java.util.Date(dateTimestamp);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat(getBy);
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+07:00"));
        return sdf.format(date);
    }
}
