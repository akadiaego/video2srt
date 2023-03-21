package com.prc.transfer.service;

import com.prc.transfer.vo.Video;

import java.util.List;

public interface VideoService {
    /**
     * 查询所有
     */
    List<Video> findAll();

    /***
     * 根据id查询
     */
    Video findVideoByVideoId(String videoId);

    String saveVideo(String videoId, String videoTitle, String videName);
}
