package leetCode.String;

/**
 * Write a function that takes a string as input and reverse only the vowels of
 * a string. Example 1: Given s = "hello", return "holle".
 * <p>
 * Example 2: Given s = "leetcode", return "leotcede".
 *
 * @author Mo
 */
public class E_345_ReverseVowelsOfAString {

    public static String reverseVowels(String s) {
        /**
         * 方案一: 将字符串转换为数组，定义两个下标变量，分别从头部和尾部对数组进行遍历；
         * 令两个下标变量进行游走，若发现当前下标对应的字符为元音，且两下标位置合理，交换两下标对应的字符，直到遍历完数组--5ms
         */
        //处理特殊情况
        if (s.length() <= 1) {
            return s;
        }
        char[] src = s.toCharArray();// 首先将字符串转换为字符数组
        // 定义两个下标变量i,j，分别从头到尾和从尾到头遍历数组
        // 检查每一位字符是否为元音字母，若不是，下标继续游走
        for (int i = 0, j = src.length - 1; i < j; i++, j--) {
            while (i < j && !isVowels(src[i]))
                i++;
            while (i < j && !isVowels(src[j]))
                j--;
            // 若是，交换两下标对应的字符的位置
            if (i <= j)
                swap(src, i, j);
        }
        return new String(src);
    }

    // 判断是否是元音字母
    public static boolean isVowels(char ch) {
        //方案一：将指定字符转化为小写后再进行逐个比较
        char c = Character.toLowerCase(ch);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        //方案二：使用字符串的indexOf方法，判断指定字符是否包含于"AEIOUaeiou"
        //该方法较复杂且占据空间，因为字符串的indexOf方法过于复杂
//		if("AEIOUaeiou".indexOf(ch) != -1)
//			return true;
//		return false;
    }

    // 交换字母位置
    public static void swap(char[] src, int i, int j) {
        char temp = src[i];
        src[i] = src[j];
        src[j] = temp;
    }

    public static void main(String[] args) {
        String aString = "aA";
        String bString = "ileetcode";
        System.out.println(reverseVowels(bString));
        System.out.println(reverseVowels(aString));

    }

}
