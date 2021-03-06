package com.mine.util.reportUtil.huaerExcelUtil;

/**
 * 对于一般的Java基本数据(包括Date) 都进行了一般处理
 * <p>
 * 但是仍然有一些复杂类型;需要把它转换到Excel,提供了一个抽象类,实现doHandle方法自定义处理即可;
 * <p>
 * 当自定义的Handle放在基本属性上时,优先使用自定义的Handle进行数据处理
 * <p>
 * Created by jincarry on 2017/10/26.
 */
public abstract class XlsGenAbstractHandle<T> {

    public XlsGenAbstractHandle() {

    }

    public abstract String doHandle(T data);
}
