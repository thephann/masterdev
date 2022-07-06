package main.java;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.StringTokenizer;

public class WordCount extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new WordCount(),args);
        System.exit(res);
    }

    public static class WCMapper_job extends Mapper<Object, Text, Text, IntWritable> {
        private static final IntWritable one = new IntWritable(1);
        private final Text word = new Text();
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while(itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word,one);
            }
        }
    }

    public static class WCReducer_job extends Reducer<Text, IntWritable,Text,IntWritable> {
        private final IntWritable res = new IntWritable();

        public void reduce(Text key,Iterable<IntWritable>values, Context context) throws IOException, InterruptedException {
            int sum=0;
            for(IntWritable val:values){
                sum+=val.get();
            }
            res.set(sum);
            context.write(key,res);
        }
    }


    public int run(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        if (args.length != 2) {
            System.out.println("Please provide two arguments :");
            System.out.println("[ 1 ] Input dir path");
            System.out.println("[ 2 ] Output dir path");
            return -1;
        }
        String in = args[0];
        String out1 = args[1];

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Word count");

        job.setJarByClass(WordCount.class);
        job.setMapperClass(WCMapper_job.class);
        job.setCombinerClass(WCReducer_job.class);
        job.setReducerClass(WCReducer_job.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(in));
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(new Path(in))) {
            fs.delete(new Path(out1), true);
        }

        FileOutputFormat.setOutputPath(job, new Path(out1));

        job.waitForCompletion(true);
        job.getConfiguration().set("mapreduce.output.textoutputformat.separator", ":");
        boolean success = job.waitForCompletion(true);
        return (success?0:1);

    }
}
