import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class load2DB {
    public static void main(String[] args)  {

        long startTime = System.nanoTime();
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/ghtk_db?useUnicode=true&characterEncoding=UTF-8";
            String csv_file = "/home/phannt8/Documents/masterdev/Ex4/src/main/Data/users.csv";
            String USER_NAME = "root";
            String PASSWORD = "thephan141";
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(jdbcURL, USER_NAME, PASSWORD);
            connection.setAutoCommit(false);
            Statement st = connection.createStatement();

//            String vietChar = "ISO";
            String query = " insert into users (id, user_name, full_name, province, age)"
                    + " values (?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            final int batchSize = 10000;
            int count = 0;

            BufferedReader br = new BufferedReader(new FileReader(csv_file));
            String rowData = "";
            br.readLine();
            while ((rowData = br.readLine()) != null) {
                String[] data = rowData.split(",");
//                System.out.println(data[0]+" "+data[1]+" "+data[2]+" "+data[3]+" "+data[4]);
                String id = data[0];
                String userName = data[1];
                String fullName = data[2];
                String province = data[3];
                String age = data[4];

                stmt.setInt(1, Integer.parseInt(id));
                stmt.setString(2, userName);
                stmt.setString(3, fullName);
                stmt.setString(4, province);
                stmt.setInt(5, Integer.parseInt(age));

                stmt.addBatch();
                count++;


                if (count % batchSize == 0) {
                    stmt.executeBatch();
                    System.out.println("Inserted Data: " + count++);
                }

            }
            br.close();
            stmt.executeBatch();
            connection.commit();
            connection.close();

        }catch (SQLException e) {
            e.printStackTrace();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        long exeTime = System.nanoTime() - startTime;
        System.out.println("Total execution time: " +
                String.format("%d min, %d sec",
                        TimeUnit.NANOSECONDS.toHours(exeTime),
                        TimeUnit.NANOSECONDS.toSeconds(exeTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(exeTime))));
    }
}


