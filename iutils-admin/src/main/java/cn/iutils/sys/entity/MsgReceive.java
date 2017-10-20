package cn.iutils.sys.entity;

import cn.iutils.sys.entity.DataEntity;

/**
 * 消息接收表
 *
 * @author iutils.cn
 * @version 1.0
 */
public class MsgReceive extends DataEntity<MsgReceive> {

    private static final long serialVersionUID = 1L;

    private String msgId;//消息编号

    private MsgSend msgSend;//消息主体

    public MsgReceive() {
        super();
    }

    public MsgReceive(String id) {
        super(id);
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public MsgSend getMsgSend() {
        return msgSend;
    }

    public void setMsgSend(MsgSend msgSend) {
        this.msgSend = msgSend;
    }
}
