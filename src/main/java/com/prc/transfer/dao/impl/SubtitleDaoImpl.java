package com.prc.transfer.dao.impl;


import com.prc.transfer.dao.SubtitleDao;
import com.prc.transfer.vo.Subtitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SubtitleDaoImpl implements SubtitleDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Subtitle findByVideoId(String videoId) {
        Query query = new Query(Criteria.where("videoId").is(videoId));
        return mongoTemplate.findOne(query, Subtitle.class);
    }
}
