package cn.iutils.sys.service;

import cn.iutils.common.service.CrudService;
import cn.iutils.common.utils.CacheUtils;
import cn.iutils.common.utils.JStringUtils;
import cn.iutils.common.utils.UserUtils;
import cn.iutils.sys.dao.IUserDao;
import cn.iutils.sys.entity.Role;
import cn.iutils.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 用户服务
 *
 * @author cc
 */
@Service
@Transactional(readOnly = true)
public class UserService extends CrudService<IUserDao, User> {

    @Autowired
    private PasswordHelper passwordHelper;

    @Autowired
    private RoleService roleService;

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    @Transactional(readOnly = false)
    public void changePassword(String userId, String password,
                               String newPassword) throws Exception {
        User user = dao.get(userId);
        String oldPassword = user.getPassword();
        user.setPassword(password);
        String myPassword = passwordHelper.getPassword(user);
        if (oldPassword.equals(myPassword)) {
            user.setPassword(newPassword);
            passwordHelper.encryptPassword(user);
            save(user);
            CacheUtils.put(user.getUsername(), user);
        } else {
            throw new RuntimeException("原密码错误");
        }
    }

    /**
     * 修改登录密码
     *
     * @param user
     * @param newPassword
     */
    @Transactional(readOnly = false)
    public void changePassword(User user, String newPassword) throws Exception {
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        save(user);
        CacheUtils.put(user.getUsername(), user);
    }

    /**
     * 根据用户名查找其角色
     *
     * @return
     */
    public Set<String> findRoles() {
        User user = UserUtils.getLoginUser();
        if (user == null) {
            return Collections.emptySet();
        }
        return roleService.findRoles(user.getRoleIds().toArray(new String[0]));
    }

    /**
     * 获取指定用户的角色对象集合
     *
     * @param userId 用户名
     * @return
     */
    public List<Role> findRolesOfUser(String userId) {
        User user = get(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        List<Role> roles = new ArrayList<Role>();
        for (String roleId : user.getRoleIds()) {
            roles.add(roleService.get(roleId));
        }
        return roles;
    }

    /**
     * 根据用户名查找其权限
     *
     * @return
     */
    public Set<String> findPermissions() {
        User user = UserUtils.getLoginUser();
        if (user == null) {
            return Collections.emptySet();
        }
        return roleService.findPermissions(user.getRoleIds().toArray(
                new String[0]));
    }

    /**
     * 根据登录名获取用户
     *
     * @param userName
     * @return
     */
    public User getUserByUserName(String userName) {
        if (JStringUtils.isBlank(userName)) {
            return null;
        }
        User user = null;
        try {
            user = (User) CacheUtils.get(userName);
            if (user == null) {
                user = dao.getUserByUserName(userName);
                CacheUtils.put(userName, user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 获取用户列表
     *
     * @param users
     * @return
     */
    public List<Map> getUsers(String[] users) {
        return dao.getUsers(users);
    }

}
