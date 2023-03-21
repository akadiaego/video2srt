package com.prc.transfer.service;

import com.prc.transfer.vo.Subtitle;
import com.prc.transfer.vo.Video;

public interface SubtitleService {

    /***
     * 根据id查询
     */
    Subtitle findSubtitleByVideoId(String videoId);
}
