package com.prc.transfer.controller;

import com.prc.transfer.service.SubtitleService;
import com.prc.transfer.vo.RespBean;
import com.prc.transfer.vo.Subtitle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Api("字幕相关功能")
@RestController
@RequestMapping("/subtitle")
public class SubtitleController {
    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);


    @Autowired
    private SubtitleService subtitleService;


    @ApiOperation("通过视频id确定唯一字幕")
    @GetMapping("/findSubtitle/{videoId}")
    public RespBean findOneByVideoId(@PathVariable("videoId") String videoId) {
        logger.info("find caption by videoId:{}",videoId);
        Subtitle subtitle = subtitleService.findSubtitleByVideoId(videoId);
        return RespBean.success(subtitle);
    }
}
