package com.mine.utils.jdk8Util;

import com.mine.common.entity.User;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Lambda表达式使用(..)->{...}
 * 说明：
 * (参数1，参数2…)表示参数列表；->表示连接符；{}内部是方法体
 * 1、=右边的类型会根据左边的函数式接口类型自动推断；
 * 2、如果形参列表为空，只需保留()；
 * 3、如果形参只有1个，()可以省略，只需要参数的名称即可；
 * 4、如果执行语句只有1句，且无返回值，{}可以省略，若有返回值，则若想省去{}，则必须同时省略return，且执行语句也保证只有1句；
 * 5、形参列表的数据类型会自动推断；
 * 6、lambda不会生成一个单独的内部类文件；
 * 7、lambda表达式若访问了局部变量，则局部变量必须是final的，若是局部变量没有加final关键字，系统会自动添加，此后在修改该局部变量，会报错；
 * Created by jiangqd on 2019/3/21.
 */
public class LambdaUtil {

    public static void main(String[] args) {
        test1();
    }

    /**
     * 使用lambda表达式简化使用内部类
     */
    public static void test1() {
        //1：不使用lambda表达式方式
        TreeSet<User> set1 = new TreeSet(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        set1.add(new User(46546));
        set1.add(new User(55));
        set1.add(new User(4524));
        set1.add(new User(45));
        System.out.println(set1);
        //2：使用lambda表达式方式
        //注意花括号、分号的省略
        Comparator<User> comparator = (x, y) -> Integer.compare(x.getAge(), y.getAge());
        TreeSet<User> set2 = new TreeSet<>(comparator);
        set2.add(new User(46546));
        set2.add(new User(55));
        set2.add(new User(4524));
        set2.add(new User(45));
        System.out.println(set2);
        set2.stream().filter((set) -> set.getAge() > 65).forEach(System.out::println);
        System.out.println(set2);
    }

}
