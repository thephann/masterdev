import com.github.javafaker.Faker;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Gen2CSV4Class {
    public static void main(String[] args) {
    // class_no, num_of_stu, year,course_no,instructor_no
        String saveFile = "/home/phannt8/Documents/masterdev/Ex4/src/main/resources/class.csv";

        writeData(saveFile,);

    }
    public static void writeData(String filePath, List<String> listName) {
        File file = new File(filePath);
        Random rand = new Random();
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = {"class_no","num_of_stu","year"};
            writer.writeNext(header);

            Faker faker = new Faker(new Locale("vi"));
            int count = 0;
            while (count < 2500) {
                count++;
                String[] dataSet = {, String.valueOf(rand.nextInt(50,101)), String.valueOf(rand.nextInt(2018,2022))};
                writer.writeNext(dataSet);
            }
            writer.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
