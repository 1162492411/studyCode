package dataStructureJ.setDemo;

/**
 * 结点的定义
 *
 * @author Mo
 */
public class Node {
    Object element;// 值域
    Node next;// 指针域

    public Node(Node nextval) {
        next = nextval;
    }// 只对指针域赋值

    public Node(Object obj, Node nextval) {// 对值域和指针域赋值
        element = obj;
        next = nextval;
    }
}