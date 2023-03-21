package com.prc.transfer.dao;

import com.prc.transfer.vo.Subtitle;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtitleDao {

    Subtitle findByVideoId(String videoId);
}
