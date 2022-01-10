package string;

/**
 * 关于替换字符串的示例
 *
 * @author Mo
 */
public class SubDemo {

    public static void main(String[] args) {
        //定义即将使用到的字符串
        String aString = new String("abcdefg");
        String bString = new String("  abc de ");
        //substring方法，从指定位置开始截取字符串
        System.out.println("从第二个位置开始截取的新字符串为：" + aString.substring(2));

        //substring方法，从指定开始位置到指定结束位置-1截取字符串
        System.out.println("从第二个位置到第五个位置之前的新字符串为:" + aString.substring(2, 5));

        //subSequence方法，从指定开始位置到指定结束位置-1截取字符序列
        System.out.println("从第二个位置到第五个位置之前的新字符序列为:" + aString.subSequence(2, 5));

        //trim方法，返回去除字符串两端的空格后的新字符串
        System.out.println("第二个字符串去除两头空格后的新字符串为:" + bString.trim());


    }
    /**
     * 总结：
     * Sting可以从指定位置截取字符串，
     * 可以在指定位置之间截取字符串/字符序列
     * 可以仅将字符串两端的空格去除
     */

}
