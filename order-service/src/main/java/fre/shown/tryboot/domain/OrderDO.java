package fre.shown.tryboot.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;

/**
 * @author Radon Freedom
 * created at 2019.04.24 下午8:37
 */

public class OrderDO {

    private Long id;
    private Long userId;
    private Long goodId;
    private Long deliveryAddrId;
    private String goodName;
    private Integer goodCount;
    private Double goodPrice;
    private Integer orderChannel;
    private Integer status;
    private Timestamp payDate;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    public OrderDO() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userId", userId)
                .append("goodId", goodId)
                .append("deliveryAddrId", deliveryAddrId)
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

        OrderDO orderDO = (OrderDO) o;

        return new EqualsBuilder()
                .append(getId(), orderDO.getId())
                .append(getUserId(), orderDO.getUserId())
                .append(getGoodId(), orderDO.getGoodId())
                .append(getDeliveryAddrId(), orderDO.getDeliveryAddrId())
                .append(getGoodName(), orderDO.getGoodName())
                .append(getGoodCount(), orderDO.getGoodCount())
                .append(getGoodPrice(), orderDO.getGoodPrice())
                .append(getOrderChannel(), orderDO.getOrderChannel())
                .append(getStatus(), orderDO.getStatus())
                .append(getPayDate(), orderDO.getPayDate())
                .append(getGmtCreate(), orderDO.getGmtCreate())
                .append(getGmtModified(), orderDO.getGmtModified())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getUserId())
                .append(getGoodId())
                .append(getDeliveryAddrId())
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public Long getDeliveryAddrId() {
        return deliveryAddrId;
    }

    public void setDeliveryAddrId(Long deliveryAddrId) {
        this.deliveryAddrId = deliveryAddrId;
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
