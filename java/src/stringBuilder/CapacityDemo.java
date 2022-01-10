package stringBuilder;


/**
 * 关于创建动态字符串的示例
 *
 * @author Mo
 */
public class CapacityDemo {

    /**
     * 输出StingBuilder的相关信息
     */
    public static void printfInfo(StringBuilder aBuilder) {
        System.out.println("动态字符串的内容为：" + aBuilder);
        System.out.println("动态字符串的长度为：" + aBuilder.length());
        System.out.println("动态字符串的容量为：" + aBuilder.capacity());
    }

    public static void main(String[] args) {
        //新建一个空的动态字符串
        StringBuilder aBuilder = new StringBuilder();
        printfInfo(aBuilder);//查看其信息

        aBuilder.append("a");//添加一个字符到动态字符串中
        printfInfo(aBuilder);//查看其信息

        aBuilder.append("qwertyuiopasdfg");//添加一堆字符到动态字符串中
        printfInfo(aBuilder);//查看其信息

        aBuilder.append("a");//再次添加一个字符到动态字符串中
        printfInfo(aBuilder);//查看其信息

        StringBuilder builder = new StringBuilder("abc");//创建一个指定字符串内容的动态字符串
        printfInfo(builder);//查看其信息
    }
/**
 * 总结：
 * 1.StringBuilder对象有容量和长度之分，仅当长度等于容量时容量才会进行扩充
 * 2.StringBuilder对象默认容量为16，创建指定字符内容的StringBuilder时
 * 其容量为字符串长度加16(见StringBuilder源代码)
 * 3.StingBuilder容量扩充策略：默认每次扩充后容量为原容量的2倍+2，
 * 容量如果扩充2倍加2后仍然不够，直接扩充到需要的容量大小(见AbstractStringBuilder源代码)
 */
}
