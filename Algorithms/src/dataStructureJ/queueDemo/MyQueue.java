package dataStructureJ.queueDemo;

/**
 * 队列的抽象数据类型
 */
public interface MyQueue {
    void enter(Object obj);//元素进队

    Object leave();//元素离队

    Object peek();//返回队首的元素

    boolean isEmpty();//判断队是否为空

    boolean isFull();//判断队是否已满

    void clear();//清除队中所有元素
}
