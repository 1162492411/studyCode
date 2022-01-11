package leetCode.String;

public class sss {

    public static final int MOD = 20;

    // 将字符串转成hash值
    public static int changeHash(String key) {
        // 数组大小一般取质数
        int arraySize = 11113;
        int hashCode = 0;
        String result=null;
        // 从字符串的左边开始计算
        for (int i = 0; i < key.length(); i++) {
            // 将获取到的字符串转换成数字，比如a的码值是97，则97-96=1
            int letterValue = key.charAt(i) - 96;
            // 就代表a的值，同理b=2；
            // 防止编码溢出，对每步结果都进行取模运算
            hashCode = ((hashCode << 5) + letterValue) % arraySize;
        }
        return Math.abs(hashCode % MOD);
    }

    public static void main(String[] args) {
        System.out.println(changeHash("120000000000-3-1-0a10bb38aab0430ea98b0ada3a59c4eb" + "888b6d7501e04c739bd8acad7e7a2330"));
        System.out.println(changeHash("120000000000-3-1-0a10bb38aab0430ea98b0ada3a5ewe4eb" + "888b6d7501e04cewe39bd8acad7e7a2330"));
        System.out.println(changeHash("120000000000-3-1-0a10bb38aab0430ea98b0aeweweda3a59c4eb" + "888b6d7501eec739bd8acad7e7a2330"));
        System.out.println(changeHash("120000000000-3-1-0a10bb38aab0430ea98b0ada3ewe9c4eb" + "888b6d7501e04c739rgd8acad7e7a2330"));
        System.out.println(changeHash("120000000000-3-1-0a10bb38aab0430ea98b0aeweda3a59c4eb" + "888b6d7501e0grg739bd8acad7e7a2330"));
    }



}
