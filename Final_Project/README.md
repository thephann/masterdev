Bài tập lớn: Xây dựng luồng dữ liệu và các biểu đồ phân tích một số thông tin về dữ liệu netflix.

Dữ liệu: https://www.kaggle.com/datasets/shivamb/netflix-shows/code

* Cấu trúc thư mục:
1) Clean data: /Final_Project/final_project/python/cleaner.py
2) Producer: /Final_Project/final_project/python/producer.py
3) Spark Job: /Final_Project/final_project/src/main/java/Spark/SparkStreaming.java

* Luồng hoạt động:
1) Dữ liệu được làm sạch và được đẩy vào kafka topics theo từng record bằng producer.
2) Sử dụng spark job là một consumer để consume messages và lưu dữ liệu vào HDFS.
3) Từ dữ liệu trên HDFS tạo bảng hive rồi thực hiện phân tích dữ liệu từ database trên HIVE.

Chạy spark submit: 
  `spark-submit --deploy-mode cluster --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.4.0 --class Spark.SparkStreaming final_project-1.0-SNAPSHOT.jar`
  
Tạo bảng hive:
~~~
create external table netflix (show_id STRING, type STRING, title STRING, director STRING, country STRING, date_added STRING, release_year STRING, rating STRING, duration STRING, genre STRING,description STRING, year_added STRING, month_added STRING, season_count STRING) STORED AS PARQUET LOCATION 'hdfs://172.17.80.21:9000/user/phannt8/btl/output';
~~~


