package fre.shown.tryboot.service;

import fre.shown.tryboot.dao.UserDAO;
import fre.shown.tryboot.domain.UserDO;
import fre.shown.tryboot.util.DateUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Radon Freedom
 * created at 2019.04.01 上午11:36
 */

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Integer DEFAULT_PAGE_NUM = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 16;
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDO userDO = userDAO.getUserDetailsByUsername(username);
        if (userDO == null || userDO.getUsername() == null) {
            throw new UsernameNotFoundException("用户名'" + username + "'不存在!");
        }
        return userDO;
    }

    @Override
    public UserDO getUserDataByUsername(String username) {
        return userDAO.getUserDataByUsername(username);
    }

    @Override
    public List<UserDO> getSpecifiedPageUserData(Integer pageNum, Integer pageSize, String queryText) {

        if (pageNum == null) {
            pageNum = DEFAULT_PAGE_NUM;
        }
        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if ("".equals(queryText)) {
            queryText = null;
        }

        int begin = pageSize * (pageNum - 1);
        return userDAO.getUsersAsList(begin, pageSize, queryText);
    }

    @Override
    public Long getTotalPageCnt(Integer pageSize, String queryText) {
        Long totalUserCnt = userDAO.getTotalUserCnt(queryText);
        Long totalPageCnt = totalUserCnt / pageSize;
        if (totalUserCnt % pageSize != 0) {
            totalPageCnt++;
        }
        return totalPageCnt;
    }

    @Override
    public Boolean addUser(UserDO userDO) {

        try {
            userDO.setCreateTime(DateUtils.getCurrentTime());
            String encodedPassword = passwordEncoder.encode(userDO.getPassword());
            userDO.setPassword(encodedPassword);
            userDAO.addUser(userDO);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteUserByUsername(String username) {

        try {
            userDAO.deleteUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteUsers(String[] usernames) {
        if (usernames == null || usernames.length == 0) {
            return false;
        }

        try {
            for (String username : usernames) {
                userDAO.deleteUserByUsername(username);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
