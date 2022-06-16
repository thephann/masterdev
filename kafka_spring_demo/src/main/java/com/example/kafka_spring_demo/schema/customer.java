package com.example.kafka_spring_demo.schema;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class customer {
    @CsvBindByName
    public int id;

    @CsvBindByName
    public int num_order;

    @CsvBindByName
    public int age;

    @CsvBindByName
    public String telephone;

}
