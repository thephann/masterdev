import com.github.javafaker.Faker;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class GenCSVFIle {
    public static Random rand = new Random();
    public static Faker faker = new Faker(new Locale("vi"));

    public static String getName() {
        String fullName = faker.name().nameWithMiddle();

        return fullName;
    }

    public static String getUserName(String fullName) {
        String[] name = fullName.split("\\s");
        String userName = "";
        for (int i=0;i < name.length;i++) {
            userName+= name[i].charAt(0);
        }
        return (userName+rand.nextInt(50)).toLowerCase();
    }

    public static String getProvince() {
        String province = faker.address().city() + " " + "Việt Nam";
        return province;
    }

    public static int getAge() {
        int randAge = rand.nextInt(15,60);
        return randAge;
    }

    public static void main(String[] args) {
        List<String> name = new ArrayList<>();

        File file = new File("/home/phannt8/Documents/masterdev/Ex4/src/main/resources/users_data.csv");
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);
            String[] header = {"id","user_name","full_name","province","age"};
            writer.writeNext(header);
            for (int i=1;i<1000001;i++) {
                String fullName = getName();
                String userName= getUserName(fullName);
                name.add(userName);

                if (name.contains(userName)) {
                    userName+=i;
                }

                String[] dataSet = {String.valueOf(i),userName,fullName,getProvince(), String.valueOf(getAge())};
                writer.writeNext(dataSet);
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
