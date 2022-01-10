package stringBuilder;

/**
 * 关于insert的示例
 *
 * @author Mo
 */
public class InsertDemo {

    public static void main(String[] args) {
        StringBuilder aBuilder = new StringBuilder("ab");

        // 插入一个boolean值
        System.out.println(aBuilder.insert(1, true));

        // 插入一个char
        System.out.println(aBuilder.insert(1, 'a'));

        // 插入一个int
        System.out.println(aBuilder.insert(1, 3));

        // 插入一个long
        System.out.println(aBuilder.insert(1, 987654321));

        // 插入一个float
        System.out.println(aBuilder.insert(1, 2.34f));

        // 插入一个double
        System.out.println(aBuilder.insert(1, 12.3));

        // 插入一个字符串
        System.out.println(aBuilder.insert(1, "qwer"));

        // 插入一个StringBuffer对象
        System.out.println(aBuilder.insert(1, new StringBuffer("poi")));

        // 插入一个char[]数组
        char[] chars = new char[]{'z', 'x', 'c'};
        System.out.println(aBuilder.insert(1, chars));

        // 插入一个Object
        System.out.println(aBuilder.insert(1, new Object()));

    }
    /**
     * 总结： insert有12个重载方法，它能够接收insert能接收的类型的值传入
     *
     */
}
