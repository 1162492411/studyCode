package collection;


import pojo.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * ArrayList的常用API
 *
 * @author Mo
 */
public class ArrayListDemo {


    public static void main(String[] args) {


        //定义一个存储Person对象的ArrayList集合
        ArrayList<Person> aList = new ArrayList<Person>();

        //add-->添加对象
        for (int i = 0; i < 5; i++) {
//			Scanner scanner = new Scanner(System.in);
//			System.out.println("请输入对象的名称");
//			String name = scanner.nextLine();
//			System.out.println("请输入对象的性别");
//			String sex = scanner.nextLine();
//			aList.add(new Person(name,sex));
            aList.add(new Person("男子" + i, "男"));
        }

        //add-->指定位置添加对象
        aList.add(5, new Person("男子6", "男"));

        //contains-->是否包含某对象
        System.out.println("第50个对象是否存在:" + aList.contains(new Person("男子50", "男")));

        //get-->取出指定位置的对象，下标从0开始
        System.out.println("第2个对象是:" + aList.get(1).getName());

        //indexOf-->取出对象所处的位置
        Person aPerson = new Person("男子4", "男");
        System.out.println("第4个对象所处位置为:" + aList.indexOf(aPerson));

        //remove-->删除某个对象
        aList.remove(new Person("男子3", "男"));

        //set-->设置指定位置的对象
        aList.set(2, new Person("替换男子", "男"));

        //size-->获取对象数量
        System.out.println("容器中的对象数量为:" + aList.size());

        //遍历--fori方式
        System.out.println("下面是fori遍历");
        for (int i = 0; i < aList.size(); i++) {
            System.out.println(aList.get(i));
        }

        //遍历--foreach
        System.out.println("下面是foreach遍历");
        for (Person person : aList) {
            System.out.println(person);
        }

        //遍历--Iterator
        System.out.println("下面是Iterator遍历");
        Iterator iterator = aList.iterator();
        while (iterator.hasNext()) {
            System.out.println((Person) iterator.next());
        }

        //clear-->清除所有元素
        aList.clear();


        Person[] pserons = {new Person("四四", "女"), new Person("五五", "男"), new Person("六六", "男")};
        Object[] per = new ArrayList<Object>().toArray(pserons);//toArray()方法将数组转换为含有参数类型的Object数组，并不进行类型擦除


        System.out.println(Arrays.asList(pserons));
        List<?> pp = new ArrayList<Object>(2);
        Object[] result = pp.toArray(pserons);
        for (int i = 0; i < result.length; i++) {
            System.out.println("第" + i + "个对象是" + result[i]);
        }

    }
/**
 * 总结：
 * 1.ArrayList是List接口的子类，基本操作为增删改查等	
 * 2.集合中存放的依然是对象的引用而不是对象本身。 
 * 3.ArrayList底层采用数组实现，当使用不带参数的构造方法生成ArrayList对象时，
 * 实际上会在底层生成一个长度为10的Object类型数组 
 * 4.如果增加的元素个数超过了10个，那么ArrayList底层会新生成一个数组，长度为原数组的1.5倍+1，
 * 然后将原数组的内容复制到新数组当中，并且后续增加的内容都会放到新数组当中。当新数组无法容纳增加的元素时，重复该过程。因为扩充属于耗时操作，因此如果在预先大致确定对象数量的情况下，推荐直接指定ArrayList容量来进行初始化
 * 5. 对于ArrayList元素的删除操作，需要将被删除元素的后续元素向前移动，代价比较高。 
 * 6. 集合当中只能放置对象的引用，无法放置原生数据类型，我们需要使用原生数据类型的包装类才能加入到集合当中。
 */

}
