package com.example.kafka_spring_demo.kafka;

import com.example.kafka_spring_demo.schema.customer;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ReadCSV {
    // https://attacomsian.com/blog/read-write-csv-files-opencsv
    public String csvFileName = "/home/phannt8/Documents/masterdev/kafka_spring_demo/src/main/resources/data/customer.csv";

    public List stdList;
    public List ReadCSVFile() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader(csvFileName));

            CsvToBean csvToBean = new CsvToBeanBuilder(csvReader)
                    .withType(customer.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            stdList = csvToBean.parse();
            csvReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stdList;
    }
}
