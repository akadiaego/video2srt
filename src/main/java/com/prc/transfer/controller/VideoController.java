package com.prc.transfer.controller;

import com.prc.transfer.service.VideoService;
import com.prc.transfer.vo.RespBean;
import com.prc.transfer.vo.Video;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@Api("视频类接口")
@RestController
@RequestMapping("/video")
public class VideoController {

    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);


    @Autowired
    private VideoService videoService;

    @ApiOperation("获取全部视频")
    @GetMapping("/all")
    public List<Video> findAll() {
        return videoService.findAll();
    }

    @GetMapping("/videoId/{videoId}")
    public RespBean findOneByVideoId(@PathVariable("videoId") String videoId) {
        logger.info("videoId:{}",videoId);
        Video videoByVideoId = videoService.findVideoByVideoId(videoId);
        return RespBean.success(videoByVideoId);
    }

    @PostMapping("/save")
    public RespBean saveVideo(@RequestParam("videoId") String videoId, @RequestParam("videoTitle") String videoTitle,
                              @RequestParam("videName") String videName) {
        return RespBean.success(videoService.saveVideo(videoId,videoTitle,videName));
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
