package com.example.spring_boot_crud_mongodb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Document("books")
@Data
public class Books implements Serializable {

    @Id
    private String id;


    @Field(name = "name")
    @TextIndexed
    private String name;

    @TextIndexed
    @Field(name = "author")
//    @TextIndexed(weight = 3)
    private String author;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    @Field(name = "publish_date")
    private Date publishDate;

    @Field(name = "description")
    private String description;
}
