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
@Document(collection = "video")//指明实体类对应mongodb的哪个集合
public class Video implements Serializable {
    //`{ “videoID”: uuid “videoTitle”: Cloud Broadcasting” “fileName”:“test.mp4”}`

    @Id
    private String id;

    @Field("videoId")
    private String videoId;

    @Field("videoTitle")
    private String videoTitle;

    @Field("fileName")
    private String fileName;

}
