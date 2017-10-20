package cn.iutils.common.utils.weather;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.util.Date;

/**
 * Created by yang on 17/6/19.
 */
public class SenseWeather {

    // 逐日天气预报
    private String TIANQI_DAILY_WEATHER_URL = "https://api.seniverse.com/v3/weather/daily.json";

    // 天气实况
    private String TIANQI_NOW_WEATHER_URL = "https://api.seniverse.com/v3/weather/now.json";

    // 生活指数
    private String TIANQI_SEARCH_LIFE_URL = "https://api.seniverse.com/v3/life/suggestion.json";

    // 城市搜索
    private String TIANQI_SUGGESTION_LIFE_URL = "https://api.seniverse.com/v3/life/suggestion.json";

    private String TIANQI_API_SECRET_KEY = "sx22jkvjxzc4snns";

    private String TIANQI_API_USER_ID = "UFB44B627A";

    /**
     * Generate HmacSHA1 signature with given data string and key
     *
     * @param data
     * @param key
     * @return
     * @throws SignatureException
     */
    private String generateSignature(String data, String key) throws SignatureException {
        String result;
        try {
            // get an hmac_sha1 key from the raw key bytes
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA1");
            // get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            // compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
            Base64 base64 = new Base64();
            result = new String(base64.encode(rawHmac));
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

    /**
     * Generate the URL to get diary weather
     *
     * @param location
     * @param language
     * @param unit
     * @param start
     * @param days
     * @return
     */
    public String generateGetDiaryWeatherURL(
            String location,
            String language,
            String unit,
            String start,
            String days
    ) throws SignatureException, UnsupportedEncodingException {
        String timestamp = String.valueOf(new Date().getTime());
        String params = "ts=" + timestamp + "&ttl=30&uid=" + TIANQI_API_USER_ID;
        String signature = URLEncoder.encode(generateSignature(params, TIANQI_API_SECRET_KEY), "UTF-8");
        return TIANQI_DAILY_WEATHER_URL + "?" + params + "&sig=" + signature + "&location=" + location + "&language=" + language + "&unit=" + unit + "&start=" + start + "&days=" + days;
    }

    public static void main(String args[]) {
        SenseWeather weather = new SenseWeather();
        try {
            String url = weather.generateGetDiaryWeatherURL(
                    "shanghai",
                    "zh-Hans",
                    "c",
                    "1",
                    "1"
            );
            System.out.println("URL:" + url);

        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

    }
}
