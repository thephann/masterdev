package main.java;

import main.java.Record.JoinGenericWritable;
import main.java.Record.PeopleWritable;
import main.java.Record.SalaryWritable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class JoinTable extends Configured implements Tool {
    @Override
    public int run(String[] allArgs) throws Exception {
        Configuration conf = new Configuration();
        String[] args = new GenericOptionsParser(conf, allArgs).getRemainingArgs();

        Job job = Job.getInstance(conf);
        job.setJarByClass(JoinTable.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setMapOutputKeyClass(Key.class);
        job.setMapOutputValueClass(JoinGenericWritable.class);

        MultipleInputs.addInputPath(job, new Path(args[0]),
                TextInputFormat.class, PeopleMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]),
                TextInputFormat.class, SalaryMapper.class);

        job.setReducerClass(JoinReducer.class);

        job.setSortComparatorClass(JoinSortingComparator.class);
        job.setGroupingComparatorClass(JoinGroupingComparator.class);

        job.setReducerClass(JoinReducer.class);
        job.setNumReduceTasks(3);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

//        FileSystem fs = FileSystem.get(conf);
//        if (fs.exists(new Path(args[0]))) {
//            fs.delete(new Path(allArgs[2]),true);
//        }

        FileOutputFormat.setOutputPath(job, new Path(args[2]));

        boolean success = job.waitForCompletion(true);
        return (success?0:1);
    }

    public static class JoinGroupingComparator extends WritableComparator {
        public JoinGroupingComparator() {
            super(Key.class, true);
        }

        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            Key first = (Key) a;
            Key second = (Key) b;

            return first.job.compareTo(second.job);
        }
    }

    public static class JoinSortingComparator extends WritableComparator {
        public JoinSortingComparator() {
            super(Key.class, true);
        }

        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            Key first = (Key) a;
            Key second = (Key) b;

            return first.compareTo(second);
        }
    }

    public static class SalaryMapper extends Mapper<LongWritable, Text, Key, JoinGenericWritable> {
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] recordFields = value.toString().split(",");
            String job = recordFields[0];
            String salary = recordFields[1];

            Key recordKey = new Key(job, Key.SALARY_RECORD);
            SalaryWritable record = new SalaryWritable(salary);

            JoinGenericWritable genericWritable = new JoinGenericWritable(record);
            context.write(recordKey, genericWritable);
        }
    }

    public static class PeopleMapper extends Mapper<LongWritable,
            Text, Key, JoinGenericWritable> {
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] recordFields = value.toString().split(",");
            int id = Integer.parseInt(recordFields[0]);
            String firstName = recordFields[1];
            String lastName = recordFields[2];
            int age = Integer.parseInt(recordFields[3]);
            String street = recordFields[4];
            String city = recordFields[5];
            String state = recordFields[6];
            int zip = Integer.parseInt(recordFields[7]);
            String job = recordFields[8];

            Key recordKey = new Key(job, Key.PEOPLE_RECORD);
            PeopleWritable record = new PeopleWritable(id, firstName, lastName, age, street, city, state, zip);
            JoinGenericWritable genericWritable = new JoinGenericWritable(record);
            context.write(recordKey, genericWritable);
        }
    }

    public static class JoinReducer extends Reducer<Key, JoinGenericWritable, NullWritable, Text> {
        StringBuilder output = new StringBuilder();

        @Override
        protected void setup(Reducer<Key, JoinGenericWritable, NullWritable, Text>.Context context) throws IOException, InterruptedException {
            output.append("id,first_name,last_name,age,street,city,state,zip,job,salary \n");
        }

        public void reduce(Key key, Iterable<JoinGenericWritable> values,
                           Context context) throws IOException, InterruptedException {
            String salary = null;


            for (JoinGenericWritable value : values) {
                Writable record = value.get();
                if (key.recordType.equals(Key.SALARY_RECORD)) {
                    SalaryWritable salaryRecord = (SalaryWritable) record;
                    salary = salaryRecord.salary.toString();
                } else if (key.recordType.equals(Key.PEOPLE_RECORD)) {
                    PeopleWritable peopleRecord = (PeopleWritable) record;
                    output.append(peopleRecord.id.toString()).append(", ");
                    output.append(peopleRecord.firstName.toString()).append(", ");
                    output.append(peopleRecord.lastName.toString()).append(", ");
                    output.append(peopleRecord.age.toString()).append(", ");
                    output.append(peopleRecord.street.toString()).append(", ");
                    output.append(peopleRecord.city.toString()).append(", ");
                    output.append(peopleRecord.state.toString()).append(", ");
                    output.append(peopleRecord.zip.toString()).append(", ");
                    output.append(key.job.toString()).append(", ");
                    output.append(salary).append("\n");
                }
            }
            context.write(NullWritable.get(), new Text(output.toString()));

        }

    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new JoinTable(), args);
        System.exit(res);
    }


}



