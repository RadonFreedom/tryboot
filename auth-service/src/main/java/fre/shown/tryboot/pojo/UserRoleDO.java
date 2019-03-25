package fre.shown.tryboot.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Radon Freedom
 * created at 2019.03.23 下午8:57
 */

public class UserRoleDO {
    private String userId;
    private Integer roleId;

    public UserRoleDO() {
    }

    public UserRoleDO(String userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserRoleDO that = (UserRoleDO) o;

        return new EqualsBuilder()
                .append(getUserId(), that.getUserId())
                .append(getRoleId(), that.getRoleId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getUserId())
                .append(getRoleId())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "UserRoleDO{" +
                "userId='" + userId + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
