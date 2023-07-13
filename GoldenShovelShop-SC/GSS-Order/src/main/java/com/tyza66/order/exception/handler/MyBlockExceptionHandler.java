package com.tyza66.goods.exception.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyza66.goods.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: tyza66
 * Date: 2023/7/10 16:10
 * Github: https://github.com/tyza66
 **/

//配置全局的错误处理方法
@Slf4j
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        //Logger loger = (Logger) LoggerFactory.getLogger(this.getClass()); 这样可以不用lombok获得log对象
        log.info("BlockExceptionHandler BlockException================" + e.getRule());
        Result r = null;
        if (e instanceof FlowException) {
            r = Result.error(100, "接口限流了");
        } else if (e instanceof DegradeException) {
            r = Result.error(101, "服务降级了");
        } else if (e instanceof ParamFlowException) {
            r = Result.error(102, "热点参数限流了");
        } else if (e instanceof SystemBlockException) {
            r = Result.error(103, "触发系统保护规则了");
        } else if (e instanceof AuthorityException) {
            r = Result.error(104, "授权规则不通过");
        }

        //返回ison数据
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(httpServletResponse.getWriter(),r);
    }
}