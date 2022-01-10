package string;

/**
 * 关于搜索字符串的示例
 *
 * @author Mo
 */
public class SearchDemo {

    public static void main(String[] args) {
        //定义即将用到的字符串
        String aString = new String("fedcabcab");

        //charAt方法，返回指定位置的字符
        System.out.println("位于第2个位置的字符是：" + aString.charAt(2));

        //codePointAt方法，返回指定位置的字符的arscii值
        System.out.println("位于第2个位置的字符的代码点是：" + aString.codePointAt(2));

        //codePointBefore方法，返回指定位置的前一个位置的字符的arscii值
        //注：String类并没有提供返回指定位置的前一个位置的字符的方法
        System.out.println("位于第2个位置的前一个字符的代码点是:" + aString.codePointBefore(2));

        //codePointCount方法，返回从beginIndex到endIndex之间的字符数量
        System.out.println("指定位置之间的字符数量为：" + aString.codePointCount(2, 5));

        //offsetByCodePoints方法，返回从指定索引处偏离指定距离后的下标
        System.out.println("从指定位置偏移指定距离后的下标为:" + aString.offsetByCodePoints(2, 3));

        //lastIndexOf方法，返回指定字符在字符串中最后一次出现的位置
        System.out.println("a在字符串中最后一次出现的位置为：" + aString.lastIndexOf('a'));

        //lastIndexOf方法，返回指定字符到指定位置为止，在字符串中最后一次出现的位置
        System.out.println("a到字符串第5个位置为止，最后一次出现的位置为:" + aString.lastIndexOf('a', 5));

        //lastIndexOf方法，返回指定字符串在字符串中最后一次出现的位置
        System.out.println("ca在字符串中最后一次出现的位置为：" + aString.lastIndexOf("ca"));

        //lastIndexOf方法，返回指定字符串到指定位置为止，在字符串中最后一次出现的位置
        System.out.println("ca从到字符串第7个位置为止，开始在字符串中最后一次出现的位置为：" + aString.lastIndexOf("ca", 7));

        //indexOf方法，返回指定字符在字符串中第一次出现的位置
        System.out.println("a在字符串中第一次出现的位置为：" + aString.indexOf('a'));

        //indexOf方法，返回指定字符到指定位置为止，在字符串中第一次出现的位置
        System.out.println("a从字符串第5个位置开始，第一次出现的位置为:" + aString.indexOf('a', 5));

        //indexOf方法，返回指定字符串在字符串中第一次出现的位置
        System.out.println("ca在字符串中第一次出现的位置为：" + aString.indexOf("ca"));

        //indexOf方法，返回指定字符串到指定位置为止，在字符串中第一次出现的位置
        System.out.println("ca从到字符串第7个位置开始，开始在字符串中第一次出现的位置为：" + aString.indexOf("ca", 7));

        //startsWith方法，返回字符串是否以指定前缀开始
        System.out.println("字符串是否以指定前缀fe开始:" + aString.startsWith("fe"));

        //startsWith方法，返回字符串是否以指定前缀开始
        System.out.println("字符串从指定位置开始，是否以指定前缀fe开始:" + aString.startsWith("fe", 2));

        //endsWith方法，返回字符串是否以指定后缀结束
        System.out.println("字符串是否以指定后缀cab结束:" + aString.endsWith("cab"));
    }
    /**
     * 总结：
     * String类可以查找指定位置的字符及其arscii值，
     * 可以计算从指定位置偏移某距离之后的位置
     * 可以对指定位置之间的字符进行计数，
     * 可在指定位置之间查找某字符或字符串第一次/最后一次出现的位置
     * 可测试字符串是否以指定前缀开始/结束
     */

}
