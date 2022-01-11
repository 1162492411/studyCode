package dataStructureJ.queueDemo;

public class sequenceQueue implements MyQueue {

    final int maxSize = 10;//默认初始长度
    final int sizePlus = 10;//默认增加长度
    private Object[] queueArray;//默认存储队列的数组引用
    private int front, rear;//定义首尾指针

    public sequenceQueue() {
        front = rear = 0;
        queueArray = new Object[maxSize];
    }

    public sequenceQueue(int n) {
        if (n <= 0) System.exit(1);
        front = rear = 0;
        queueArray = new Object[n];
    }

    /**
     * 元素入队
     * 首先定义临时指针，使其遍历到队尾
     * 若发现队已满，则扩充队，并新建数组将数据转移到新数组，将数组引用指向新数组
     * 若队列还有空余位置，则将队尾指针向后移,元素插入队尾元素之后
     * ps:从旧数组复制数据到新数组时需根据尾指针位置分情况复制，复制数据后注意处理首尾指针
     */
    public void enter(Object obj) {
        if (isFull()) {
            Object[] temp = new Object[queueArray.length + sizePlus];
            if (rear == queueArray.length - 1) {//若队尾刚好在末尾
                for (int i = 1; i <= rear; i++) temp[i] = queueArray[i];
            } else {//否则，先将原队列前面数据复制到新数组，再将后方数据复制到新数组
                int i, j = 1;
                for (i = front + 1; i < queueArray.length; i++, j++) temp[j] = queueArray[i];
                for (i = 0; i < rear; i++) temp[j] = queueArray[i];
                front = 0;
                rear = queueArray.length - 1;
            }
            queueArray = temp;
        }
        rear = (rear + 1) % queueArray.length;//求出队尾的下一个循环位置
        queueArray[rear] = obj;//将元素的值赋给新的队尾位置
    }

    /**
     * 元素出队
     * 首先判断是否队空，若是，返回null
     * 否则，将指针指向队首指针的下一位置，返回该位置的数据
     */
    public Object leave() {
        if (isEmpty()) return null;
        front = (front + 1) % queueArray.length;
        return queueArray[front];
    }

    /**
     * 读取队首元素
     * 返回队首指针的下一个位置的数据(注意比较与元素出队的不同之处)
     */
    public Object peek() {
        if (isEmpty()) return null;
        return queueArray[(front + 1) % queueArray.length];
    }

    /**
     * 判断队列是否已空
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 判断队列是否已满
     */
    public boolean isFull() {
        return (rear + 1) % queueArray.length == front;
    }

    /**
     * 清除队中所有元素
     * 使队首指针与队尾指针的值均为0
     */
    public void clear() {
        front = rear = 0;
    }

    public static void main(String[] args) {
        sequenceQueue aQueue = new sequenceQueue();
        aQueue.enter(1);
        aQueue.enter(2);
        aQueue.enter(3);

        System.out.println(aQueue.peek());
        System.out.println(aQueue.peek());

        System.out.println(aQueue.leave());
        System.out.println(aQueue.leave());

    }

}
