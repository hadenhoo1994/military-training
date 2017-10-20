package cn.iutils.common.utils.json;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 转化为视频链接增加头部
 */
public class VideoUrlSerializerUsing implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object object, Object fieldName, Type fieldType, int i) throws IOException {
        String value = (String) object;
        String fileName = value.substring(value.lastIndexOf("/") + 1);
        String path = "video/" + fileName;
        String url = "http://tj-oss.tujiakeji.com/" + path;
        jsonSerializer.write(url);
    }
}
