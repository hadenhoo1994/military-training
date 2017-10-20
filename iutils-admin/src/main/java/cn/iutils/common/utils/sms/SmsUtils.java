package cn.iutils.common.utils.sms;

import cn.iutils.common.config.Constant;
import cn.iutils.common.utils.JedisUtils;
import cn.iutils.common.utils.PropertiesUtil;
import cn.iutils.common.utils.StringUtils;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 * <p>
 * 备注:Demo工程编码采用UTF-8
 * 国际短信发送请勿参照此DEMO
 */
public class SmsUtils {

    private static Properties properties = PropertiesUtil.loadProperties("classpath:aliyun.properties");

    public static String getConfig(String key) {
        return properties.getProperty(key);
    }

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = getConfig("sms.product");
    //产品域名,开发者无需替换
    static final String domain = getConfig("sms.domain");

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = getConfig("access.key.id");
    static final String accessKeySecret = getConfig("access.key.id");

    public static SendSmsResponse sendSms(String phoneNumber, String signName, String templateCode, String templateParam, String outId) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化 cn-beijing,cn-hangzhou,cn-qingdao,cn-shenzhen
        IClientProfile profile = DefaultProfile.getProfile("cn-shenzhen", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-shenzhen", "cn-shenzhen", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //模版内容:验证码${code}，您正在注册成为新用户，感谢您的支持！
        request.setTemplateParam(templateParam);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId(outId);

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    public static QuerySendDetailsResponse querySendDetails(String bizId, String phoneNumber) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化 cn-beijing,cn-hangzhou,cn-qingdao,cn-shenzhen
        IClientProfile profile = DefaultProfile.getProfile("cn-shenzhen", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-shenzhen", "cn-shenzhen", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(phoneNumber);
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;
    }

    /**
     * 发送验证码
     *
     * @param phoneNumber
     * @param param
     * @return
     * @throws ClientException
     */
    public static boolean sendVerifyCode(String phoneNumber, String param) throws ClientException {
        param = "123456";
        String outId = Constant.SMS_VERIFY + phoneNumber;
        Map<String, String> templateParam = new HashMap<String, String>();
        templateParam.put("code", param);
//        SendSmsResponse response = sendSms(phoneNumber, "阿里云短信测试专用", "SMS_80300409", JSON.toJSONString(templateParam), outId);
//        if (response.getCode() != null && response.getCode().equals("OK")) {
        JedisUtils.set(outId, param, 60);
//            return true;
//        }
        return true;
//        return false;
    }

    /**
     * 检测验证码
     *
     * @param phoneNumber
     * @param code
     * @return
     * @throws ClientException
     */
    public static boolean checkVerifyCode(String phoneNumber, String code) throws ClientException {
        String outId = Constant.SMS_VERIFY + phoneNumber;
        if (StringUtils.isNoneBlank(phoneNumber) && StringUtils.isNoneBlank(code) && JedisUtils.exists(outId)) {
            String s = JedisUtils.get(outId);
            if (s.equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws ClientException, InterruptedException {

        boolean b = sendVerifyCode("17755111109", "654321");
        System.out.println(b);

//        //发短信
//        SendSmsResponse response = sendSms("17755111109", "阿里云短信测试专用", "SMS_80300409", "{\"code\":\"123456\"}", "654321");
//        System.out.println("短信接口返回的数据----------------");
//        System.out.println("Code=" + response.getCode());
//        System.out.println("Message=" + response.getMessage());
//        System.out.println("RequestId=" + response.getRequestId());
//        System.out.println("BizId=" + response.getBizId());
//
//        Thread.sleep(3000L);
//
//        //查明细
//        if (response.getCode() != null && response.getCode().equals("OK")) {
//            System.out.println("短信发送成功!");
//        }

    }
}
