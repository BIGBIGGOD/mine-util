package com.mine.util.exampleutil.banktrans;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2019/4/9 0009.
 */
public interface BankTransUtil {

    /**
     * 银行内部转账，从转出帐号中扣除转账金额，给转入帐号增加转账金额，需要保证以上两个操作
     * 要么同时成功，要么同时失败
     * fromAccountId 转出帐号
     * outAccountId 转入帐号
     * amount 转账金额
     */
    public void transferInner(String fromAccountId,String outAccountId,BigDecimal amount);


    /**
     * 外部转账-转出，从转出帐号中扣除转账金额
     * fromAccountId 转出帐号
     * amount 转账金额
     */
    public void transferOut(String fromAccountId,BigDecimal amount);


    /**
     * 外部转账-转入，从转入帐号中增加转账金额
     * outAccountId 转入帐号
     * amount 转账金额
     */
    public void transferIn(String outAccountId,BigDecimal amount);
}
