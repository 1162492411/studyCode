package dataStructureJ.stackDemo;

/**
 * 栈的抽象接口定义
 */
public interface MyStack {
    void push(Object obj);//向栈顶插入一个新元素obj

    Object pop();//从栈中删除栈顶元素并返回

    Object peek();//返回栈顶元素的值

    boolean isEmpty();//判断栈是否为空

    boolean isFull();//判断栈是否满

    void clear();//清除栈的所有元素
}
