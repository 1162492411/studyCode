package dataStructureJ.sortDemo;

/**
 * 交换排序 包含：冒泡排序、快速排序
 *
 * @author Mo
 */
public class ChangeSortDemo {

    /**
     * 冒泡排序
     * 描述：在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。即：
     * 每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换
     */
    public static int[] bubleSort(int[] source) {
        for (int i = 0; i < source.length - 1; i++) {// 循环整个数组
            for (int j = 0; j < source.length - 1; j++) {// 从当前位置开始向后循环比较
                if (source[j] > source[j + 1]) {// 若后面一位大于前面一位，则交换位置
                    int temp = source[j];
                    source[j] = source[j + 1];
                    source[j + 1] = temp;
                } // 交换位置结束
            } // 从当前位置向后循环结束
        } // 循环数组结束
        return source;// 返回排序后的数组
    }

    /**
     * 快速排序
     * 描述：选择一个位置作为基准，通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,此时基准元素在其排好序后的正确位置,
     * 然后再用同样的方法递归地排序划分的两部分
     */
    public static int[] quickSort(int[] source) {
        qsort1(source, 0, source.length - 1);
        return source;// 返回排序后的数组
    }

    public static void qsort1(int[] a, int p, int r) {
        // 0个或1个元素，返回
        if (p >= r)
            return;
        // 选择左端点为pivot
        int x = a[p];
        int j = p;
        for (int i = p + 1; i <= r; i++) {
            // 小于pivot的放到左边
            if (a[i] < x) {
                swap(a, ++j, i);
            }
        }
        // 交换左端点和pivot位置
        swap(a, p, j);
        // 递归子序列
        qsort1(a, p, j - 1);
        qsort1(a, j + 1, r);
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 测试函数
     */
    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        // int[] result = directInsert(a);//测试直接插入排序
        int[] result = quickSort(a);// 测试希尔排序
        for (int i : result)
            System.out.println(i);
    }

}
