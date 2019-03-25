package fre.shown.tryboot.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Radon Freedom
 * created at 2019.03.23 下午8:57
 */

public class RoleDO {

    private Integer roleId;
    private String roleName;

    public RoleDO() {
    }

    public RoleDO(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RoleDO roleDO = (RoleDO) o;

        return new EqualsBuilder()
                .append(roleId, roleDO.roleId)
                .append(roleName, roleDO.roleName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(roleId)
                .append(roleName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "RoleDO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
