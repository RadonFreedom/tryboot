package fre.shown.tryboot.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Radon Freedom
 * created at 2019.04.26 上午9:45
 */

public class SeckillOrderVO {

    private Long seckillGoodId;
    private Integer goodCnt;
    private String username;
    private Integer orderChannel;
    private Long deliveryInfoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SeckillOrderVO seckillOrderVO = (SeckillOrderVO) o;

        return new EqualsBuilder()
                .append(getSeckillGoodId(), seckillOrderVO.getSeckillGoodId())
                .append(getGoodCnt(), seckillOrderVO.getGoodCnt())
                .append(getUsername(), seckillOrderVO.getUsername())
                .append(getOrderChannel(), seckillOrderVO.getOrderChannel())
                .append(getDeliveryInfoId(), seckillOrderVO.getDeliveryInfoId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getSeckillGoodId())
                .append(getGoodCnt())
                .append(getUsername())
                .append(getOrderChannel())
                .append(getDeliveryInfoId())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("seckillGoodId", seckillGoodId)
                .append("goodCnt", goodCnt)
                .append("username", username)
                .append("orderChannel", orderChannel)
                .append("deliveryInfoId", deliveryInfoId)
                .toString();
    }

    public SeckillOrderVO() {
    }

    public Long getSeckillGoodId() {
        return seckillGoodId;
    }

    public void setSeckillGoodId(Long seckillGoodId) {
        this.seckillGoodId = seckillGoodId;
    }

    public Integer getGoodCnt() {
        return goodCnt;
    }

    public void setGoodCnt(Integer goodCnt) {
        this.goodCnt = goodCnt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(Integer orderChannel) {
        this.orderChannel = orderChannel;
    }

    public Long getDeliveryInfoId() {
        return deliveryInfoId;
    }

    public void setDeliveryInfoId(Long deliveryInfoId) {
        this.deliveryInfoId = deliveryInfoId;
    }
}
