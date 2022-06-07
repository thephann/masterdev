
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class AvroParser {
    public static void main(final String[] args) throws IOException {
        Schema schema = new Schema.Parser().parse(new File("/home/phannt8/git_space/masterdev/avro_schema_demo/src/main/avro/student.avsc"));
        GenericRecord stu1 = new GenericData.Record(schema);
        stu1.put("id",5);
        stu1.put("fname","fn1");
        stu1.put("lname","ln1");
        stu1.put("age",22);
        stu1.put("gender","male");

        GenericRecord stu2 = new GenericData.Record(schema);
        stu2.put("id",1);
        stu2.put("fname","fn2");
        stu2.put("lname","ln2");
        stu2.put("age",22);
        stu2.put("gender","female");


        DatumWriter<GenericRecord> datumWriter = new SpecificDatumWriter<>(schema);
        DataFileWriter<GenericRecord> fw = new DataFileWriter<>(datumWriter);
        fw.create(schema, new File("/home/phannt8/git_space/masterdev/avro_schema_demo/src/main/resources/student.avro"));
        fw.append(stu1);
        fw.append(stu2);
        fw.close();

        System.out.println("Successfully");


    }

}
