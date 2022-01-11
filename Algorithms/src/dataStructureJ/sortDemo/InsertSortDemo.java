package dataStructureJ.sortDemo;

/**
 * 插入排序 包含：直接插入排序、希尔排序
 *
 * @author Mo
 */
public class InsertSortDemo {

    /**
     * 直接插入排序 描述：每步取出一个待排序的记录，按大小顺序将其插入到已排序序列中的合适位置，直至全部排序完
     */
    public static int[] directInsert(int[] source) {
        for (int i = 1; i < source.length; i++) {// 遍历整个数组
            // 假定第一个位置的记录已排序，从第二个位置开始排序
            int temp = source[i];// 取出待排序的记录并将其暂存
            int j = i - 1;
            // 将待排序的记录与已排序的序列比较
            // 若已排序序列中某项的值大于待排序的记录，将其后移一位
            for (; j >= 0 && source[j] > temp; j--)
                source[j + 1] = source[j];
            source[j + 1] = temp;// 将待排序记录放入该位置
        } // 遍历整个数组结束
        return source;
    }

    /**
     * 希尔排序 描述：先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，每组中记录的下标相差d.对每组中全部元素进行直接插入排序，
     * 然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入排序。当增量减到1时，进行直接插入排序后，排序完成
     */
    public static int[] shellSort(int[] source) {
        int j, step;
        int len = source.length;
        for (step = len / 2; step > 0; step /= 2)
            for (j = step; j < len; j++)
            /**
             * 从数组第step个元素开始，然后将每个元素与自己组内的数据进行直接插入排序
             */
                if (source[j] < source[j - step]) {
                    int temp = source[j];
                    int k = j - step;
                    while (k >= 0 && source[k] > temp) {
                        source[k + step] = source[k];
                        k -= step;
                    }
                    source[k + step] = temp;
                }
        return source;// 返回排序后的数组
    }

    /**
     * 测试函数
     */
    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        // int[] result = directInsert(a);//测试直接插入排序
        int[] result = shellSort(a);// 测试希尔排序
        for (int i : result)
            System.out.println(i);
    }

}
