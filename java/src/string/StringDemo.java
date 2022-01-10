package string;

/**
 * String的常用API示例
 *
 * @author Mo
 */
public class StringDemo {

    public static void main(String[] args) {
        //创建字符串
        String aString = " Hello, world,  ";

        //length-->获取字符串长度
        System.out.println("字符串长度为-->" + aString.length());

        //concat-->连接字符串
        System.out.println(aString.concat("test"));

        //+-->连接字符串
        System.out.println(aString + 2);

        //formate-->创建格式化字符串
        System.out.format("这是第%d次测试字符串%d%n", 1, 2);

        //charAt-->获取指定位置的字符
        System.out.println(aString.charAt(3));

        //toCharArray-->获取字符数组
        System.out.println(aString.toCharArray());

        //subString-->截取子字符串
        System.out.println(aString.substring(2, 5));

        //根据","进行分割，得到2个子字符串
        String subSentences[] = aString.split(",");
        for (String sub : subSentences) {
            System.out.println(sub);
        }

        //trim-->去除空格
        System.out.println(aString.trim());

        //toLowerCase-->全部变成小写
        System.out.println(aString.toLowerCase());

        //toUpperCase-->全部变成大写
        System.out.println(aString.toUpperCase());

        //contains-->判断是否包含子字符串
        System.out.println("a字符串是否包含o-->" + aString.contains("o"));

        //indexOf-->判断指定字符在字符串中的位置
        System.out.println("a字符串中o首次出现的位置为-->" + aString.indexOf('o'));

        //replace-->替换字符串中指定字符
        System.out.println(aString.replace('o', 'a'));

        //replaceFirst-->只替换字符串第一个出现的指定字符
        System.out.println(aString.replaceFirst(",", "_"));

        //startsWith-->是否以指定字符串开头
        System.out.println("a字符串是否以空格开头-->" + aString.startsWith(" "));

        //endsWith-->是否以指定字符串结尾
        System.out.println("a字符串是否以a结尾-->" + aString.startsWith("a"));

        String bString = "aaa";
        String cString = "bbb";
        String dString = "aaa";
        String eString = "aAA";
        //比较两字符串是否相等
        System.out.println("b和c字符串是否相等-->" + bString.equals(cString));
        System.out.println("b和d字符串是否相等-->" + bString.equals(dString));
        System.out.println("b和e字符串是否相等-->" + bString.equals(eString));

    }

}
