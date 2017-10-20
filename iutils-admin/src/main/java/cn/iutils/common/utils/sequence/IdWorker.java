package cn.iutils.common.utils.sequence;

public class IdWorker {

    private static Sequence idWorker;

    static {
        idWorker = new Sequence(0, 0);
    }

    private IdWorker() {
    }

    public static Sequence getInstance() {
        return idWorker;
    }

}