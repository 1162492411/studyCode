package collection;


import pojo.Person;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Mo on 2017/4/25.
 */
public class LoopEfficiencyTest {


    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        for (int i = 0; i < 1000_000; i++) {
            persons.add(new Person("男子" + i, "男"));
        }

        //fori
        long startTime = System.currentTimeMillis();
        int size = persons.size();
        for (int i = size - 1; i > 0; i--)
            System.out.println(persons.get(i));
        long endTime = System.currentTimeMillis();
        long timeA = endTime - startTime;

        //foreach
        long startTime2 = System.currentTimeMillis();
        persons.forEach(System.out::println);
        long endTime2 = System.currentTimeMillis();
        long timeB = endTime2 - startTime2;


        long startTime3 = System.currentTimeMillis();
        Iterator<Person> iterator = persons.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
        long endTime3 = System.currentTimeMillis();
        long timeC = endTime3 - startTime3;

        System.out.println("fori遍历耗时" + timeA);
        System.out.println("foreach遍历耗时" + timeB);
        System.out.println("iterator遍历耗时" + timeC);
    }


}
