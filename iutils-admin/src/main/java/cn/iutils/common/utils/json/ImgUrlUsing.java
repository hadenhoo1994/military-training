package cn.iutils.common.utils.json;

import cn.iutils.common.utils.StringUtils;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2018/1/6.
 */
public class ImgUrlUsing implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        String url = String.valueOf(o);
        if (StringUtils.isBlank(url)){
            jsonSerializer.write("/static/assets/img/user.png");
        }else{
            jsonSerializer.write(url);
        }
    }
}
