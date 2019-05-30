package ds.comment.dianshang;

import ds.comment.dianshang.entity.Comment;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: lkx
 * @Date: create in 18:33 2019/5/30
 */
public class StringTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
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

            }
        }
//        Pattern pattern = Pattern.compile("^id(\\d+).+[\\u4e00-\\u9fa5]*$");


    }
}
