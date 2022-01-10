package string;

/**
 * 关于创建字符串的示例
 *
 * @author Mo
 */
public class InitDemo {

    public static void main(String[] args) {
        //设置一些可能会被用到的元素
        char[] aValue = {'1', '2', '3', '4'};
        byte[] bValue = {'a', 'b', 'c', 'd', 'e'};
        StringBuffer cBuffer = new StringBuffer("asdf");

        //构造一个空字符串对象
        String aString = new String();
        //构造一个指定字符串的副本
        String bString = new String("abc");
        //通过char数组构造字符串，可根据指定位置构造字符串
        String cString = new String(aValue);
        //通过byte数组构建一个字符串,可根据指定位置构造字符串，可根据指定编码格式构造字符串，指定编码时需进行异常处理
        String dString = new String(bValue);
        //通过动态字符串构建字符串
        String eString = new String(cBuffer);
        //打印构造的字符串
        System.out.println("aString:" + aString);
        System.out.println("bString:" + bString);
        System.out.println("cString:" + cString);
        System.out.println("dString:" + dString);
        System.out.println("eString:" + eString);
    }
/**
 * 总结：
 * 1.String类可以将char数组、byte数组、ascii数组、动态字符串封装为字符串;
 */
}
