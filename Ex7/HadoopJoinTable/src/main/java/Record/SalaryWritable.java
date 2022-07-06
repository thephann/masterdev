package main.java.Record;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class SalaryWritable implements Writable {
    public Text salary = new Text();

    public SalaryWritable(String salary) {
        this.salary.set(salary);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        this.salary.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.salary.readFields(dataInput);
    }
}
