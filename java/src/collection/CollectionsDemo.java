package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsDemo {

    public static void main(String[] args) {
        //初始化集合numbers
        List numbers = new ArrayList();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        System.out.println(numbers);
        //旋转，以2为支点，进行旋转
        Collections.rotate(numbers, 2);

        System.out.println(numbers);
        //反转
        Collections.reverse(numbers);

        System.out.println(numbers);
        //排序
        Collections.sort(numbers);
        System.out.println(numbers);

        //把3和4的元素进行交换
        Collections.swap(numbers, 3, 4);
        System.out.println(numbers);

        //打乱顺序
        Collections.shuffle(numbers);
        System.out.println(numbers);

        //得到一个线程安全的List（默认的List不是线程安全的）
        List<Integer> syncNumbers = Collections.synchronizedList(numbers);
    }
}
/**
 * 总结：
 * 1.Collections是集合的工具类，存在大量静态方法，能够操作集合中的数据
 * 2.Collections常用操作包括排序(sort，依赖于对象实现的comparator，或者传入一个Compator)、反转、线程安全化。
 **/
