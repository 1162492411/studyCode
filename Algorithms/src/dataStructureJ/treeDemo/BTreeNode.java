package dataStructureJ.treeDemo;

/**
 * 采用独立分配的结点链接而成二叉树
 *
 * @author Mo
 */
public class BTreeNode {
    Object element;
    BTreeNode left, right;

    public BTreeNode(Object obj) {
        element = obj;
        left = right = null;
    }

    public BTreeNode(Object obj, BTreeNode left, BTreeNode right) {
        element = obj;
        this.left = left;
        this.right = right;
    }
}
