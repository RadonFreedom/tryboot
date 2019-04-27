package fre.shown.tryboot.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;

/**
 * @author Radon Freedom
 * created at 2019.04.24 下午8:37
 */

public class SeckillOrderDO {

    private Long id;
    private String username;
    private Long seckillGoodId;
    private Long deliveryInfoId;
    private String goodName;
    private Integer goodCount;
    private Double goodPrice;
    private Integer orderChannel;
    private Integer status;
    private Timestamp payDate;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    public SeckillOrderDO() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("username", username)
                .append("seckillGoodId", seckillGoodId)
                .append("deliveryInfoId", deliveryInfoId)
                .append("goodName", goodName)
                .append("goodCount", goodCount)
                .append("goodPrice", goodPrice)
                .append("orderChannel", orderChannel)
                .append("status", status)
                .append("payDate", payDate)
                .append("gmtCreate", gmtCreate)
                .append("gmtModified", gmtModified)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SeckillOrderDO seckillOrderDO = (SeckillOrderDO) o;

        return new EqualsBuilder()
                .append(getId(), seckillOrderDO.getId())
                .append(getUsername(), seckillOrderDO.getUsername())
                .append(getSeckillGoodId(), seckillOrderDO.getSeckillGoodId())
                .append(getDeliveryInfoId(), seckillOrderDO.getDeliveryInfoId())
                .append(getGoodName(), seckillOrderDO.getGoodName())
                .append(getGoodCount(), seckillOrderDO.getGoodCount())
                .append(getGoodPrice(), seckillOrderDO.getGoodPrice())
                .append(getOrderChannel(), seckillOrderDO.getOrderChannel())
                .append(getStatus(), seckillOrderDO.getStatus())
                .append(getPayDate(), seckillOrderDO.getPayDate())
                .append(getGmtCreate(), seckillOrderDO.getGmtCreate())
                .append(getGmtModified(), seckillOrderDO.getGmtModified())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getUsername())
                .append(getSeckillGoodId())
                .append(getDeliveryInfoId())
                .append(getGoodName())
                .append(getGoodCount())
                .append(getGoodPrice())
                .append(getOrderChannel())
                .append(getStatus())
                .append(getPayDate())
                .append(getGmtCreate())
                .append(getGmtModified())
                .toHashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getSeckillGoodId() {
        return seckillGoodId;
    }

    public void setSeckillGoodId(Long seckillGoodId) {
        this.seckillGoodId = seckillGoodId;
    }

    public Long getDeliveryInfoId() {
        return deliveryInfoId;
    }

    public void setDeliveryInfoId(Long deliveryInfoId) {
        this.deliveryInfoId = deliveryInfoId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public Integer getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(Integer orderChannel) {
        this.orderChannel = orderChannel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getPayDate() {
        return payDate;
    }

    public void setPayDate(Timestamp payDate) {
        this.payDate = payDate;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }
}
