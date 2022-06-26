import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;
import com.opencsv.CSVWriter;

public class GenBooksData {
    private static Random rand = new Random(42);

    public static Faker faker = new Faker(new Locale("en"));

    public static String getBookName() {
        return faker.book().title();
    }

    public static String getAuthorName() {
        return faker.book().author();
    }

    public static String getPublishDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(faker.date().birthday());
    }

    public static String getDescription() {
        return faker.lorem().paragraph();
    }

    public static void main(String[] arg) {
        File file = new File("/home/phannt8/Documents/masterdev/Ex6/DataGenerator/src/Data/booksDataset.csv");
        try {
            FileWriter fileWriter = new FileWriter(file);

            CSVWriter writer = new CSVWriter(fileWriter);
            String[] header = {"name","author","publish_date","description"};
            writer.writeNext(header);

            for (int i=0; i<50001;i++) {
                String[] dataset = {getBookName(),getAuthorName(),getPublishDate(),getDescription()};
                writer.writeNext(dataset);
            }
            System.out.println("Successfully!");
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
