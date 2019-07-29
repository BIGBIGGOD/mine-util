package com.mine.util.participleUtil;

import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.*;

import java.util.regex.Pattern;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description ansj中文分词
 * @DATE 2019/7/16 15:41
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class AnsjUtil {

    private static final Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");

    public static void main(String[] args) {
        // 中文分词操作
        //精准分词
        String str  = "大龙虾";
        Result result1 = ToAnalysis.parse(str);
        //用户自定义词典优先策略的分词
        Result result2 = DicAnalysis.parse(str);
        //带有新词发现功能的分词
        Result result3 = NlpAnalysis.parse(str);
        //最小颗粒度的分词
        Result result4 = BaseAnalysis.parse(str);
        //面向索引的分词
        Result result5 = IndexAnalysis.parse(str);
//        for (Term term: result5.getTerms()) {
//            Matcher m = pattern.matcher(term.getName());
//            if (m.find()) {
//                // 存在中英文数字，针对中英文前 1 / 3 的字符制作索引
//                String tempNameStr = m.group();
//
//                if (tempNameStr.length() == 1) {
//                    pushKey(tempNameStr, null);
//                } else if (tempNameStr.length() > 1) {
//                    double calculated = ((double) tempNameStr.length() - 1) / (double) 3;
//
//                    // 向上上取整
//                    int endIndex = (int) Math.ceil(calculated);
//                    while (endIndex >= 0) {
//                        pushKey(tempNameStr.substring(0, endIndex --), null);
//                    }
//                }
//
//            } else {
//                // 不存在中英文数字，直接推入
//                pushKey(term.getName(), null);
//            }
//        }
    }

    private static void pushKey(String key, String str) {
        System.out.println(key);
    }
}
