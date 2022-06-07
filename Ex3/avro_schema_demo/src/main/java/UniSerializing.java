import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import src.main.avro.Programs;
import src.main.avro.depart;
import src.main.avro.universities;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

public class UniSerializing {
    public static void main(String args[]) throws IOException {
        String baseDir = "/home/phannt8/git_space/masterdev/avro_schema_demo";
        // un1
        universities uni1 = new universities();
        List<depart> departList1 = new ArrayList<depart>();
        depart depart1 = new depart();
        depart1.setDepartName("Architecture");
        depart1.setContact("0987124555");
        depart1.setNumberOfStudents(130);
        depart1.setPrograms(Programs.Honors);
        departList1.add(depart1);

        uni1.setUniName("Cambridge");
        uni1.setNumberOfStaffs(500);
        uni1.setEmail("CambridgeCont@gmail.com");
        uni1.setDepartment(departList1);

        // un2
        universities uni2 = new universities();
        List<depart> departList2 = new ArrayList<depart>();
        depart depart2 = new depart();
        depart2.setDepartName("Information technology");
        depart2.setContact("0876543678");
        depart2.setNumberOfStudents(150);
        depart2.setPrograms(Programs.HighQuality);
        departList2.add(depart2);

        uni2.setUniName("Stanford");
        uni2.setNumberOfStaffs(700);
        uni2.setEmail("StanfordCont@gmail.com");
        uni2.setDepartment(departList2);

        List<universities> universitiesList = new ArrayList<universities>();
        universitiesList.add(uni1);
        universitiesList.add(uni2);

        try{
        DatumWriter<universities> empDatumWriter = new SpecificDatumWriter<universities>(universities.class);
        DataFileWriter<universities> empFileWriter = new DataFileWriter<universities>(empDatumWriter);
        empFileWriter.create(uni1.getSchema(), new File(baseDir,"src/main/resources/university.avro"));
            for (universities s: universitiesList) {
                empFileWriter.append(s);
            }
            empFileWriter.close();
        }
        catch(IOException e) {
            throw new UncheckedIOException("Can't convert",e);
        }
    }

}
