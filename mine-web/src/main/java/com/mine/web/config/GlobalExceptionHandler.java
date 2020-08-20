package com.mine.web.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mine.common.enums.CommonEnum;
import com.mine.common.exception.BasesException;
import com.mine.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @date 2017/2/20
 */

@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler extends BaseController {

//    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public Result defaultErrorHandler(Exception e, HttpServletRequest request) {
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName(DEFAULT_ERROR_VIEW);
        if (e instanceof BasesException) {
            log.warn("请求报错，地址url={},异常信息:{}", request.getRequestURL(), e.getMessage());
            return failResponse(((BasesException) e).getCode(), e.getMessage());
        } else if (e instanceof AccessDeniedException) {
            log.warn("权限不足，不允许访问，地址url={},异常信息:{}", request.getRequestURL(), e.getMessage());
        }
        log.error("请求地址url={},异常信息：", request.getRequestURL(), e);
        return failResponse(CommonEnum.FAILURE.getCode(), CommonEnum.FAILURE.getMessage());
    }
}
