package com.mine.util.callBackUtil;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/12/31 15:03
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class TestDefineCallBack {
    public static void say(DefineCallBack defineCallBack) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("进入say");
        defineCallBack.handle("接口实现");
    }

    public static void main(String[] args) throws InterruptedException {
        say((msg) -> {
            System.out.println("进入回调方法");
            System.out.println(msg + "xx");
        });
    }
}
