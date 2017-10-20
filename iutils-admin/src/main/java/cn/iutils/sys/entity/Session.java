package cn.iutils.sys.entity;

import cn.iutils.sys.entity.DataEntity;

/**
 * session管理
 *
 * @author iutils.cn
 * @version 1.0
 */
public class Session extends DataEntity<Session> {

    private static final long serialVersionUID = 1L;

    private String userId;//用户编号
    private String ip;//IP地址
    private long timeout;//过期时间
    private String sessionStr;//session对象

    public Session() {
        super();
    }

    public Session(String id) {
        super(id);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public String getSessionStr() {
        return sessionStr;
    }

    public void setSessionStr(String sessionStr) {
        this.sessionStr = sessionStr;
    }

}
