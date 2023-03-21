package com.prc.transfer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "subtitle")
public class Subtitle implements Serializable {

    @Id
    private String id;

    @Field("startTime")
    private String startTime;

    @Field("endTime")
    private String endTime;

    @Field("text")
    private String text;
}
