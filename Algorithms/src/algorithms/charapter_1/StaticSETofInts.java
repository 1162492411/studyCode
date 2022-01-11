package algorithms.charapter_1;

import java.util.Arrays;

/**
 * 二分查找，适用于整数集合
 *
 * @author Mo
 */
public class StaticSETofInts {
    private int[] a;

    /**
     * 构造函数
     *
     * @param keys 源数组
     */
    public StaticSETofInts(int[] keys) {
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++) {
            a[i] = keys[i];//保护性复制//????什么意思
        }
        Arrays.sort(a);
    }

    /**
     * 根据指定值在指定数组中进行二分查找
     *
     * @param key 指定的值
     * @return 指定值是否存在于指定数组中
     */
    public boolean contains(int key) {
        return rank(key) != -1;
    }

    /**
     * 查找指定值是否在指定数组中存在
     *
     * @param key 指定的值
     * @return 指定值是否存在于指定数组中
     */
    private int rank(int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] keys = {1, 5, 1, 4, 2, 3};
        StaticSETofInts set = new StaticSETofInts(keys);
        System.out.println(set.contains(4));
        System.out.println(set.contains(6));
        System.out.println(set.contains(-10));
    }

}