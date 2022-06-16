import com.github.javafaker.Faker;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Gen2CSV4Course {
    public static void main(String[] args) throws IOException {
        String readSub = "/home/phannt8/Documents/masterdev/Ex4/src/main/Data/girl.txt";
        String genSub = "/home/phannt8/Documents/masterdev/Ex4/src/main/resources/instructor.csv";
        List<String> first_name = Arrays.asList("Nguyễn","Trần","Lê","Phạm","Hoàng","Huỳnh","Phan","Vũ","Võ","Đặng","Bùi","Đỗ","Hồ","Ngô","Dương");
        List<String> saveSub = Gen2CSV.readData(readSub);
        List<String> typeList = Arrays.asList("Compulsory","Elective");
        List<String> instructor_fn = new ArrayList<>();
//        for (String s: saveSub) {
//            System.out.println(s);
//        }
        instructor_fn = Gen2CSV.mergeName(first_name,saveSub);
        writeSubData(genSub,instructor_fn);
    }

    public static void writeSubData(String filePath,List<String>listName) {
        File file = new File(filePath);
        Random rand = new Random();
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = {"instructor_no","course_no","gender","telephone"};
            writer.writeNext(header);

            Faker faker = new Faker(new Locale("vi"));
            int count = 0;
            while (count < 402) {
                count++;
                //instructor
//                String[] dataSet = {String.valueOf(count),listName.get(count),Gen2CSV.randomName(typeList)};
                String[] datasetInstruc = {Gen2CSV.randomName(listName), String.valueOf(rand.nextInt(2)),faker.phoneNumber().phoneNumber()};
                //course
//                String[] courseDataset = {String.valueOf(count), listName.get(count), String.valueOf(rand.nextInt(2))};
                writer.writeNext(datasetInstruc);
            }
            writer.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

// phone_number id - userId
