package com.mine.util.javaToolUtil;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description guava工具类中的参数判断
 * @DATE 2019/10/30 14:47
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
public class GuavaParamUtil {

    public static void judgeParams() {
        String str1 = "xx";

        //判断String类型是否为空
        boolean res1 = Strings.isNullOrEmpty(str1);

        //判断String类型是否为空，为空返回空字符串
        String res2 = Strings.emptyToNull(str1);

        //判断String类型是否为空，为空字符串返回null
        String res3 = Strings.emptyToNull(str1);

        //对象为空时抛出空指针异常
        Preconditions.checkNotNull(str1, "参数错误", "xxxx");
        //表达式为false时抛出参数异常
        Preconditions.checkArgument(str1 == "s" && str1.equals("xx"), "参数错误");
        //当index小于0或者大于等于size的时候抛出下标越界异常
        Preconditions.checkElementIndex(1, 100, "索引超标");
        //当index小于0或者大于size的时候抛出下标越界异常
        Preconditions.checkPositionIndex(1, 100, "索引超标");
        //当start小于0或者start大于end或者end大于size的时候抛出下标越界异常
        Preconditions.checkPositionIndexes(0, 100, 50);
        //当状态表达式为false时抛出状态异常
        Preconditions.checkState(200 == 200);
    }

    public static void main(String[] args) {
        try {
//            String str = "xx";
//            Preconditions.checkArgument(str.equals("aa"), "参数错误");
//            int a = 0;
//            int b = 1/a;
        } catch (Exception e) {
            log.error("异常", e);
            System.out.println(e.getMessage());
        }
    }
}
