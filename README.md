# EX7

## Task 1

1.Create input, output folder on HDFS.
~~~
hdfs dfs -mkdir /phannt8/ex1/input
hdfs dfs -mkdir /phannt8/ex1/output
~~~

2.Copy input file from local to server.

3.Put data to hdfs.
~~~
hdfs dfs -put /home/hadoop/phannt8/input.txt /phannt8/ex1/input
~~~

4.Copy classes folder from local to server and build jar file.
~~~
scp -r /home/phannt8/Documents/masterdev/Ex7/WordCount/target/classes hadoop@172.17.80.21:/home/hadoop/phannt8/ex1
jar -cvf ex1.jar -C classes/ .
~~~

5.Run jar file with yarn.
~~~
yarn jar ex1.jar main.java.WordCount /phannt8/ex1/input/input.txt /phannt8/ex1/output
~~~

6.Get output from hdfs.
~~~
hdfs dfs -get /phannt8/ex1/output/part-r-00000 ex1_res
~~~

## Task 2

1.Create input, output folder on HDFS.
~~~
hdfs dfs -mkdir /phannt8/ex2/input
hdfs dfs -mkdir /phannt8/ex2/output
hdfs dfs -mkdir /phannt8/ex2/output2
~~~

2.Copy input file from local to server.
~~~
scp -r /home/phannt8/Documents/masterdev/Ex7/HadoopMapReduce/input/count_distinct.csv hadoop@172.17.80.21:/home/hadoop/phannt8/ex2
~~~

3.Put data to hdfs.
~~~
hdfs dfs -put /home/hadoop/phannt8/ex2/count_distinct.csv /phannt8/ex2/input
~~~

4.Copy classes folder from local to server and build jar file.
~~~
scp -r /home/phannt8/Documents/masterdev/Ex7/HadoopMapReduce/target/classes  hadoop@172.17.80.21:/home/hadoop/phannt8/ex2
jar -cvf ex2.jar -C classes/ .
~~~

5.Run jar file with yarn.
~~~
yarn jar ex2.jar main.java.WordCount /phannt8/ex2/input/count_distinct.csv /phannt8/ex2/output /phannt8/ex2/output1
~~~

6.Get output from hdfs.
~~~
hdfs dfs -get /phannt8/ex2/output1/part-r-00000 ex2_res
~~~

## Task 3

1.Create input, output folder on HDFS.
~~~
hdfs dfs -mkdir /phannt8/ex3/input
hdfs dfs -mkdir /phannt8/ex3/output
~~~

2.Copy input file from local to server.
~~~
scp -r /home/phannt8/Documents/masterdev/Ex7/HadoopJoinTable/input/salary.csv  hadoop@172.17.80.21:/home/hadoop/phannt8/ex3
scp -r /home/phannt8/Documents/masterdev/Ex7/HadoopJoinTable/input/people.csv  hadoop@172.17.80.21:/home/hadoop/phannt8/ex3
~~~

3.Put data to hdfs.
~~~
hdfs dfs -put /home/hadoop/phannt8/ex3/people.csv /phannt8/ex3/input
hdfs dfs -put /home/hadoop/phannt8/ex3/salary.csv /phannt8/ex3/input
~~~

4.Copy classes folder from local to server and build jar file.
~~~
scp -r /home/phannt8/Documents/masterdev/Ex7/HadoopJoinTable/target/classes hadoop@172.17.80.21:/home/hadoop/phannt8/ex3
jar -cvf ex3.jar -C classes/ .
~~~

5.Run jar file with yarn.
~~~
yarn jar ex3.jar main.java.JoinTable /phannt8/ex3/input/people.csv /phannt8/ex3/input/salary.csv  /phannt8/ex3/output
~~~

6.Get output from hdfs.
~~~
hdfs dfs -get /phannt8/ex3/output/part-r-00000 ex3_res
~~~

# EX8

input on HDFS: /phannt8/spark/input/small_data.snappy.parquet

output on HDFS:
/phannt8/spark/small_data_output/device_model_num_user
/phannt8/spark/small_data_output/device_model_list_user
/phannt8/spark/small_data_output/button_count_by_user_id_device_model

