package string;

public class ValueDemo {

    public static void main(String[] args) {
        //创建一些将要用到的内容
        boolean flag = true;
        char aChar = 'a';
        char[] aChars = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        double aDouble = 1234.2;
        float aFloat = 123456;
        int aInt = 4;
        long aLong = 44;
        Object aObejct = new Object();

        //valueOf方法，将布尔值转换为字符串
        System.out.println(String.valueOf(flag));

        //valueOf方法，将char字符转换为字符串
        System.out.println(String.valueOf(aChar));

        //valueOf方法，将字符数组转换为字符串
        System.out.println(String.valueOf(aChars));

        //valueOf方法，将一个字符数组中的指定位置内容转化成字符串
        System.out.println(String.valueOf(aChars, 3, 2));

        //valueOf方法，将一个double值转化成字符串
        System.out.println(String.valueOf(aDouble));

        //valueOf方法，将一个float值转化成字符串
        System.out.println(String.valueOf(aFloat));

        //valueOf方法，将一个整数值转化成字符串
        System.out.println(String.valueOf(aInt));

        //valueOf方法，将一个long值转化成字符串
        System.out.println(String.valueOf(aLong));

        //valueOf方法，将一个对象转化成字符串
        System.out.println(String.valueOf(aObejct));

        Object bObject = null;
        System.out.println(String.valueOf(bObject));

    }
    /**
     * String的静态方法valueOf可将基本数据类型转化为字符串
     * 如果一个对象变量指向null，则将其转化为字符串"null"
     */

}
