package cn.iutils.common.utils.json;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class SpecialtyNameSerializerUsing implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object object, Object fieldName, Type fieldType, int i) throws IOException {
        String value = (String) object;
        if (value.indexOf("（") >= 0) {
            value = value.substring(0, value.indexOf("（"));
        }
        jsonSerializer.write(value);
    }
}
