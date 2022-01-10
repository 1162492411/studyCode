package collection;

import pojo.Person;

import java.util.*;

/**
 * hashMap的常用API
 *
 * @author Mo
 */
public class HashMapDemo {

    public static void main(String[] args) {
        //创建一个hashMap对象
        HashMap<String, Person> aMap = new HashMap<String, Person>();

        //put-->存入键值对
        for (int i = 0; i < 10; i++) {
            //i的字符串形式作为键，自定义的person类的对象作为值，存入hashMap对象
            aMap.put(i + "", new Person("Person " + i, "男"));
        }
        //可以放入重复的值，但不能放入重复的键
        aMap.put("3", new Person("Person 3", "男"));

        //get-->获取键值对
        System.out.println(aMap.get("3").getName());


        //遍历HashMap的键--获取内置的keySet
        System.out.println("遍历键");
        Set<String> set2 = aMap.keySet();
        for (String key : set2) {
            System.out.println("遍历的键是" + key);
        }
        System.out.println("-------------------");

        //遍历HasoMap的值
        System.out.println("遍历值");
        Collection<Person> collection = aMap.values();
        System.out.println(collection.size());
        for (Person person : collection) {
            System.out.println("遍历的值是" + person);
        }

        //遍历HashMap的键值对--获取内置的entrySet
        System.out.println("遍历键值对");
        Set set = aMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println("键是" + entry.getKey());
            System.out.println("值是" + entry.getValue());
        }
        System.out.println("----------------------");


    }
    /**
     * 总结： 1.HashMap储存数据的方式是—— 键值对 2.基于哈希表实现，可以通过调整初始容量和加载因子进行性能调优。
     * 3.默认初始容量=16，加载因子=0.75，加载因子会影响rehash操作。最大容量必须是2的幂且小于2的30次方，传入容量过大将被这个值替换。
     * 4.HashMap使用拉链法进行数据存储，其维护了一个数据存储数组table，table中存储了一个链表
     */
}
