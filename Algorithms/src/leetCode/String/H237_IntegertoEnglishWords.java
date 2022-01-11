package leetCode.String;

/**
 * 问题描述： Convert a non-negative integer to its english words representation.
 * Given input is guaranteed to be less than 231 - 1.
 * <p>
 * For example,
 * <p>
 * 123 -> "One Hundred Twenty Three" 12345 ->
 * "Twelve Thousand Three Hundred Forty Five" 1234567 ->
 * "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * @author Mo
 */
public class H237_IntegertoEnglishWords {
    private static String[] weights = {" ", " Thousand ", " Million ", " Billion "};// 存储单位的数组
    private static String[] digitNum = {" ", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ",
            "Nine "};// 0~9的英文表达
    private static String[] teenNum = {" ", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ",
            "Seventeen ", "Eighteen ", "Nineteen "};// 11~19的英文表达
    private static String[] tyNum = {" ", "Ten ", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ",
            "Eighty ", "Ninety "};// 10、20、...90的英文表达

    public static String numberToWords(int num) {
        // 方案一：首先切割输入的数字，对每一个切割的数字进行转换，根据切割的数字的位置为其赋予单位，将处理后的内容拼接成字符串返回
        // 首先处理特殊情况
        if (num < 0 || num > 2_147_483_647)
            System.exit(-1);// 如果数值不合理，停止运行
        if (num == 0)
            return "Zero";// 如果为0，直接返回zero
        String result = " ";// 存储最终结果
        int[] temp = new int[4];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = num % 1000;// 取出数字的最后三位
            // 将取出来的三位数进行转换并赋予单位
            if (temp[i] != 0) {// 若当前处理的三位数不同为0
                result = Tranform(temp[i]) + weights[i] + result;
            }
            num = num / 1000;// 抛弃处理过的后三位
        }

        return result.trim();
    }

    // 将数字转换为英文
    private static String Tranform(int num) {
        StringBuilder resulttemp = new StringBuilder();
        // 若输入的数字全为0，直接返回
        if (num == 0)
            return "";
        int digit = num % 10;// 取个位数
        int teen = (num / 10) % 10;// 取十位数
        int Hundred = num / 100;// 取百位数
        // 转化个位数
        if (digit != 0 && teen != 1)
            resulttemp.insert(0, digitNum[digit]);
        // 转化10
        if (teen == 1 && digit == 0)
            resulttemp.insert(0, tyNum[teen]);
        // 转化11~19
        if (teen == 1 && digit != 0)
            resulttemp.insert(0, teenNum[digit]);
        // 转化20、30、...90
        if (teen > 1)
            resulttemp.insert(0, tyNum[teen]);
        // 转化百位数
        if (Hundred > 0)
            resulttemp.insert(0, digitNum[Hundred] + "Hundred ");
        // 对字符串再处理
        return resulttemp.toString().trim();
    }

    public static void main(String[] args) {
        // System.out.println(numberToWords(5));
        // System.out.println(numberToWords(10));
        System.out.println(numberToWords(0));
        System.out.println(numberToWords(12));
        System.out.println(numberToWords(13));

        // System.out.println(numberToWords(20));
        // System.out.println(numberToWords(21));
        // System.out.println(numberToWords(100));
        // System.out.println(numberToWords(101));
        // System.out.println(numberToWords(110));
        // System.out.println(numberToWords(112));
        // System.out.println(numberToWords(120));
        // System.out.println(numberToWords(123));
        System.out.println(numberToWords(1_120_000_004));
        System.out.println(numberToWords(12345));

    }

}
