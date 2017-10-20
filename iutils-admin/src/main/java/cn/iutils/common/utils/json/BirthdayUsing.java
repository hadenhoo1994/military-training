package cn.iutils.common.utils.json;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 将birthday格式转为 yyyy-mm-dd
 * Created by Haden on 2017/8/17.
 */
public class BirthdayUsing implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        Date birthday = (Date) o;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String bir = simpleDateFormat.format(birthday);
        jsonSerializer.write(bir);
    }

}
