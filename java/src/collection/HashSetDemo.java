package collection;

import java.util.HashSet;

/**
 * HashSet的常用API
 *
 * @author Mo
 */
public class HashSetDemo {

    public static void main(String[] args) {
        //创建一个hashSet对象
        HashSet<String> aSet = new HashSet<String>();

        //添加一个元素
        System.out.println(aSet.add("1"));
        //再次添加一个元素
        System.out.println(aSet.add("2"));
        //添加一个重复的元素--添加失败，因为set不存储重复元素
        System.out.println(aSet.add("1"));

        //尝试添加元素
        for (int i = 5; i < 20; i++) {
            aSet.add(i + "");
        }
        //打印后发现元素并不是完全按照插入顺序排列--采用foreach打印		
        for (String string : aSet) {
            System.out.println(string);
        }

    }
/**
 * 总结：
 * 1.HashSet基于 HashMap 实现，HashSet 底层采用 HashMap 来保存所有元素
 * 2.在HashSet中存入自定义的对象时，需要重写equals和hashCode方法，因为set并不允许存储相同的元素
 */
}
