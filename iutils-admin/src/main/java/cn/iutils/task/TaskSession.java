package cn.iutils.task;

import cn.iutils.common.service.BaseService;
import cn.iutils.common.spring.SpringUtils;
import cn.iutils.common.utils.SerializableUtils;
import cn.iutils.sys.entity.Sessions;
import cn.iutils.sys.service.SessionService;
import org.apache.shiro.session.Session;

import java.util.List;

/**
 * 在线会话验证任务 抽象类 每个项目要实现
 *
 * @author iutils.cn
 */
public abstract class TaskSession extends BaseService {

    /**
     * session服务
     */
    private SessionService sessionService = SpringUtils.getBean(SessionService.class);

    /**
     * 执行任务
     */
    public void run() {
        //处理会话
        doSession(new Sessions());
    }

    /**
     * 处理会话
     *
     * @param dbSession
     * @return
     */
    private void doSession(Sessions dbSession) {
        Sessions lastDbSession = new Sessions();
        List<Sessions> sessionList = sessionService.findList(dbSession);
        for (int i = 0; i < sessionList.size(); i++) {
            Sessions session = sessionList.get(i);
            Session session1 = SerializableUtils.deserialize(session.getSession());
            //获取当前时间
            long nowTime = System.currentTimeMillis();
            long lastTime = session1.getLastAccessTime().getTime();
            if (nowTime - lastTime > session1.getTimeout()) {
                //已过期，删除记录
                sessionService.delete(session.getId().toString());
                doOtherSession(session);
            }
            if (i == sessionList.size() - 1) {
                lastDbSession = sessionList.get(i);
            }
        }
        if (sessionList.size() == 100) {//每次处理100条
            //继续处理下页
            doSession(lastDbSession);
        }
    }

    /**
     * @param session
     */
    public abstract void doOtherSession(Sessions session);

}
