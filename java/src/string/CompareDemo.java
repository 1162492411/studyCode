package string;

/**
 * 关于比较字符串的示例
 *
 * @author Mo
 */
public class CompareDemo {

    public static void main(String[] args) {
        /**定义两个字符串以测试比较方法*/
        String aString = new String("abcd");
        String bString = new String("abcD");

        /**测试比较方法并打印其结果*/
        //比较两字符串是否相等
        System.out.println("eqauls  " + aString.equals(bString));
        //忽略大小写的情况下比较两字符串是否相等
        System.out.println("equalsIgnoreCase  " + aString.equalsIgnoreCase(bString));
        //比较两字符串的顺序，结果为0表示相等，结果大于0表示后者大于前者，结果小于0表示后者小于前者
        System.out.println("compareTo  " + aString.compareTo(bString));
        //忽略大小写的情况下，比较两字符串的顺序，结果为0表示相等，结果大于0表示后者大于前者，结果小于0表示后者小于前者
        System.out.println("compareToIgnoreCase  " + aString.compareToIgnoreCase(bString));
        //注：还可采用正则表达式进行比较


        System.out.println("s1:" + "/admin/public/login".startsWith("/admin/public/login"));
        System.out.println("s1:" + "/admin/public/login/".startsWith("/admin/public/login"));
    }

}
