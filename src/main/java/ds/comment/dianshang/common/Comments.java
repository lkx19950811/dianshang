package ds.comment.dianshang.common;

import java.util.Random;

/**
 * @Author: lkx
 * @Date: create in 17:09 2019/5/30
 */
public class Comments {
    /** 实物交易 */
    public static final String[] type1 = {"你店的都很好质量。谢谢",
            "虽然还没有到手上，不过爸爸说不错","最近太忙了，确认晚了，东西是很好的",
            "买了很多东西 都非常满意 很好的卖家 我会常来的 折扣卡可以升到顶级了吧",
            "物流公司的态度比较差,建议换一家！不过店长人还不错",
            "上次上货的时候，弄点新款吧！给多点选择",
            "寄得很快,2天就到了","还不错.质量挺好的.速度也快","掌柜人不错 。服务很热情",
            "还能说什么呢～～有生之年能碰到老板这个店，够幸福的．","忙了，都忘记了，不好意思！商品还不错",
            "这样太便宜了吧,真是太好了","比外面的实体店便宜多了","爱了,爱了",
            "质量太好了吧,推荐购买","我已经推荐朋友购买","宝贝包装很严实,非常好啊"};

    public static String getComment(String [] comments){
        int size = comments.length;
        int randNum = new Random().nextInt(size -1);
        return comments[randNum];
    }
}
