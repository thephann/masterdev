import com.github.javafaker.Faker;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class GenData {
    public static void main(String[] args) {
        List<String> last_name = Arrays.asList("Nguyễn","Trần","Lê","Phạm","Hoàng","Huỳnh","Phan","Vũ","Võ","Đặng","Bùi","Đỗ","Hồ","Ngô","Dương");
        String readdta = "/home/phannt8/Documents/masterdev/InsertDB/src/Data/boy.txt";
        List<String>fullName = new ArrayList<>();
        fullName = mergeName();
        writeData();

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
    public static void writeData(String filePath, List<String> listName) {
        File file = new File(filePath);
        Random rand = new Random();
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = {"id","user_name","full_name","province","age"};
            writer.writeNext(header);

            Faker faker = new Faker(new Locale("vi"));
            int count = 0;
            while (count < 1000000) {
                count++;
                String[] dataSet = {};
                writer.writeNext(dataSet);
            }
            writer.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
