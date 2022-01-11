package dataStructureJ.huffmanDemo;

/**
 * 哈夫曼树的结点
 *
 * @author Mo
 */
public class Node implements Comparable<Node> {

    private String data;//字符
    private int weight;//权值
    private Node parent, left, right;//parent为父结点，left为左孩子结点，right为右孩子结点

    //空构造函数
    public Node() {
        super();
    }

    //根据数据定义结点
    public Node(String data) {
        this.data = data;
    }

    //根据权值定义结点
    public Node(String data, int weight) {
        super();
        this.data = data;
        this.weight = weight;
    }

    //根据权值、父结点、左孩子结点、右孩子结点定义结点
    public Node(String data, int weight, Node parent, Node left, Node right) {
        super();
        this.data = data;
        this.weight = weight;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    //比较
    public int compareTo(Node o) {
        if (o.getWeight() > this.getWeight()) return 1;
        else if (o.getWeight() < this.getWeight()) return -1;
        return 0;
    }

    //打印结点
    public String toString() {
        return "\ndata:" + this.data + ";weight:" + this.weight;
    }
}
