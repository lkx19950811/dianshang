package ds.comment.dianshang;

import ds.comment.dianshang.entity.Comment;
import ds.comment.dianshang.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: lkx
 * @Date: create in 18:28 2019/5/30
 */
@Component
public class Schedule {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CommentRepository commentRepository;
    @Scheduled(initialDelay = 0,fixedDelay = 1000000)
    public void getComment() throws IOException {
        FileReader file = new FileReader("content.txt");
        BufferedReader bufferedReader = new BufferedReader(file);
        String line;
        while ((line = bufferedReader.readLine())!=null){
            Pattern pattern = Pattern.compile("id:(\\d+).+content(.+)时间改为");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()){
                logger.info("id :{}____文本{}",matcher.group(1),matcher.group(2));
                Comment comment = commentRepository.findById(Long.valueOf(matcher.group(1))).get();
                comment.setContent(matcher.group(2));
                commentRepository.save(comment);
            }
        }
    }
}
