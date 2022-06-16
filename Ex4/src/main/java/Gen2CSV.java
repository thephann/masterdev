import com.github.javafaker.Faker;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class Gen2CSV {
    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        String filePath = "/home/phannt8/Documents/masterdev/Ex4/src/main/resources/student.csv";
        String genFile = "/home/phannt8/Documents/masterdev/Ex4/src/main/resources/student.csv";
        String genLec = "/home/phannt8/Documents/masterdev/Ex4/src/main/resources/instructor.csv";
        String readFile = "/home/phannt8/Documents/masterdev/Ex4/src/main/Data/boy.txt";
        String readFile_girl = "/home/phannt8/Documents/masterdev/Ex4/src/main/Data/girl.txt";
        List<String> first_name = Arrays.asList("Nguyễn","Trần","Lê","Phạm","Hoàng","Huỳnh","Phan","Vũ","Võ","Đặng","Bùi","Đỗ","Hồ","Ngô","Dương");
        List<String> boy_data = readData(readFile);
        List<String> girl_data = readData(readFile_girl);
        List<String> fullName = new ArrayList<>();
        List<String> fullName4Lec = new ArrayList<>();

//        System.out.println(first_name.get(rand.nextInt(first_name.size())));
        fullName = mergeName(first_name,boy_data);
        fullName4Lec = mergeName(first_name,girl_data);
//        try {
//            File file = new File(filePath);
//            FileWriter fw = new FileWriter(file);
//            for (String d: boy_data) {
//                fullName.add(first_name.get(rand.nextInt(first_name.size())) + " "+ d);
//            }
//            fw.close();
//        } catch(IOException e) {
//            e.printStackTrace();
//        }

        writeData(genFile,fullName);
    }
    public static List<String> mergeName(List<String>listName,List<String>nameData) {
        Random rand = new Random();
        List<String> resName = new ArrayList<>();
        for (String d: nameData) {
            resName.add(listName.get(rand.nextInt(listName.size())) + " "+ d);
        }
        return resName;

    }

    public static List<String> readData(String readFile) throws IOException {
        List<String> data = new ArrayList<String>();
        String testRow;
        try {

            BufferedReader br = new BufferedReader(new FileReader(readFile));

            while ((testRow = br.readLine()) != null) {

                data.add(testRow);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found " + readFile);
        } catch (IOException e) {
            System.out.println("ERROR: Could not read " + readFile);
        }
        return data;
    }

    public static String randomName(List<String> listName) {
        Random rand = new Random();
        String name = listName.get(rand.nextInt(listName.size()));
        return name;
    }
    public static void writeData(String filePath,List<String>listName) {
        File file = new File(filePath);
        Random rand = new Random();
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = {"student_no","student_name","gender","telephone"};
            writer.writeNext(header);

            Faker faker = new Faker(new Locale("vi"));
            int count = 0;
            while (count < 1000) {
                count++;
                String[] dataSet = {String.valueOf(18020000+count),randomName(listName), String.valueOf(rand.nextInt(2)),faker.phoneNumber().phoneNumber()};
                writer.writeNext(dataSet);
            }
            writer.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
