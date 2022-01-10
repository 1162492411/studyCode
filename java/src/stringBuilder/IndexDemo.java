package stringBuilder;

/**
 * IndexOf的示例
 *
 * @author Mo
 */
public class IndexDemo {

    public static void main(String[] args) {
        StringBuilder aBuilder = new StringBuilder("AbcdAbceAbc");

        //indexOf-->指定字符串在StringBuilder首次出现的位置
        System.out.println("bc在字符串中首次出现的位置:" + aBuilder.indexOf("bc"));

        //indexOf-->指定字符串在StringBuilder从指定位置以后首次出现的位置
        System.out.println("从4开始bc在字符串中首次出现的位置:" + aBuilder.indexOf("bc", 3));

        //lastIndexOf-->指定字符串在StringBuilder最后出现的位置
        System.out.println("bc在字符串最后出现的位置:" + aBuilder.lastIndexOf("bc"));

        //lastIndexOf-->指定字符串在StringBuilder在指定位置以后最后出现的位置
        System.out.println(aBuilder.lastIndexOf("bc", 10));
    }
/**
 * 总结：indexOf与lastIndexOf是判断指定字符串在StringBuilder中首次/最后出现的位置
 */
}
