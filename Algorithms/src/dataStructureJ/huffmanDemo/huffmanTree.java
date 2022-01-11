package dataStructureJ.huffmanDemo;

import java.util.*;

public class huffmanTree {
    private static ArrayList<Node> list = null;// 结点集合

    // 定义一个默认的哈夫曼树
    public static ArrayList<Node> defaultHuffmanTree() {
        list = new ArrayList<Node>();
        list.add(new Node("c", 2));
        list.add(new Node("a", 4));
        list.add(new Node("s", 2));
        list.add(new Node("t", 3));
        list.add(new Node("b", 3));
        return list;
    }

    // 用户输入定义一个哈弗曼树
    public static ArrayList<Node> initHuffmanTree() {
        list = new ArrayList<Node>();
        Scanner a = new Scanner(System.in);
        System.out.println("请输入结点个数：");
        int count = a.nextInt();
        System.out.println("结点个数为" + count);
        for (int i = 0; i < count; i++) {// 循环count次
            System.out.println("请输入第" + (i + 1) + "个结点:");
            Scanner bScanner = new Scanner(System.in);
            String data = bScanner.nextLine();
            System.out.println("请输入第" + (i + 1) + "个结点的权值:");
            Scanner cScanner = new Scanner(System.in);
            int weight = cScanner.nextInt();
            list.add(new Node(data, weight));
        }
        return list;
    }

    // 创建哈夫曼树
    public static Node creatHuffmanTree(ArrayList<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);// 先将结点进行排序
            Node left = nodes.get(nodes.size() - 1);
            Node right = nodes.get(nodes.size() - 2);
            Node parent = new Node(null, left.getWeight() + right.getWeight());
            parent.setLeft(left);
            parent.setRight(right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    // 广度优先遍历
    public static ArrayList<Node> breadthFirst(Node root) {
        Queue<Node> queue = new ArrayDeque<Node>();
        ArrayList<Node> lists = new ArrayList<Node>();
        if (root != null) {
            // 将根元素入“队列”
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            // 将该队列的“队尾”的元素添加到List中
            lists.add(queue.peek());
            Node p = queue.poll();
            // 如果左子节点不为null，将它加入“队列”
            if (p.getLeft() != null) {
                queue.offer(p.getLeft());
            }
            // 如果右子节点不为null，将它加入“队列”
            if (p.getRight() != null) {
                queue.offer(p.getRight());
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        Node aHead = creatHuffmanTree(defaultHuffmanTree());
//		Node aHead = creatHuffmanTree(initHuffmanTree());
        System.out.println("广度遍历哈夫曼树:\n" + huffmanTree.breadthFirst(aHead));
    }

}
