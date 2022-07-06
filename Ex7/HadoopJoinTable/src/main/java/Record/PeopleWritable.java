package main.java.Record;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PeopleWritable implements Writable {
    public IntWritable id = new IntWritable();
    public Text firstName = new Text();
    public Text lastName = new Text();
    public IntWritable age = new IntWritable();
    public Text street = new Text();
    public Text city = new Text();
    public Text state = new Text();
    public IntWritable zip = new IntWritable();

    public PeopleWritable(){}

    public PeopleWritable(int id, String firstName, String lastName, int age, String street, String city, String state, int zip){
        this.id.set(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.age.set(age);
        this.street.set(street);
        this.city.set(city);
        this.state.set(state);
        this.zip.set(zip);
    }

    public void write(DataOutput dataOutput) throws IOException {
        this.id.write(dataOutput);
        this.firstName.write(dataOutput);
        this.lastName.write(dataOutput);
        this.age.write(dataOutput);
        this.street.write(dataOutput);
        this.city.write(dataOutput);
        this.state.write(dataOutput);
        this.zip.write(dataOutput);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.id.readFields(dataInput);
        this.firstName.readFields(dataInput);
        this.lastName.readFields(dataInput);
        this.age.readFields(dataInput);
        this.street.readFields(dataInput);
        this.city.readFields(dataInput);
        this.state.readFields(dataInput);
        this.zip.readFields(dataInput);
    }
}
