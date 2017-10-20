package cn.iutils.sys.entity;

/**
 * session管理
 *
 * @author iutils.cn
 * @version 1.0
 */
public class Sessions extends DataEntity<Sessions> {

    private static final long serialVersionUID = 1L;

    private String session;//session对象

    public Sessions() {
        super();
    }

    public Sessions(String id) {
        super(id);
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

}
