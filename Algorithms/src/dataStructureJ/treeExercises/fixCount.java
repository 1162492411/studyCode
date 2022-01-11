package dataStructureJ.treeExercises;

/**
 * 哈夫曼树练习题
 * 描述：
 * 修理牧场：农夫要修理牧场的一段栅栏，他测量了栅栏，发现需要N块木头，每块木头长度为整数Li个长度单位，于是他购买了一条很长的、能锯成N块的木头，即该木头的长度是Li的总和。
 * 但是农夫自己没有锯子，请人锯木头的酬金跟这段木头的长度成正比。为简单起见，不妨就设酬金等于所锯木头的长度。例如，要将长度为20的木头锯成长度为8、7和5的三段，第一次锯木头花费20，将木头锯成12和8；第二次锯木头花费12，将长度为12的木头锯成7和5，总花费为32。如果第一次将木头锯成15和5，则第二次锯木头花费15，总花费为35（大于32）。
 * 请编写程序帮助农夫计算将木头锯成N块的最少花费。
 * 首先输入一个正整数N（N≤104），表示要将木头锯成N块。接着给出N个正整数Li（Li≤50），表示每段木块的长度。输出一个整数，即将木头锯成N块的最少花费。
 * 例如：
 * 输入：
 * 8
 * 4 5 1 2 1 3 1 1
 * 输出：
 * 49
 */

import huffmanDemo.Node;
import huffmanDemo.huffmanTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class fixCount {
    // 用户定义输入哈夫曼树
    public static ArrayList<Node> initHuffmanTree() {
        ArrayList<Node> list = new ArrayList<Node>();
        Scanner a = new Scanner(System.in);
        System.out.println("请输入想要锯成的块数：");
        int count = a.nextInt();
        for (int i = 0; i < count; i++) {// 循环count次
            System.out.println("请输入第" + (i + 1) + "块的长度:");// 记录每块长度
            Scanner cScanner = new Scanner(System.in);
            int weight = cScanner.nextInt();
            list.add(new Node(i + "", weight));// 数据域赋非空值即可
        }
        return list;
    }

    // 广度遍历计算值
    public static int breadthFirst(Node root) {
        int sum = 0;// 累加和
        Queue<Node> queue = new ArrayDeque<Node>();
        ArrayList<Node> lists = new ArrayList<Node>();
        if (root != null) {
            // 将根元素入“队列”
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            // 将该队列的“队尾”的元素添加到List中
            lists.add(queue.peek());
            sum += queue.peek().getWeight();// 将队尾的结点的权值累加
            Node p = queue.poll();
            // 如果左子节点的数据为null，将它加入“队列”
            if (p.getLeft().getData() == null) {
                queue.offer(p.getLeft());
            }
            // 如果右子节点的数据为null，将它加入“队列”
            if (p.getRight().getData() == null) {
                queue.offer(p.getRight());
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Node aHead = huffmanTree.creatHuffmanTree(initHuffmanTree());
        System.out.println(breadthFirst(aHead));
    }

}
