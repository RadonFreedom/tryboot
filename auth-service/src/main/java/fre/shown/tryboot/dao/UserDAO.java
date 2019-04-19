package fre.shown.tryboot.dao;

import fre.shown.tryboot.domain.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

/**
 * 与类{@link fre.shown.tryboot.domain.UserDO}相关的DAO操作，与表user对应。
 *
 * @author Radon Freedom
 * created at 2019.02.13 20:39
 */

public interface UserDAO {
    /**
     *
     * @param username 用户名
     * @return 包含id, username, email.
     */
    UserDO getUserDataByUsername(String username);

    /**
     *
     * @param username 用户名
     * @return 包含用户认证信息.
     * @see org.springframework.security.core.userdetails.UserDetails
     */
    UserDO getUserDetailsByUsername(String username);

    /**
     * 返回从begin开始的数量为cnt个的用户List。
     *
     * @param begin     从第<B>begin</B>用户开始(exclusive)（按id升序排序）
     * @param cnt       返回的用户数目
     * @param queryText 查询的文本
     * @return 返回从begin开始的数量为cnt个的用户List。
     * 如果剩余用户量不足，将返回所有剩余用户。
     * 如果begin之后（inclusive）没有用户，返回空List。
     */
    List<UserDO> getUsersAsList(@Param("begin") Integer begin, @Param("cnt") Integer cnt, @Param("queryText") String queryText);

    /**
     * 返回user表总记录条数
     *
     * @param queryText 需要查询的文本
     * @return user表总记录条数
     */
    Long getTotalUserCnt(@Param("queryText") String queryText);


    /**
     * 新增用户，如果用户username已存在将抛出异常。
     * 方法参数 {@code userDO} 的id属性无效，不会被使用。
     *
     * @param userDO 新增用户信息，不包括id（即便包括也不会被使用）
     * @throws DataIntegrityViolationException 待插入用户的username已存在
     */
    Boolean addUser(UserDO userDO);

    /**
     * 根据username确认user表需要删除的<B>唯一</B>条目
     *
     * @param username 不能为空，否则抛出异常。可以为不存在的值
     */
    Boolean deleteUserByUsername(String username);
}
