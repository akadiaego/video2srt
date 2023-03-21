package com.prc.transfer.service.impl;

import com.prc.transfer.dao.VideoDao;
import com.prc.transfer.service.VideoService;
import com.prc.transfer.vo.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

//    @Autowired
//    public VideoServiceImpl(MongoTemplate mongoTemplate){
//        this.mongoTemplate = mongoTemplate;
//    }
//    private final MongoTemplate mongoTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private VideoDao videoDao;
    /**
     * 查询所有
     */
    @Override
    public List<Video> findAll() {
        return videoDao.findAllVideo();
    }

    /***
     * 根据id查询
     * @param videoId
     */
    @Override
    public Video findVideoByVideoId(String videoId) {
        return videoDao.findByVideoID(videoId);
    }

    @Override
    public String saveVideo(String videoId, String videoTitle, String videName) {
        Video video = new Video();
        video.setVideoId("1234");
        video.setVideoTitle("test");
        video.setFileName("test2.mp4");
        mongoTemplate.save(video);
        return "添加成功";
    }
}
