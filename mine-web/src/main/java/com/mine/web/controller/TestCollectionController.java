package com.mine.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.mine.common.entity.User;
import com.mine.domain.service.TestService;
import com.mine.web.aops.LoggerManage;
import com.mine.web.common.BaseController;
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

    @Autowired
    private TestService testService;

    /**
     * 测试
     *
     * @param json1 json
     */
    @RequestMapping(value = "test1", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    @LoggerManage(description = "测试方法")
    public String test(String json1, String json2, Integer num, @DateTimeFormat(pattern = "yyyy-mm-dd") Date date, User user) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(json1), "参数错误");
        testService.test1();
        System.out.println(json1);
        return "你好啊";

    }
}
