package collection;

import pojo.Person;

import java.util.LinkedList;


/**
 * LinkedList的常用API
 *
 * @author Mo
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        //LinkedList是一个双向链表结构的list
        LinkedList<Person> aHeros = new LinkedList<Person>();
        //add-->在链表尾部添加对象
        aHeros.add(new Person("张三", "男"));
        aHeros.add(new Person("李四", "男"));
        aHeros.add(new Person("王五", "女"));
        for (Person person : aHeros) {
            System.out.println(person.getName() + " " + person.getSex());
        }

        //addFirst-->在头部添加对象
        aHeros.addFirst(new Person("张二", "女"));
        //addLast-->在尾部添加对象，等同于add
        aHeros.addLast(new Person("赵六", "女"));
        for (Person person : aHeros) {
            System.out.println(person.getName() + " " + person.getSex());
        }

        //getFirst-->查看第一个对象
        System.out.println("查看第一个对象:" + aHeros.getFirst().getName());
        //getLast-->查看最后一个对象
        System.out.println("查看最后一个对象:" + aHeros.getLast().getName());

        //removeFirst-->移除第一个对象
        aHeros.removeFirst();
        //removeLast-->移除最后一个对象
        aHeros.removeLast();
        for (Person person : aHeros) {
            System.out.println(person.getName() + " " + person.getSex());
        }


    }

}
