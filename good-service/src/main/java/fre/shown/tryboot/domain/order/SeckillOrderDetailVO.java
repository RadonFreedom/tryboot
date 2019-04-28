package fre.shown.tryboot.domain.order;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;

/**
 * @author Radon Freedom
 * created at 2019.04.27 下午5:09
 */

public class SeckillOrderDetailVO {

    private String goodName;
    private String goodImg;
    private Double goodPrice;
    private String seckillPrice;
    private Timestamp gmtCreate;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("goodName", goodName)
                .append("goodImg", goodImg)
                .append("goodPrice", goodPrice)
                .append("seckillPrice", seckillPrice)
                .append("gmtCreate", gmtCreate)
                .toString();
    }

    public SeckillOrderDetailVO() {
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodImg() {
        return goodImg;
    }

    public void setGoodImg(String goodImg) {
        this.goodImg = goodImg;
    }

    public Double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(String seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
