package com.prc.transfer.dao;

import com.prc.transfer.vo.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoDao {

    List<Video> findAllVideo() ;

    Video findByVideoID (String videoId) ;
}
