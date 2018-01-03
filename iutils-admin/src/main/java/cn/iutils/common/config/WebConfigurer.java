package cn.iutils.common.config;

import cn.iutils.common.filter.FlyUserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
class WebConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    private FlyUserInterceptor flyUserInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(flyUserInterceptor).addPathPatterns("/fly/my/**");//配置登录拦截器拦截路径
    }

}