package stringBuilder;

public class InitDemo {

    /**
     * 输出StingBuilder的相关信息
     */
    public static void printfInfo(StringBuilder aBuilder) {
        System.out.println("动态字符串的内容为：" + aBuilder);
        System.out.println("动态字符串的长度为：" + aBuilder.length());
        System.out.println("动态字符串的容量为：" + aBuilder.capacity());
    }

    /**
     * 测试函数
     */
    public static void main(String[] args) {
        //初始化一个空StringBuilder对象
        StringBuilder aBuilder = new StringBuilder();
        printfInfo(aBuilder);

        //根据字符串初始化一个StringBuilder对象
        StringBuilder builder = new StringBuilder("abcde");
        printfInfo(builder);

        //根据指定的容量建立一个Stir能够Builder对象
        StringBuilder cBuilder = new StringBuilder(20);
        printfInfo(cBuilder);

        //根据指定的字符序列建立一个StirngBuilder对象
        StringBuilder dBuilder = new StringBuilder("qwerf");
        printfInfo(dBuilder);
    }

}
