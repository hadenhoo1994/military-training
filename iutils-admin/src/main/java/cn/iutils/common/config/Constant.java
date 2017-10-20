package cn.iutils.common.config;

/**
 * 常量
 * Created by ASUS on 2017/6/14.
 */
public class Constant {

    /**
     * 文件保存位置
     */
    public final static String STUDENT_ICON = "/file/studentIcon/";
    public final static String STUDENT_ICON_PATH = "http://data.haohaobang.com.cn:8888/";
    public final static String STATUS_NORMAL = "0";
    public final static String STATUS_DELETE = "1";

    public static final int NO = 0;
    public static final int YES = 1;

    public static final String NIL = "";
    public static final String LINE = "-";

    // 登录验证码
    public static final String SMS_VERIFY = "SMS_VERIFY_";

    /**========================================登录类型====================================================*/
    /**
     * 手机短信登录
     */
    public static final String LOGIN_TYPE_SMS = "0";
    /**
     * 微信登录
     */
    public static final String LOGIN_TYPE_WECHAT = "1";
    /**
     * 密码登录
     */
    public static final String LOGIN_TYPE_PSW = "2";

    /**
     * ========================================客户端类型==================================================
     */
    //微信小程序
    public static final String CLIENT_WX_LITE = "0";
    //微信WEB
    public static final String CLIENT_WX_WEB = "1";
    //Android
    public static final String CLIENT_APP_ANDROID = "2";
    //iOS
    public static final String CLIENT_APP_IOS = "3";
    //PC
    public static final String CLIENT_APP_PC = "4";

    /**
     * ========================================业务类型==================================================
     */
    public static final String CHOOSE_TUTOR = "1";  //选择老师教我
    public static final String CHOOSE_STUDENT = "2"; //选择我要教谁

    /**
     * ========================================登录渠道==================================================
     */

    //微信小程序
    public static final int SOCIAL_WX_LITE = 0;
    //微信WEB
    public static final int SOCIAL_WX_WEB = 1;
    //微信APP
    public static final int SOCIAL_WX_APP = 2;
    //QQ
    public static final int SOCIAL_QQ = 3;
    //微博
    public static final int SOCIAL_WB = 4;

    /**
     * =======================================微信回调返回码===============================================
     */
    public static final String WX_PAY_TITLE = "预兔职场订单";
    public static final String WX_NOTIFY_SUCCESS = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";    //微信
    public static final String WX_NOTIFY_FAIL = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[FAIL]]></return_msg></xml>";    //微信

    /**=======================================返回码======================================================*/
    /**
     * rest接口返回数据JSON
     * <p>
     * 200 - 请求成功
     * <p>
     * HTTP 400 - 请求无效
     * HTTP 401 - 未授权：登录失败
     * HTTP 403 - 禁止访问
     * HTTP 404 - 无法找到文件
     * HTTP 405 - 资源被禁止
     * HTTP 406 - 无法接受
     * HTTP 407 - 要求代理身份验证
     * HTTP 410 - 永远不可用
     * HTTP 412 - 先决条件失败
     * HTTP 414 - 请求 - URI 太长
     * HTTP 500 - 内部服务器错误
     * <p>
     * 600 - 未知错误
     * 601 - 参数不足
     * 602 - 参数有误
     * 603 - 短信验证码不正确
     * 611 - 没有找到对象
     * 621 - 用户不存在
     * 622 - 用户个人资料未完善
     * 623 - 用户未完成实名认证
     * 624 - 用户未完成师傅认证
     * 651 - 该手机号码已被绑定
     * 652 - 该微信已被其他用户绑定
     * 653 - 绑定信息异常
     * 660 - 金额有误
     * 661 - 余额不足
     * <p>
     */
    public static final String SUCCESS_CODE = "200";
    public static final String ERROR_CODE = "400";
    public static final String BACK_NO_AUTH = "401";
    public static final String FAILURE_CODE = "600";
    public static final String PARAMETER_INCORRECT = "601";
    public static final String PARAMETER_ERR = "602";
    public static final String SMS_CODE_ERRO = "603";
    public static final String OBJECT_NOT_FOUND = "611";
    public static final String USER_NOT_EXISTENT = "621";
    public static final String USERDATA_NOT_DONE = "622";
    public static final String USER_NOT_AUDIT = "623";
    public static final String USER_NOT_TOTUR = "624";
    public static final String MOBILE_WAS_BINDING = "651";
    public static final String WX_WAS_BINDING = "652";
    public static final String WX_BINDING_ERRO = "653";
    public static final String AMOUNT_OF_MONEY_ERR = "660";
    public static final String AMOUNT_LESS="661";

    public static final String ERROR = "error";
    public static final String SUCCESS = "ok";
    public static final String FAILURE = "fail";

    public static final String ERROR_MSG = "系统异常，请联系管理员";
    public static final String FAILURE_MSG = "系统繁忙,请稍后重试";
    public static final String SUCCESS_MSG = "接口调用成功";

    /**
     * =======================================日 时 分 秒======================================================
     */
    public static final long DAY = 24 * 60 * 60 * 1000;     //天
    public static final long HOUR = 60 * 60 * 1000;    //时
    public static final long MINUTE = 60 * 1000;  //分
    public static final long SECOND = 1000; // 秒
}
