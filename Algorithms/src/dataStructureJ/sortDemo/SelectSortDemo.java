package dataStructureJ.sortDemo;

/**
 * 选择排序
 * 包含：简单选择排序、堆排序
 *
 * @author Mo
 */
public class SelectSortDemo {

    /**
     * 简单选择排序 描述：在待排序的一维数组中，选出第一小的数与第一个位置的数进行交换，在剩下的数中选择出其中第一小的数
     * 与剩下数组中第一个位置的数进行交换，直至循环到倒数第二个数与最后一个数比较为止
     */
    public static int[] selectSort(int[] source) {
        for (int i = 0; i < source.length; i++) {// 循环整个数组
            int minPosition = i;// 使用变量存储当次循环中最小值的下标
            // 遍历未排序的数组，找出最小值的下标
            for (int j = i + 1; j < source.length; j++) {
                if (source[j] < source[minPosition])
                    minPosition = j;
            }
            // 将未排序数组的第一个位置与待排序数组中的最小值交换位置
            int temp = source[i];
            source[i] = source[minPosition];
            source[minPosition] = temp;
        } // 循环数组结束
        return source;// 返回排序后的数组
    }

    /**
     * 堆排序
     */
    public static int[] heapSort(int[] source) {
        for (int i = 0; i < source.length; i++) {
            createMaxdHeap(source, source.length - 1 - i);
            swap(source, 0, source.length - 1 - i);
        }
        return source;//返回排序后的数组
    }

    //堆排序用到的建堆方法
    public static void createMaxdHeap(int[] source, int lastIndex) {
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            // 保存当前正在判断的节点  
            int k = i;
            // 若当前节点的子节点存在  
            while (2 * k + 1 <= lastIndex) {
                // biggerIndex总是记录较大节点的值,先赋值为当前判断节点的左子节点  
                int biggerIndex = 2 * k + 1;
                if (biggerIndex < lastIndex) {
                    // 若右子节点存在，否则此时biggerIndex应该等于 lastIndex  
                    if (source[biggerIndex] < source[biggerIndex + 1]) {
                        // 若右子节点值比左子节点值大，则biggerIndex记录的是右子节点的值  
                        biggerIndex++;
                    }
                }
                if (source[k] < source[biggerIndex]) {
                    // 若当前节点值比子节点最大值小，则交换2者得值，交换后将biggerIndex值赋值给k  
                    swap(source, k, biggerIndex);
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    //堆排序用到的交换位置方法
    public static void swap(int[] source, int i, int j) {
        if (i == j) {
            return;
        }
        source[i] = source[i] + source[j];
        source[j] = source[i] - source[j];
        source[i] = source[i] - source[j];
    }

    /**
     * 测试函数
     */
    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        // int[] result = directInsert(a);//测试直接插入排序
        int[] result = heapSort(a);// 测试希尔排序
        for (int i : result)
            System.out.println(i);

    }

}
