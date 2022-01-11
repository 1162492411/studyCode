package dataStructureJ.treeDemo;

/**
 * 使用数组中的元素结点链接而成二叉树
 *
 * @author Mo
 */
public class ABTreeNode {
    Object element;
    int left, right;

    public ABTreeNode(Object obj) {
        element = obj;
        left = right = 0;
    }

    public ABTreeNode(Object obj, int left, int right) {
        element = obj;
        this.left = left;
        this.right = right;
    }
}
