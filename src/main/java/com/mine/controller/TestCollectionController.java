package com.mine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试
 *
 * @author jiangqingdong
 * @version V1.0
 * @Description
 * @Date 2019年6月17日 下午4:22:21
 */
@Controller
@Slf4j
@RequestMapping("/test")
public class TestCollectionController extends BaseController {


    /**
     * 测试
     *
     * @param json
     */
    @RequestMapping(value = "test1",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public void test(String json) {
        try {
            System.out.println(json);
        } catch (Exception e) {
            log.info("信息收集出错，原始信息:{}", json);
            log.info("信息收集出错，堆栈信息", e);
        }
    }

}
