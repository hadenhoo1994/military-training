package cn.iutils.common.utils.json;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 转化为视频链接增加头部
 */
public class UrlSerializerUsing implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object object, Object fieldName, Type fieldType, int i) throws IOException {
        String value = (String) object;
        String url = "http://www.maiziedu.com" + value;
        jsonSerializer.write(url);
    }
}
