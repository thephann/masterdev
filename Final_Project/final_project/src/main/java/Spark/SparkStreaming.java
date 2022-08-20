package Spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQueryException;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.split;

import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

public class SparkStreaming {
    public static void main(String[] args) throws TimeoutException, StreamingQueryException {
        SparkSession spark = SparkSession
                .builder()
                .appName("Netflix Spark ")

//                .master("local")
//                .config("spark.driver.bindAddress", "127.0.0.1")
                .config("spark.scheduler.mode", "FAIR")
                .getOrCreate();
        spark.sparkContext().setLogLevel("ERROR");


        Dataset<Row> ds = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "192.168.5.102:9092")
                .option("subscribe", "netflix")
                .option("startingOffsets", "earliest")
                .load();

        Dataset<Row> DF =
                ds.selectExpr("CAST(value as string)")
                        .select(split(col("value"),",").getItem(0).as("show_id"),
                                split(col("value"),",").getItem(1).as("type"),
                                split(col("value"),",").getItem(2).as("title"),
                                split(col("value"),",").getItem(3).as("director"),
                                split(col("value"),",").getItem(4).as("country"),
                                split(col("value"),",").getItem(5).as("date_added"),
                                split(col("value"),",").getItem(6).as("release_year"),
                                split(col("value"),",").getItem(7).as("rating"),
                                split(col("value"),",").getItem(8).as("duration"),
                                split(col("value"),",").getItem(9).as("genre"),
                                split(col("value"),",").getItem(10).as("description"),
                                split(col("value"),",").getItem(11).as("year_added"),
                                split(col("value"),",").getItem(12).as("month_added"),
                                split(col("value"),",").getItem(13).as("season_count")

                        );
//        DF.show(10);

        DF.writeStream()
                .option("partition",1)
                .outputMode("append")
                .format("parquet")
                //hdfs://172.17.80.21:9000/user/phannt8/btl/checkpoint
                .option("checkpointLocation", "hdfs://192.168.5.102:9000/user/phannt8/btl/checkpoint")
                ///home/phannt8/Documents/masterdev/Final_Project/final_project/phannt8/checkpoint
                //hdfs://172.17.80.21:9000/user/phannt8/btl/output
                .option("path", "hdfs://192.168.5.102:9000/user/phannt8/btl/output")
                ///home/phannt8/Documents/masterdev/Final_Project/final_project/phannt8/output
                .start().awaitTermination();
    }
}

