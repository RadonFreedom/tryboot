package fre.shown.tryboot.service;

import fre.shown.tryboot.domain.UserDO;

import java.util.List;

/**
 * 与用户维护功能相关的服务
 * @author Radon Freedom
 * created at 2019.02.15 16:55
 */


public interface UserService {

    UserDO getUserDataByUsername(String username);

    /**
     *
     * 根据每页数据行数和第几页返回用户信息List
     *
     * @param pageSize 每页的数据行数
     * @param pageNum 第几页
     * @param queryText 查询文本
     * @return 指定页的用户List
     */
    List<UserDO> getSpecifiedPageUserData(Integer pageSize, Integer pageNum, String queryText);

    /**
     * 根据查询文本和每页条目数返回总页数
     *
     * @param pageSize 每页条目数
     * @param queryText 查询文本
     * @return 根据查询文本和每页条目数返回总页数
     */
    Long getTotalPageCnt(Integer pageSize, String queryText);

    /**
     * 新增用户，如果新增失败（UserDO的username属性值已存在）返回false。
     * 将调用{@link UserDO#setCreateTime(String)}来设置传入参数userDO的创建时间为执行到这个方法的时间。
     *
     * @param userDO 待新增的用户
     * @return 如果新增失败（UserDO的username属性值已存在）返回false，否则返回true
     */
    Boolean addUser(UserDO userDO);

    /**
     * 根据username确认user表需要删除的<B>唯一</B>条目
     * @param username 需要删除的username值
     * @return 若执行出现异常则返回false
     */
    Boolean deleteUserByUsername(String username);

    /**
     * 根据username确认user表需要删除的所有条目
     * @param usernames 包含所有需要删除的username值
     * @return 若usernames为空，长度为0，执行出现异常则返回false
     */
    Boolean deleteUsers(String[] usernames);
}
