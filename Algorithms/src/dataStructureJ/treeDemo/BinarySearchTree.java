package dataStructureJ.treeDemo;

/**
 * 二叉搜索数的接口类
 *
 * @author Mo
 */
public interface BinarySearchTree {
    Object find(Object obj);//查找值为obj的结点

    Object update(Object obj);//更新值为obj的结点

    boolean insert(Object obj);//插入值为obj的结点

    boolean delete(Object obj);//删除值为pbj的结点
}
