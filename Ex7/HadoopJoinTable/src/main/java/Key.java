package main.java;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Key implements WritableComparable<Key> {
    public static final IntWritable PEOPLE_RECORD = new IntWritable(1);
    public static final IntWritable SALARY_RECORD = new IntWritable(0);
    public Text job = new Text();
    public IntWritable recordType = new IntWritable();



    public Key() {}

    public Key(String job, IntWritable recordType) {
        this.job.set(job);
        this.recordType = recordType;
    }

    @Override
    public int compareTo(Key other) {
        if (this.job.equals(other.job)) {
            return this.recordType.compareTo(other.recordType);
        } else {
            return this.job.compareTo(other.job);
        }
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        this.job.write(dataOutput);
        this.recordType.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.job.readFields(dataInput);
        this.recordType.readFields(dataInput);
    }

    public boolean equals(Key other) {
        return this.job.equals(other.job) && this.recordType.equals(other.recordType);
    }

    public int hashCode() {
        return this.job.hashCode();
    }

}
