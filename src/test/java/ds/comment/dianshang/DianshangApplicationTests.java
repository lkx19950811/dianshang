package ds.comment.dianshang;

import ds.comment.dianshang.common.Comments;
import ds.comment.dianshang.entity.Comment;
import ds.comment.dianshang.entity.Good;
import ds.comment.dianshang.repository.CommentRepository;
import ds.comment.dianshang.repository.GoodRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DianshangApplicationTests {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String startDate = "yyyy-MM-dd";
    private String endDate = "yyyy-MM-dd";
    @Autowired
    GoodRepository goodsRepository;
    @Autowired
    CommentRepository commentRepository;

    @Test
    public void Ecshop() {
        Comment comment = commentRepository.getOne(572L);
        comment.setContent("我很好");
        comment.setAddTime((int)(System.currentTimeMillis() / 1000L));
        commentRepository.save(comment);
    }

    /**
     * 刷评论
     */
    @Test
    public void EchsopComment() {
        startDate = "2019-04-15";
        endDate = "2019-05-20";
        List<Good> goods = goodsRepository.findAll();
        List<Comment> comments = new ArrayList<>();
        for (Good good : goods) {
            setComment(3, comments, good.getId());
        }
        int num = commentRepository.saveAll(comments).size();
        logger.info("完成了{} 条", num);
    }

    /**
     * 修正所有评论时间
     */
    @Test
    public void FixCommentTime() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        startDate = "2019-04-01";
        endDate = "2019-05-29";
        List<Comment> comments = commentRepository.findAll();
        for (Comment comment : comments) {
            int second = randomTimeSec(startDate, endDate);
            comment.setAddTime(second);
            logger.info("id:{} content{} 时间改为{}", comment.getId(), comment.getContent(), sf.format((long) second * 1000L));
        }
        commentRepository.saveAll(comments);
    }
    @Test
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
    /**
     * @param num      需要生成的评论数量
     * @param comments 存放评论的集合
     * @param goodId   商品ID
     */
    public void setComment(int num, List<Comment> comments, Long goodId) {
        for (int i = 0; i < num; i++) {
            Comment comment = new Comment();
            comment.setContent(Comments.getComment(Comments.type1));
            comment.setAddTime(randomTimeSec(startDate, endDate));
            comment.setIdValue(Integer.valueOf(goodId.toString()));
            comments.add(comment);
        }
    }

    private static Integer randomTimeSec(String beginDate, String endDate) {
        return (int) (randomDate(beginDate, endDate).getTime() / 1000);
    }

    private static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date();
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }
}
