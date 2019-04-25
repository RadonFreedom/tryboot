package fre.shown.tryboot.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Radon Freedom
 * created at 2019.04.19 下午8:21
 */

public class DeliveryInfoDO {

    private Long id;
    private Long userId;
    private String username;
    private String deliverAddress;
    private String phoneNumber;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userId", userId)
                .append("username", username)
                .append("deliverAddress", deliverAddress)
                .append("phoneNumber", phoneNumber)
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

        DeliveryInfoDO that = (DeliveryInfoDO) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getUserId(), that.getUserId())
                .append(getUsername(), that.getUsername())
                .append(getDeliverAddress(), that.getDeliverAddress())
                .append(getPhoneNumber(), that.getPhoneNumber())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getUserId())
                .append(getUsername())
                .append(getDeliverAddress())
                .append(getPhoneNumber())
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeliverAddress() {
        return deliverAddress;
    }

    public void setDeliverAddress(String deliverAddress) {
        this.deliverAddress = deliverAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
