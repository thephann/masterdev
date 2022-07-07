# EX7

## Task 1

1.Create input, output folder on HDFS.
~~~
hdfs dfs -mkdir /phannt8/ex1/input
hdfs dfs -mkdir /phannt8/ex1/output
~~~

2.Copy input file from local to server.

3.Copy classes folder from local to server and build jar file.
~~~
scp -r /home/phannt8/Documents/masterdev/Ex7/WordCount/target/classes hadoop@172.17.80.21:/home/hadoop/phannt8/ex1
jar -cvf ex1.jar -C classes/ .
~~~

4.Run jar file with yarn.
~~~
yarn jar ex1.jar main.java.WordCount /phannt8/ex1/input/input.txt /phannt8/ex1/output
~~~

5.Get output from hdfs.
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

3.Copy classes folder from local to server and build jar file.
~~~
scp -r /home/phannt8/Documents/masterdev/Ex7/HadoopMapReduce/target/classes  hadoop@172.17.80.21:/home/hadoop/phannt8/ex2
jar -cvf ex2.jar -C classes/ .
~~~

4.Run jar file with yarn.
~~~
yarn jar ex2.jar main.java.WordCount /phannt8/ex2/input/count_distinct.csv /phannt8/ex2/output /phannt8/ex2/output1
~~~

5.Get output from hdfs.
~~~
hdfs dfs -get /phannt8/ex2/output1/part-r-00000 ex2_res
~~~

## Task 3
