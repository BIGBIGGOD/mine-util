package com.mine.util.jdk8Util;

import com.mine.model.User;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by jiangqd on 2019/3/22.
 */
public class BuiltInFunctionalUtil {

    public static void main(String[] args) {
        method5();
    }

    /**
     * Predicate是一个返回布尔类型的函数
     * 用Predicate定义一个判断式，之后用这个判断调用对应的方法返回布尔值，其它方法都是围绕test作逻辑处理
     */
    public static void method1() {
        Predicate<User> predicate = user -> user.getAge() > 10;
        User user = new User(12);
        if (predicate.test(user)) {
            System.out.println("age>12");
        }
    }

    /**
     * 使用Functions接受一个参数，并产生一个结果。默认方法可以将多个函数串在一起,下面是一个基本的类型转换例子
     */
    public static void method2() {
        Function<String, Integer> toInteger = Integer::valueOf;
        int i = toInteger.apply("57");
        System.out.println(i);
    }

    /**
     * 使用Supplier(生产者)能够产生一个给定的泛型类型的对象，使用get方法能够获得。与Functional不同的是Suppliers不接受输入参数
     */
    public static void method3() {
        Supplier<User> supplier = User::new;
        User user = supplier.get();
    }

    /**
     * 代表在一个单一的输入参数上执行操作
     */
    public static void method4() {
        Consumer<User> consumer = user -> System.out.println("你好啊");
        consumer.accept(new User());
    }

    /**
     * Comparators(比较器)
     */
    public static void method5() {
        Comparator<User> comparator = (u1, u2) -> u1.getAge() - u2.getAge();
        User u1 = new User(222);
        User u2 = new User(52);
        User u3 = new User(66);
        Set<User> set = new TreeSet<>(comparator);
        set.add(u1);
        set.add(u2);
        set.add(u3);
        System.out.println(set);
    }

    /**
     * Optionals是一个简单的容器，存放的值可能是空的或者非空的，主要用来防止空指针异常
     */
    public static void method6() {
        Optional<String> optional = Optional.of("ddd");
        Optional<String> optiona2 = Optional.empty();
        optional.get();
        optional.isPresent();
        optional.orElse("kj");
        //判空
        Optional.ofNullable("nodes").orElseThrow(() -> new IllegalArgumentException("无计算节点，无法进行计算"));
        Optional.ofNullable(new User()).orElse(new User());
    }

}
