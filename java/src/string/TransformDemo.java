package string;

import java.io.UnsupportedEncodingException;

/**
 * 关于转化字符串的示例
 *
 * @author Mo
 */
public class TransformDemo {

    public static void main(String[] args) {
        //定义将要用到的字符串
        String aString = new String("aBcD");
        String bString = new String("abcd");

        //toLowerCase方法，将字符串全部转换为小写，返回的是新字符串
        System.out.println("全部转换为小写字母：" + aString.toLowerCase());

        //toUpperCase方法，将字符串全部转换为大写，返回的是新字符串
        System.out.println("全部转换为大写字母：" + aString.toUpperCase());

        //toString方法,返回一个字符串
        System.out.println(aString.toString());

        //toCharArray,将字符串转换为char数组
        char[] aChar = aString.toCharArray();
        for (char c : aChar) {
            System.out.println(c);
        }

        //getChars方法，将源字符串指定部分内容复制到目标char数组中的指定位置
        char[] bChar = {'q', 'w', 'e', 'r'};
        aString.getChars(1, 3, bChar, 2);
        System.out.println(bChar);

        //getBytes方法，将字符串转换为byte数组
        byte[] aByte = null;
        aByte = aString.getBytes();
        try {
            aByte = aString.getBytes("GBK");//可指定目标byte数组的编码格式
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
/**
 * 总结：
 * String可以将自身全部转化为大写/小写，
 * 可将自身转化为byte/char数组
 */
}
