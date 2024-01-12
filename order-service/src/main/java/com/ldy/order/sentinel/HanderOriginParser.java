package com.ldy.order.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 是不是sentinel使得这个拦截器生效的呢？答：sentinel如果不配置，这个拦截器会执行，但是没有拦截功能
 */
@Slf4j
@Component
public class HanderOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        String origin = httpServletRequest.getHeader("origin");

//        if (true){
//            return origin; //先把请求头拦截器置为无效
//        }


        log.info("origin的值为："+origin);
        if (StringUtils.isEmpty(origin)){
            origin="blank";
        }
        return origin;
    }
}
