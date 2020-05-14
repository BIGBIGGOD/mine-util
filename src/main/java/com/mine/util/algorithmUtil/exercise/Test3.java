package com.mine.util.algorithmUtil.exercise;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/3/11 10:23
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class Test3 {

    public static void main(String[] args) {

    }

    public int xxx(int[] date, int[] use) {
        int[] arr = new int[366];
        int min = date[0];
        int max = date[date.length -1];
        for (int i = 0; i < date.length; i++) {

        }
        return 0;
    }

    public int mincostTickets(int[] days, int[] costs) {
        int len = days.length, maxDay = days[len - 1], minDay = days[0];
        int[] dp = new int[maxDay + 31]; // 多扩几天，省得判断 365 的限制
        // 只需看 maxDay -> minDay，此区间外都不需要出门，不会增加费用
        for (int d = maxDay, i = len - 1; d >= minDay; d--) {
            // i 表示 days 的索引
            // 也可提前将所有 days 放入 Set，再通过 set.contains() 判断
            if (d == days[i]) {
                dp[d] = Math.min(dp[d + 1] + costs[0], dp[d + 7] + costs[1]);
                dp[d] = Math.min(dp[d], dp[d + 30] + costs[2]);
                i--; // 别忘了递减一天
            } else dp[d] = dp[d + 1]; // 不需要出门
        }
        return dp[minDay]; // 从后向前遍历，返回最前的 minDay

    }
}

