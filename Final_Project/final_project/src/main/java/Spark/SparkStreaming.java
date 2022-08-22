package Spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.StructType;
import scala.collection.Seq;

import java.util.HashMap;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import static org.apache.spark.sql.functions.*;

public class SparkStreaming {
//    public static Dataset<Row> clean_data(Dataset<Row> JobsDF){
//        String [] colNames = {"director"};
//        Dataset<Row> New_Data=JobsDF.drop("cast");
////        Dataset<Row> df = New_Data.na().drop("any", colNames);
//        Dataset<Row> df = New_Data.na().fill("Missing",colNames);
////        df = df.na().fill("Missing", new String[]{"country","date_added","rating","duration"});
////        df.withColumn("date_added",to_timestamp(New_Data.col("date_added"),"yyyy-MM-dd"));
//        return df;
//    }
    public static void main(String[] args) throws TimeoutException, StreamingQueryException {
        SparkSession spark = SparkSession
                .builder()
                .appName("Netflix Spark ")

//                .master("local")
//                .config("spark.driver.bindAddress", "127.0.0.1")
                .config("spark.scheduler.mode", "FAIR")
                .getOrCreate();
        spark.sparkContext().setLogLevel("ERROR");
        ReadStream(spark);
    }
    private static void ReadStream(SparkSession spark) throws StreamingQueryException {
        StructType sc = new StructType()
                .add("show_id", "STRING")
                .add("type", "STRING")
                .add("title", "STRING")
                .add("director", "STRING")
                .add("country", "STRING")
                .add("date_added", "STRING")
                .add("release_year", "STRING")
                .add("rating", "STRING")
                .add("duration", "STRING")
                .add("genre", "STRING")
                .add("description", "STRING")
                .add("year_added", "STRING")
                .add("month_added", "STRING")
                .add("season_count", "STRING");

        Dataset<Row> ds = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "192.168.193.93:9092")
                .option("subscribe","netflix-1")
                .option("startingOffsets", "earliest")
                .load();

        Dataset<Row> df1 = ds.selectExpr("CAST (value AS STRING)");

        Dataset<Row> df2 = df1.withColumn("value", functions.from_json(df1.col("value"), sc ,new HashMap<>()));

        Dataset<Row> DF =
                df2.select(df2.col("value.show_id"),
                        df2.col("value.type"),
                        df2.col("value.title"),
                        df2.col("value.director"),
                        df2.col("value.country"),
                        df2.col("value.date_added"),
                        df2.col("value.release_year"),
                        df2.col("value.rating"),
                        df2.col("value.duration"),
                        df2.col("value.genre"),
                        df2.col("value.description"),
                        df2.col("value.year_added"),
                        df2.col("value.month_added"),
                        df2.col("value.season_count")
                );

        StreamingQuery query = DF
                .writeStream()
                .format("parquet")
                .outputMode("append")
                .option("checkpointLocation", "hdfs://172.17.80.21:9000/user/phannt8/btl/checkpoint")
                .option("path", "hdfs://172.17.80.21:9000/user/phannt8/btl/output")
                .start();
        query.awaitTermination();

    }













//        Dataset<Row> DF =
//                ds.selectExpr("CAST(value as string)")
//                        .select(split(col("value"),",").getItem(0).as("show_id"),
//                                split(col("value"),",").getItem(1).as("type"),
//                                split(col("value"),",").getItem(2).as("title"),
//                                split(col("value"),",").getItem(3).as("director"),
//                                split(col("value"),",").getItem(4).as("country"),
//                                split(col("value"),",").getItem(5).as("date_added"),
//                                split(col("value"),",").getItem(6).as("release_year"),
//                                split(col("value"),",").getItem(7).as("rating"),
//                                split(col("value"),",").getItem(8).as("duration"),
//                                split(col("value"),",").getItem(9).as("genre"),
//                                split(col("value"),",").getItem(10).as("description"),
//                                split(col("value"),",").getItem(11).as("year_added"),
//                                split(col("value"),",").getItem(12).as("month_added"),
//                                split(col("value"),",").getItem(13).as("season_count")

//                        );
//        DF.show(10);

//        DF.writeStream()
//                .option("partition",1)
//                .outputMode("append")
//                .format("parquet")
//                //hdfs://172.17.80.21:9000/user/phannt8/btl/checkpoint
//                //hdfs://192.168.5.102:9000/user/phannt8/btl/checkpoint
//                .option("checkpointLocation", "./Final_Project/final_project/phannt8/checkpoint")
//                ///home/phannt8/Documents/masterdev/Final_Project/final_project/phannt8/checkpoint
//                //hdfs://172.17.80.21:9000/user/phannt8/btl/output
//                .option("path", "./Final_Project/final_project/phannt8/output")
//                //hdfs://192.168.5.102:9000/user/phannt8/btl/output
//                ///home/phannt8/Documents/masterdev/Final_Project/final_project/phannt8/output
//                .start().awaitTermination();

}

