package cn.iutils.mt.service;

import cn.iutils.mt.entity.rest.UserInfoSelectRes;
import cn.iutils.sys.entity.User;
import com.mzlion.core.digest.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.iutils.common.service.CrudService;
import cn.iutils.mt.dao.UserInfoDao;
import cn.iutils.mt.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户表 Service层
 *
 * @author iutils.cn
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class UserInfoService extends CrudService<UserInfoDao, UserInfo> {

    /**
     * 用户登录
     * @param userInfo
     * @return
     */
    public UserInfo login(UserInfo userInfo) {
        UserInfo userInfoParam = new UserInfo();
        userInfoParam.setMobileNumber(userInfo.getMobileNumber());
//        userInfoParam.setPassword(MD5.digestBase64(userInfo.getPassword()));  //MD5验证密码
        userInfoParam.setPassword(userInfo.getPassword());
        return dao.findOne(userInfoParam);
    }

    /**
     * 根据手机号码查找用户
     * @param mobileNumber
     * @return
     */
    public boolean findUserByPhone(String mobileNumber) {
        UserInfo param = new UserInfo();
        param.setMobileNumber(mobileNumber);
        List<UserInfo> userInfos = dao.findList(param);
        if (userInfos.size()<1){
            //用户不存在
            return false;
        }else{
            //用户存在
            return  true;
        }
    }

    /**
     * 获取用户
     * @param seach
     * @return
     */
    public List<UserInfoSelectRes> getStudentByNameOrNumber(String seach) {
        List<UserInfoSelectRes> userInfoSelectRes = new ArrayList<>();
        List<UserInfo> userInfos = dao.getStudentByNameOrNumber(seach);
        for (UserInfo u: userInfos
             ) {
           UserInfoSelectRes usr = new UserInfoSelectRes();
           usr.setId(u.getId());
           usr.setText(u.getName()+","+u.getBattalionName()+u.getCompanyName()+u.getPlatoonName());
            userInfoSelectRes.add(usr);
        }
        return userInfoSelectRes;
    }
}
