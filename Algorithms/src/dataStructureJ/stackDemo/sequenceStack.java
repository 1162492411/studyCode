package dataStructureJ.stackDemo;

import java.util.Stack;

public class sequenceStack implements MyStack {
    Stack<String> aStack = new Stack<String>();
    final int maxSize = 3;//默认初始长度
    final int sizePlus = 3;  //默认增加长度
    private Object[] stackArray;//定义存储栈的数组引用
    private int top;//定义保存栈的栈顶元素的下标


    /**
     * 无参的构造函数
     */
    public sequenceStack() {
        top = -1;//置栈的栈顶为-1
        stackArray = new Object[maxSize];//初始默认长度的数组以存储元素
    }

    /**
     * 带初始长度参数的构造函数
     *
     * @param n 初始长度
     */
    public sequenceStack(int n) {
        if (n <= 0) System.exit(1);//首先检验长度是否合理
        top = -1;//置栈的栈顶为-1
        stackArray = new Object[n];//初始化一个指定长度的数组以存储数据
    }

    /**
     * 元素进栈
     * 首先判断栈是否满
     * 若栈满则增加长度并新建数组，将旧数组数据复制进新数组，并指向新数组
     * 否则栈长度加1，将新元素加入栈顶
     */
    public void push(Object obj) {
        if (isFull()) {
            Object[] temp = new Object[stackArray.length + sizePlus];
            for (int i = 0; i < top + 1; i++) temp[i] = stackArray[i];
            stackArray = temp;
            System.out.println("********此时栈进行了一次扩充***********");
        }
        top++;
        stackArray[top] = obj;
        System.out.println("*******此时进栈元素为:" + stackArray[top] + "*********");
    }

    /**
     * 元素出栈
     * 若栈以空则返回空
     * 否则使栈顶减1，返回栈顶之上的元素值
     */
    public Object pop() {
        if (isEmpty()) return null;
        top--;//是够可改写成 return stackArray[top--];
        return stackArray[top + 1];
    }

    /**
     * 取栈顶元素
     */
    public Object peek() {
        if (isEmpty()) return null;
        return stackArray[top];
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 判断栈是否已满
     */
    public boolean isFull() {
        return top == stackArray.length - 1;
    }

    /**
     * 清除栈中所有元素
     */
    public void clear() {
        top = -1;
    }

    /**
     * 检测信息函数
     */
    public void check() {
        System.out.println("此时栈顶为:" + top);
        System.out.println("此时栈顶元素为:" + peek());
        System.out.println("此时栈的长度为:" + stackArray.length);
        System.out.println("此时栈是否为空:" + isEmpty());
        System.out.println("此时栈是否已满:" + isFull());
        System.out.println("\n--------------------\n");
    }

    /**
     * 测试函数
     */
    public static void main(String[] args) {
        //初始化栈并查看其信息
        sequenceStack aStack = new sequenceStack();
        aStack.check();
        //元素入栈
        int[] a = {1, 4, 7};
        for (int i = 0; i < a.length; i++) {
            aStack.push(a[i]);
        }
        aStack.check();

        aStack.push(10);
        aStack.check();

        aStack.push(11);
        aStack.check();


        //访问栈顶元素	
        System.out.println("---访问栈顶元素：" + aStack.peek());
        aStack.check();
        System.out.println("---访问栈顶元素：" + aStack.peek());
        aStack.check();
        //元素出栈
        while (!aStack.isEmpty()) {
            System.out.println("---弹出栈顶元素：" + aStack.pop());
            aStack.check();
        }

    }
}
