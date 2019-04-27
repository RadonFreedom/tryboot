package fre.shown.tryboot.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Radon Freedom
 * created at 2019.04.27 上午11:31
 */

public class SeckillGoodInfoDTO {

    private Double goodPrice;
    private Double seckillPrice;
    private String goodName;

    public SeckillGoodInfoDTO() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("goodPrice", goodPrice)
                .append("seckillPrice", seckillPrice)
                .append("goodName", goodName)
                .toString();
    }

    public Double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
}
