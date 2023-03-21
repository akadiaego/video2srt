package com.prc.transfer.service.impl;

import com.prc.transfer.dao.SubtitleDao;
import com.prc.transfer.service.SubtitleService;
import com.prc.transfer.vo.Subtitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubtitleServiceImpl implements SubtitleService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SubtitleDao subtitleDao;

    /***
     * 根据id查询
     * @param videoId
     */
    @Override
    public Subtitle findSubtitleByVideoId(String videoId) {
        return subtitleDao.findByVideoId(videoId);
    }
}
