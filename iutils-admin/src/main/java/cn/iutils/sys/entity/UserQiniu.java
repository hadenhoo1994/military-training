package cn.iutils.sys.entity;

import cn.iutils.common.BaseEntity;

/**
 * 用户七牛配置
 *
 * @author MyCode
 * @version 1.0
 */
public class UserQiniu extends DataEntity<UserQiniu> {

    private static final long serialVersionUID = 1L;

    private String domain;// 域名
    private String access;// AK
    private String secret;// SK
    private String pub;// PUB
    private String pri;// PRI

    public UserQiniu() {
        super();
    }

    public UserQiniu(String id) {
        super(id);
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

}
