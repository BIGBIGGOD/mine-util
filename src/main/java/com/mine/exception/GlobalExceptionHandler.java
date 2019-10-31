//package com.mine.exception;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.mine.controller.BaseController;
//import com.mine.enums.CommonEnum;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author jiangqingdong
// * @date 2017/2/20
// */

//import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
//@ResponseBody
//@Slf4j
//public class GlobalExceptionHandler extends BaseController {
//
////    public static final String DEFAULT_ERROR_VIEW = "error";
//
//    @ExceptionHandler(value = Exception.class)
//    public Result defaultErrorHandler(Exception e, HttpServletRequest request) {
//        log.info("请求地址url={},异常信息：", request.getRequestURL(), e);
////        ModelAndView mav = new ModelAndView();
////        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return failResponse(CommonEnum.FAILURE.getCode(),CommonEnum.FAILURE.getMessage());
//    }
//}
