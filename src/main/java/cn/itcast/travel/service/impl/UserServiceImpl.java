package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;

/**
 * Created with IntelliJ IDEA.
 * Author: JHong.Tao
 * Date: 2019-09-11-14:47
 * Version：1.0.0
 * Description:
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        // 1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        // 判断u是否为null
        if(u != null){
            // 用户名已经存在，注册失败
            return false;
        }

        // 2.保存用户信息,注册成功
        userDao.save(user);
        return true;
    }
}
