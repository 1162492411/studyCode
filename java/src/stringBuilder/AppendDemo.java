package stringBuilder;

/**
 * 关于append的示例
 *
 * @author Mo
 */
public class AppendDemo {

    public static void main(String[] args) {
        StringBuilder aBuilder = new StringBuilder("abc");
        // 传入一个boolean值
        System.out.println(aBuilder.append(true));

        // 传入一个char
        System.out.println(aBuilder.append('a'));

        // 传入一个int
        System.out.println(aBuilder.append(3));

        // 传入一个long
        System.out.println(aBuilder.append(987654321));

        // 传入一个float
        System.out.println(aBuilder.append(2.34f));

        // 传入一个double
        System.out.println(aBuilder.append(12.3));


        // 传入一个字符串
        System.out.println(aBuilder.append("qwer"));

        // 传入一个StringBuffer对象
        System.out.println(aBuilder.append(new StringBuffer("poi")));

        // 传入一个char[]数组
        char[] chars = new char[]{'z', 'x', 'c'};
        System.out.println(aBuilder.append(chars));

        // 传入一个Object
        System.out.println(aBuilder.append(new Object()));

    }
    /**
     * 总结： 
     * append共有十三个重载方法，接受不同类型的值传入，
     * 这些类型包括：
     * Object, String, StringBuffer, CharSequence, char[] 
     * 同时也支持基本数据类型的值传入： 
     * boolean, char, int, long, float,double
     */
}
