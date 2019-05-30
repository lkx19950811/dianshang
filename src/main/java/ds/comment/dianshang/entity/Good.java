package ds.comment.dianshang.entity;

import javax.persistence.*;

/**
 * @Author: lkx
 * @Date: create in 16:41 2019/5/30
 */
@Entity
@Table(name = "ecs_goods")
public class Good {
    // 基础信息
    @Id
    @GeneratedValue
    @Column(name = "goods_id")
    private Long id;
    private String goodsName;
    private String goodsDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }
}
