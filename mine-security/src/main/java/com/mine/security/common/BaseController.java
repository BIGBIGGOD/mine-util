package com.mine.security.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mine.common.enums.CommonEnum;

/**
 * Created by jiangqingdong on 2019/1/24.
 */
public abstract class BaseController {

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request;
    }

    public HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
        return response;
    }

    public Result response(int code, String message, Object model) {
        Result result = new Result();
        result.setRetCode(code);
        result.setMsg(message);
        if (model != null) {
            result.setModel(model);
        }
        return result;
    }

    public Result successResponse() {
        Result result = new Result();
        result.setRetCode(CommonEnum.SUCCESS.getCode());
        result.setMsg(CommonEnum.SUCCESS.getMessage());
        return result;
    }

    public Result successResponse(Object model) {
        Result result = new Result();
        result.setRetCode(CommonEnum.SUCCESS.getCode());
        result.setMsg(CommonEnum.SUCCESS.getMessage());
        result.setModel(model);
        return result;
    }

    public Result failResponse(int code, String message) {
        Result result = new Result();
        result.setRetCode(code);
        result.setMsg(message);
        return result;
    }

    public Result failResponse(CommonEnum commonEnum) {
        Result result = new Result();
        result.setRetCode(commonEnum.getCode());
        result.setMsg(commonEnum.getMessage());
        return result;
    }


    public ModelAndView createMav(String viewName, Map<String, ?> model) {
        return new ModelAndView(viewName, model);
    }

    public int getStart(Integer start) {
        return start == null ? 0 : start.intValue();
    }

    public int getLimit(Integer limit) {
        return limit == null ? 10 : limit.intValue();
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Result {
        private int retCode;

        private String msg;

        private Object model;

        public int getRetCode() {
            return retCode;
        }

        public void setRetCode(int retCode) {
            this.retCode = retCode;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Object getModel() {
            return model;
        }

        public void setModel(Object model) {
            this.model = model;
        }


    }
}
