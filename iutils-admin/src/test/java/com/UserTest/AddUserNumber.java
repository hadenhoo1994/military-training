package com.UserTest;

import cn.iutils.common.utils.sequence.IdWorker;
import cn.iutils.mt.entity.Project;
import cn.iutils.mt.entity.UserInfo;
import cn.iutils.mt.service.ProjectService;
import cn.iutils.mt.service.UserInfoService;
import org.apache.zookeeper.data.Id;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-config.xml"})
public class AddUserNumber {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ProjectService projectService;

    @Test
    public void setUserNumber() {
        List<UserInfo> userInfos = userInfoService.findList(new UserInfo());
        for (UserInfo u : userInfos) {
            u.setNumber(IdWorker.getInstance().nextId());
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            userInfoService.save(u);
        }
    }

    @Test
    public void SetProjectNumber() throws InterruptedException {
        List<Project> projects = projectService.findList(new Project());
        for (Project p: projects
             ) {
            p.setNumber(IdWorker.getInstance().nextId());
            Thread.sleep(100L);
            projectService.modify(p);
        }
    }
}
