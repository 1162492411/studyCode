package stringBuilder;

/**
 * 其他方法示例
 *
 * @author Mo
 */
public class OtherDemo {

    public static void main(String[] args) {
        StringBuilder aBuilder = new StringBuilder("abcdefg");

        //delete方法-->删除指定位置之间的字符
        System.out.println("delete方法:" + aBuilder.delete(1, 3));

        //deleteCharAt方法-->删除指定位置的字符
        System.out.println("deleteChatAt方法:" + aBuilder.deleteCharAt(2));

        //replace-->替换对象中指定位置的内容
        aBuilder.replace(0, 2, "qwe");
        System.out.println("replace方法:" + aBuilder);

        //reverse-->反转字符串
        System.out.println("反转字符串:" + aBuilder.reverse());

        //此外还有writeObject与readObject这两个序列化和反序列化的方法

    }

}
