package cn.iutils.mt.service;

import cn.iutils.mt.entity.rest.UserInfoSelectRes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.iutils.common.service.CrudService;
import cn.iutils.mt.dao.ProjectDao;
import cn.iutils.mt.entity.Project;

import java.util.ArrayList;
import java.util.List;

/**
* 军训项目表 Service层
* @author iutils.cn
* @version 1.0
*/
@Service
@Transactional(readOnly = true)
public class ProjectService extends CrudService<ProjectDao, Project> {

    public List<UserInfoSelectRes> getProjectByNameOrNumber(String name) {
        List<UserInfoSelectRes> userInfoSelectRes = new ArrayList<>();
        List<Project> userInfos = dao.getProjectByNameOrNumber(name);
        for (Project u: userInfos
                ) {
            UserInfoSelectRes usr = new UserInfoSelectRes();
            usr.setId(u.getId());
            usr.setText(u.getProjectName()+","+u.getNumber());
            userInfoSelectRes.add(usr);
        }
        return userInfoSelectRes;
    }
}
