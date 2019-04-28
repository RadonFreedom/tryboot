package fre.shown.tryboot.domain.order;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;

/**
 * @author Radon Freedom
 * created at 2019.04.24 下午8:37
 */

public class SeckillOrderDO {

    private Long id;
    private String username;
    private Long goodId;
    private Long seckillGoodId;
    private Long deliveryInfoId;
    private String goodName;
    private Integer goodCount;
    private Double goodPrice;
    private Double seckillPrice;
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
                .append("goodId", goodId)
                .append("deliveryInfoId", deliveryInfoId)
                .append("goodName", goodName)
                .append("goodCount", goodCount)
                .append("seckillPrice", seckillPrice)
                .append("goodPrice", goodPrice)
                .append("orderChannel", orderChannel)
                .append("status", status)
                .append("payDate", payDate)
                .append("gmtCreate", gmtCreate)
                .append("gmtModified", gmtModified)
                .toString();
    }

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
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
