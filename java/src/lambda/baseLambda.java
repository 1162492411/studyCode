package lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Mo on 2017/5/2.
 */
public class baseLambda {

    private static List<String> names = Arrays.asList("abc", "abwq", "aab", "ab", "aab", "ba", "ba", "bbaa", "bas");

    //展示如何在Comparator中使用Lambda
    private static void comparatorDemo() {
        System.out.println("执行comparatorDemo");

        Collections.sort(names, (a, b) -> a.compareTo(b));//简化了comparator,JVM会自动推断->之后的代码的返回类型

        System.out.println(names);
    }

    //展示如何在foreach中使用Lambda
    private static void forEachDemo() {
        System.out.println("执行forEachDemo");

        names.forEach(o -> {
            System.out.print(o);
            System.out.println("--");
        });//遍历集合框架中的每个元素

        names.forEach(o -> System.out.println(o + "--"));//更加简化的遍历，仅有一行代码时可以省略{}

        names.forEach(System.out::println);//简化形参，使用方法引用

    }

    //展示如何在Runnable中使用Lambda
    private static void runnableDemo() {
        System.out.println("执行runnableDemo");
        new Thread(() -> System.out.println("xxccvvbb")).start();
    }

    //展示如何在Predicate中使用Lambda
    private static void predicateDemo() {
        System.out.println("执行predicateDemo");
        Predicate<String> predicate = s -> s.length() < 3;//简化Predicate的test()的创建
        names.forEach(o -> {
            if (predicate.test(o)) System.out.println(o);
        });
    }

    //展示如何在Stream的filter()中使用Lambda
    private static void streamFilterDemo() {
        System.out.println("执行streamFilterDemo");
        //过滤掉names中所有字符串长度小于2的字符串
        System.out.println(names.stream().filter(a -> a.length() > 2).collect(Collectors.toList()));
    }

    //展示如何在Stream的map()中使用Lambda
    private static void streamMapDemo() {
        System.out.println("执行streamMapDemo");
        //将names中所有字符串转化为大写
        System.out.println(names.stream().map(String::toUpperCase).collect(Collectors.toList()));
    }

    public static void main(String[] args) {
//        comparatorDemo();
//        forEachDemo();
//        runnableDemo();
//        predicateDemo();
        streamFilterDemo();
//        streamMapDemo();
    }
}
