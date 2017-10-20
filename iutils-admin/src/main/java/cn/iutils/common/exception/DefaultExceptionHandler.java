package cn.iutils.common.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常处理器
 */
public class DefaultExceptionHandler implements HandlerExceptionResolver {

    private static Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ex.printStackTrace();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", ex.getMessage());
        //如果抛出的是系统自定义的异常则直接转换
        if (ex instanceof ApiException) {
            // 使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常
            FastJsonJsonView view = new FastJsonJsonView();
            Map<String, Object> attributes = new HashMap<String, Object>();
            attributes.put("code", ((ApiException) ex).getCode());
            attributes.put("message", ((ApiException) ex).getMessage());
            view.setAttributesMap(attributes);
            modelAndView.setView(view);
            return modelAndView;
        } else if (ex instanceof BusinessException) {
            modelAndView.setViewName("error/error");
        } else if (ex instanceof Throwable) {
            modelAndView.setViewName("error/500");
        } else if (ex instanceof UnauthorizedException) {
            modelAndView.setViewName("error/403");
        } else {
            modelAndView.setViewName("error/404");
        }

        //向前台返回错误信息
        return modelAndView;

    }
}