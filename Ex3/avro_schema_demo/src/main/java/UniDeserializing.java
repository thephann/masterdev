import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;
import src.main.avro.universities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UniDeserializing {
    public static void main(String []args) throws IOException {
        DatumReader<universities> empDatumReader = new SpecificDatumReader<>(universities.class);
        DataFileReader<universities> dataFileReader = new DataFileReader<>(new File("/home/phannt8/git_space/masterdev/avro_schema_demo/src/main/resources/university.avro"), empDatumReader);
        universities u = null;

        try (FileWriter uni_data = new FileWriter("uni_data.json")) {
            BufferedWriter bw = new BufferedWriter(uni_data);
            bw.write("{\"uni\":[");
            while(dataFileReader.hasNext()) {
                u=dataFileReader.next(u);
                bw.write(u.toString());
                bw.newLine();
            }
            bw.close();
            System.out.println("Done");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

//        while(dataFileReader.hasNext()){
//
//            u=dataFileReader.next(u);
//            System.out.println(u);
//        }

    }
}
