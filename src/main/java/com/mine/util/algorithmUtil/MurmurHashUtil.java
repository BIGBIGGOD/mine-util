package com.mine.util.algorithmUtil;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.function.Consumer;

import com.mine.entity.User;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description MurmurHash算法
 * @DATE 2020/3/30 19:28
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class MurmurHashUtil {

    public static void main(String[] args) throws UnsupportedEncodingException {
        Consumer<User> consumer = user -> System.out.println(user.toString());
        consumer.accept(new User());
//        assert ("5016608279269930526".equals(MurmurHashUtil.hashUnsigned("chenshuo").toString()));
//        assert ("17121371936686143062".equals(MurmurHashUtil.hashUnsigned("shaoguoqing").toString()));
//        assert ("5451996895512824982".equals(MurmurHashUtil.hashUnsigned("baozenghui").toString()));
//        assert ("10652549470333968609".equals(MurmurHashUtil.hashUnsigned("05ff62ff6f7749ffff43ffff6673ff65").toString()));
//        assert ("15134676900169534748".equals(MurmurHashUtil.hashUnsigned("hahahaha").toString()));
//        assert ("6439159232526071817".equals(MurmurHashUtil.hashUnsigned("hahah1369531321aha5466sfdfaerttedddd56da").toString()));
//        assert ("1146745369200541601".equals(MurmurHashUtil.hashUnsigned("测试汉字").toString()));
//        assert ("10129762727109086067".equals(MurmurHashUtil.hashUnsigned("1234566大大21".getBytes("GBK")).toString()));
//        assert ("5141842319936259217".equals(MurmurHashUtil.hashUnsigned("12345665哦4哦3我的妈呀21".getBytes("GBK")).toString()));
    }
    /**
     * murmurhash算法实现
     */
    public static Long hash(byte[] key) {

        //创建缓冲区
        ByteBuffer buf = ByteBuffer.wrap(key);
        int seed = 0x1234ABCD;

        /**
         * ---ByteOrder定义了写入buffer时字节的顺序，java默认是BIG_ENDIAN，2个内置的ByteOrder
         * ByteOrder.BIG_ENDIAN和ByteOrder.LITTLE_ENDIAN
         *
         * ---ByteOrder.nativeOrder()
         * 返回本地jvm运行的硬件的字节顺序.使用和硬件一致的字节顺序可能使buffer更加有效.
         *
         * ---ByteOrder.toString()
         * 返回ByteOrder的名字,BIG_ENDIAN或LITTLE_ENDIAN
         */
        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first:
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return h;
    }

    public static Long hash(String key) {
        return hash(key.getBytes());
    }


    /**
     * Long转换成无符号长整型（C中数据类型）
     */
    public static BigDecimal readUnsignedLong(long value) {
        if (value >= 0) {
            return new BigDecimal(value);
        }
        //0x7fffffffffffffffL转成二进制全为1，也就是long类型（64位）的最大值，实际用于给value取反，当value转成二进制对应位置都为1时不变，否则变成0
        long lowValue = value & 0x7fffffffffffffffL;
        //使用BigDecimal类型将value在之前的基础上加上long类型的最大值再加一
        return BigDecimal.valueOf(lowValue).add(BigDecimal.valueOf(Long.MAX_VALUE)).add(BigDecimal.valueOf(1));
    }

    /**
     * 返回无符号murmur hash值
     */
    public static BigDecimal hashUnsigned(String key) {
        return readUnsignedLong(hash(key));
    }

    public static BigDecimal hashUnsigned(byte[] key) {
        return readUnsignedLong(hash(key));
    }
}
