import com.prc.transfer.vo.Video;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@SpringBootTest
public class MongoTemplateTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void saveVideo() {
        Video video = new Video();
        video.setVideoId("1234");
        video.setVideoTitle("test");
        video.setFileName("test2.mp4");
        mongoTemplate.save(video);
    }
}
