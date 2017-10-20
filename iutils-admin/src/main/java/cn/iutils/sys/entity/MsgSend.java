package cn.iutils.sys.entity;

import cn.iutils.sys.entity.enums.MsgEnum;

/**
 * 消息发送表
 *
 * @author iutils.cn
 * @version 1.0
 */
public class MsgSend extends DataEntity<MsgSend> {

    private static final long serialVersionUID = 1L;

    private MsgEnum type;//消息类型  系统通知  站内信 用户通知
    private String title;//标题
    private String content;//内容
    private int level;//级别 -1低 0普通 1高
    private String users;//发送给

    public MsgSend() {
        super();
    }

    public MsgSend(String id) {
        super(id);
    }

    public MsgEnum getType() {
        return type;
    }

    public void setType(MsgEnum type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
