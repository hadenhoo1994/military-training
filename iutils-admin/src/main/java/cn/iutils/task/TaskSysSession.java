package cn.iutils.task;

import cn.iutils.sys.entity.Sessions;

/**
 * 默认系统Session处理
 *
 * @author iutils.cn
 */
public class TaskSysSession extends TaskSession {

    /**
     * 开始执行
     */
    public void start() {
        super.run();//覆盖后必须要调用super.run()
    }

    /**
     * 其他逻辑处理，给用户做其他事情
     *
     * @param session
     */
    @Override
    public void doOtherSession(Sessions session) {

    }

}
