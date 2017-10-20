package cn.iutils.sys.dao;

import cn.iutils.common.Servlets;
import cn.iutils.common.config.JConfig;
import cn.iutils.common.spring.SpringUtils;
import cn.iutils.common.utils.JStringUtils;
import cn.iutils.common.utils.SerializableUtils;
import cn.iutils.sys.entity.Sessions;
import cn.iutils.sys.service.SessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 自定义实现SessionDAO，实现session基于数据库层会话共享
 * 先从缓存中获取，找不到再从数据库获取
 *
 * @author iutils.cn
 */
public class IDBSessionDAO extends CachingSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(IDBSessionDAO.class);

    /**
     * session服务
     */
    private SessionService sessionService = SpringUtils.getBean(SessionService.class);

    /**
     * Session更新
     *
     * @param session
     */
    @Override
    protected void doUpdate(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }
        HttpServletRequest request = Servlets.getRequest();
        if (request != null) {
            String uri = request.getServletPath();
            // 如果是静态文件，则不更新SESSION
            if (Servlets.isStaticFile(uri)) {
                return;
            }
            // 如果是视图文件，则不更新SESSION
            if (JStringUtils.startsWith(uri, JConfig.getConfig("web.view.prefix"))
                    && JStringUtils.endsWith(uri, JConfig.getConfig("web.view.suffix"))) {
                return;
            }
        }
        //保存到数据库
        Sessions dbSession = new Sessions();
        //序列化Session
        dbSession.setSession(SerializableUtils.serialize(session));
        dbSession.setId(session.getId().toString());
        sessionService.update(dbSession);
    }

    /**
     * Session删除
     *
     * @param session
     */
    @Override
    protected void doDelete(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }
        //过期删除
        sessionService.delete(session.getId().toString());
    }

    /**
     * Session创建
     *
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        HttpServletRequest request = Servlets.getRequest();
        if (request != null) {
            String uri = request.getServletPath();
            // 如果是静态文件，则不创建SESSION
            if (Servlets.isStaticFile(uri)) {
                return null;
            }
        }
        //获取自定义 SessionID
        Serializable sessionId = generateSessionId(session);
        //设置SessionID
        assignSessionId(session, sessionId);
        //保存到数据库
        Sessions dbSession = new Sessions();
        //序列化Session
        dbSession.setSession(SerializableUtils.serialize(session));
        dbSession.setId(session.getId().toString());
        dbSession.setCreateDate(session.getStartTimestamp());
        sessionService.add(dbSession);
        return session.getId();
    }

    /**
     * Session 读取
     *
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = null;
        logger.info("session: " + sessionId);
        Sessions dbSession = sessionService.get(sessionId.toString());
        if (dbSession != null) {
            session = SerializableUtils.deserialize(dbSession.getSession());
        }
        return session;
    }

}
