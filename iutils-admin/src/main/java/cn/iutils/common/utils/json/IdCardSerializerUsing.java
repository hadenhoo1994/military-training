package cn.iutils.common.utils.json;

import cn.iutils.common.utils.StringUtils;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 转化身份证号码时
 * Created by Haden on 2017/7/18.
 */
public class IdCardSerializerUsing implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object object, Object fieldName, Type fieldType, int i) throws IOException {
        String value = (String) object;
        if(StringUtils.isBlank(value)){
            jsonSerializer.write(value);
            return;
        }
        if (value.length()<15){
            jsonSerializer.write(value);
            return;
        }
        StringBuffer idNumber = new StringBuffer(value);
        idNumber.replace(6, 14, "********");
        jsonSerializer.write(idNumber.toString());
    }
}
