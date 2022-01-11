package leetCode.String;
/**
 * 问题描述：
 * Write a function that takes a string as input and returns the string reversed.
 * Example:
 * Given s = "hello", return "olleh". 
 * @author Mo
 *
 */
public class E_344_ReverseString {
	/**
	 * 问题解法
	 * @param s 输入的字符串
	 * @return 输入的字符串的逆置字符串
	 */
    public static  String reverseString(String s) {
    	//方法一：利用StringBuilder的逆置后使用Stirng.valueOf--∞∞
    	//return String.valueOf(new StringBuilder(s).reverse());
    	//方法二：利用StringBuilder的逆置后使用StringBuilder的toString方法--4ms
    	//return new StringBuilder(s).reverse().toString();
    	//方法三：每次从原字符串中取一个字符复制到目标数组，使用新数组初始化一个字符串--3ms
       // char[] buf = s.toCharArray();
        char[] dst = new char[s.length()];
        for(int i = 0,j = s.length() - 1;j >= 0; i++,j--){
            //System.arraycopy(buf, j, dst, i, 1);
            dst[i] = s.charAt(j);
        }
        return new String(dst);
    }
    /**
     * 测试函数
     * @param args
     */
    public static void main(String[] args){
    	String aString = "hello";
    	System.out.println(reverseString(aString));
    }
}
