package cn.iutils.common.utils.json;

import cn.iutils.common.utils.DateUtils;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 转化为视频时长
 */
public class VideoTimeSerializerUsing implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object object, Object fieldName, Type fieldType, int i) throws IOException {
        String value = (String) object;
        String time = DateUtils.secToTime(Integer.parseInt(value));
        jsonSerializer.write(time);
    }
}
