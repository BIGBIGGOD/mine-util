package com.mine.utils.algorithmutil.exercise;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/3/10 16:18
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class Test2 {
    public static void main(String[] args) {
        String str = "abcabcdabcde";
        lengthOfLongestSubstring(str);
    }

    public void test1(String str) {
        char[] arr = str.toCharArray();
        Set<Character> set = Sets.newHashSet();
        int start = 0;
        int end = 0;
        while (true) {

        }
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public void test2(String str) {
        Set<Character> set = Sets.newHashSet();
        int num = 0;
        int max = 0;
        String maxStr = "";
        StringBuilder ss = new StringBuilder();
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                char c = arr[j];
                if (set.add(c)) {
                    num++;
                    ss.append(c);

                } else {
                    if (num > max) {
                        max = num;
                        maxStr = ss.toString();
                    }
                    num = 0;
                    ss = new StringBuilder();
                    set = Sets.newHashSet();
                    break;
                }
            }
        }
        System.out.println("maxNum=" + max + "maxStr=" + maxStr);
    }
}
