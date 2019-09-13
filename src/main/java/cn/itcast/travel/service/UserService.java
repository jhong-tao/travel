package cn.itcast.travel.service;

import cn.itcast.travel.domain.User; /**
 * Created with IntelliJ IDEA.
 * Author: JHong.Tao
 * Date: 2019-09-11-14:45
 * Version：1.0.0
 * Description:
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user);

    /**
     * 用户激活
     * @param code
     * @return
     */
    boolean active(String code);
}
