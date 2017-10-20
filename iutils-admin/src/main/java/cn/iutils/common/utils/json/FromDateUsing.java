package cn.iutils.common.utils.json;

import cn.iutils.common.utils.DateUtils;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/** 将createDate转为多久前
 * Created by Haden on 2017/8/17.
 */
public class FromDateUsing implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        Date createDate = (Date) o;
        String fromDate = DateUtils.fromToday(createDate);
        jsonSerializer.write(fromDate);
    }
}
