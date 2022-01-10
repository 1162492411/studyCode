package collection;

import pojo.Person;

import java.util.TreeSet;

/**
 * TreeSet的常用API
 *
 * @author Mo
 */
public class TreeSetDemo {

    public static void main(String[] args) {
        TreeSet<Person> aSet = new TreeSet<Person>();

        // 添加元素
        aSet.add(new Person("张三", "男"));
        aSet.add(new Person("李四", "女"));
        aSet.add(new Person("王五", "女"));

        // 遍历查看
        for (Person person : aSet) {
            System.out.println(person.getName() + " " + person.getSex());
        }

        TreeSet<String> bSet = new TreeSet<String>();

        bSet.add("abc");
        bSet.add("xyz");
        bSet.add("bcd");

        for (String string : bSet) {
            System.out.println(string);
        }
    }
    /**
     * 总结： TreeSet是依靠TreeMap来实现的。
     * 1.TreeSet是一个有序集合，TreeSet中的元素将按照升序排列，缺省是按照自然排序进行排列，
     * 意味着TreeSet中的元素要实现Comparable接口。或者有一个自定义的比较器
     * 2.由于set不保存重复元素，因此在将自定义对象放入TreeSet时，需要重写equals和hashCode方法
     */
}
