package com.mine.util.securityutil.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/11/1 16:16
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@RestController
@RequestMapping(value = "/security")
public class TestController {

    @RequestMapping(value = "test1")
    public String test1(@RequestParam(value = "str") String str) {
        return "你好啊";
    }
}
