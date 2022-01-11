package dataStructureJ.treeDemo;

/**
 * 二叉树的抽象数据类型
 *
 * @author Mo
 */
public interface BinaryTree {
    //由mode数组提供遍历二叉树的四种不同的访问次序
    final String[] mode = {"preOrder", "inOredr", "postOrder", "levelOrder"};

    //根据二叉树的广义表表示在计算机中建立对应的二叉树
    void creatBTree(String gt);

    //判断二叉树是否为空
    boolean isEmpty();

    //按照字符串指定的次序遍历二叉树
    void traverseBTree(String s);

    //从二叉树中查找值为obj的结点
    Object findBTree(BTreeNode rt, Object obj);

    //求二叉树的深度
    int depthBTree(BTreeNode rt);

    //求二叉树的结点数
    int countBTree(BTreeNode rt);

    //按照树的一种表示方法输出二叉树
    void printBTree(BTreeNode rt);

    //清除二叉树中的所有结点
    void clearBTree();
}
