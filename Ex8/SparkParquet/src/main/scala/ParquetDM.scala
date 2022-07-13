import org.apache.spark.sql.SparkSession

object ParquetDM {
  val small_data_url = "/home/phannt8/Documents/masterdev/Ex8/SparkParquet/Sample_data/small_data.parquet"
  val task1_save_url = "/home/phannt8/Documents/masterdev/Ex8/SparkParquet/user/phannt8/device_model_num_user/"
  val task2_save_url = "/home/phannt8/Documents/masterdev/Ex8/SparkParquet/user/phannt8/device_model_list_user/"
  val task3_save_url = "/home/phannt8/Documents/masterdev/Ex8/SparkParquet/user/phannt8/button_count_by_user_id_device_model/"

  val spark: SparkSession = SparkSession
    .builder()
    .appName("Spark-Demo")
    .config("spark.master", "local")
    .getOrCreate()

  def main(args: Array[String]): Unit = {
    println("Hello World!")
    val parquetFile = spark.read.parquet(small_data_url)
    parquetFile.createOrReplaceTempView("parquetTable")
        val sparkSql = spark
          .sql("select device_model, count(distinct user_id) as count from parquetTable where device_model is not null group by device_model")
        sparkSql.repartition(1).write.mode("overwrite").format("parquet").save(task1_save_url)

        val sparkSql_list_users = spark
          .sql("select device_model, collect_list(user_id) as list_users from parquetTable where device_model is not null group by device_model")
        sparkSql_list_users.repartition(1).write.mode("overwrite").format("orc").save(task2_save_url)

    val sparkSql_task3 = spark
      .sql("select concat(user_id,'_',device_model) as user_id_device_model, button_id, count(*) as count from parquetTable where button_id is not null group by user_id_device_model, button_id ")
    sparkSql_task3.repartition(1).write.mode("overwrite").format("parquet").save(task3_save_url)
  }
}
