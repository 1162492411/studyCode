package stringBuilder;

/**
 * StringBuilder的常用API
 *
 * @author Mo
 */
public class Demo {

    public static void main(String[] args) {
        //构造一个StringBuilder对象
        StringBuilder aBuilder = new StringBuilder("abcdefg");

        //append-->向对象中追加内容
        aBuilder.append("qwer");
        System.out.println("测试append方法:" + aBuilder);

        //delete-->从对象中删除指定位置的内容
        aBuilder.delete(2, 5);
        System.out.println("测试delete方法:" + aBuilder);

        //insert-->向对象中指定位置插入内容
        aBuilder.insert(2, "zz");
        System.out.println("测试insert方法:" + aBuilder);

        //replace-->替换对象中指定位置的内容
        aBuilder.replace(0, 2, "qwe");
        System.out.println("测试replace方法:" + aBuilder);

        //reverse-->反转对象
        System.out.println("测试reverse方法:" + aBuilder.reverse());

        //查看对象的长度
        System.out.println("该字符串的长度为:" + aBuilder.length());

        //查看对象的容量
        System.out.println("该字符串的容量为:" + aBuilder.capacity());
    }
/**
 * 总结：
 * 1.StringBuilder是动态字符串，能实现字符串的动态修改
 * 2.StringBuilder常用方法包括插入、删除、替换、定点插入、反转
 * 3.StringBuilder有长度和容量之分
 */
}
