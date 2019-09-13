package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

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
        System.out.println(user.getUsername().toString());
        User u = userDao.findByUsername(user.getUsername());
        // 判断u是否为null
        if(u != null){
            // 用户名已经存在，注册失败
            return false;
        }

        // 2.保存用户信息,注册成功
        // 2.1设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        // 2.2设置激活状态
        user.setStatus("N");
        userDao.save(user);

        // 3.激活邮件发送，邮件正文
        String content = "<a href='http://localhost/travel/activeUserServlet?code="+user.getCode()+"'>点击激活【黑马旅游网】</a>";
        MailUtils.sendMail(user.getEmail(),content,"黑马旅游网邮件激活");
        return true;
    }

    @Override
    public boolean active(String code) {
        // 1.根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if(user != null){
             // 2.调用dao修改激活状态方法
            userDao.updateStatus(user);
            return true;
        }else {
            return false;
        }
    }
}
