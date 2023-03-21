package com.prc.transfer.dao.impl;

import com.prc.transfer.dao.VideoDao;
import com.prc.transfer.vo.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VideoDaoImpl implements VideoDao{

    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<Video> findAllVideo() {
        return mongoTemplate.findAll(Video.class);
    }

    @Override
    public Video findByVideoID(String videoId) {
        Query query = new Query(Criteria.where("videoId").is(videoId));
        return mongoTemplate.findOne(query, Video.class);
    }
}
