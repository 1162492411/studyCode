package dataStructureJ.setDemo;

public class sequenceSet implements Set {
    final int defaultSize = 10;//预定义的集合的数组的初始长度
    private Object[] setArray;//定义存储集合的数组的引用对象
    private int length;//定义数组中所保存集合的当前长度

    /**
     * 无参的构造函数
     */
    public sequenceSet() {
        length = 0;//集合初始为空，即长度为0
        setArray = new Object[defaultSize];//数组初始长度为defaultSize的值10
    }

    /**
     * 带初始长度参数的构造函数
     *
     * @param n 指定的初始长度
     */
    public sequenceSet(int n) {
        if (n <= 0) System.exit(1);//若指定的长度不合法则退出程序
        length = 0;//初始集合长度为0
        setArray = new Object[n];//初始化长度为n的数组
    }

    /**
     * 检查保存集合的数组空间是否被用完
     */
    public boolean checkSize() {
        return length == setArray.length;
    }

    /**
     * 向集合插入一个元素
     *
     * @param obj 欲插入的元素
     * @return 插入是否成功
     */
    public boolean add(Object obj) {
        for (int i = 0; i < length; i++) {
            if (setArray[i].equals(obj)) return false;//遍历集合中是否存在欲插入的元素，若存在则返回false
        }
        if (checkSize()) {//如果数组被用完
            Object[] p = new Object[length * 2];//新建一个为原来两倍长度的数组
            for (int i = 0; i < length; i++) {//将原数组的值赋值到新数组
                p[i] = setArray[i];
            }
            setArray = p;//将原数组指向新数组
        }
        setArray[length] = obj;//在集合末尾插入新元素
        length++;//集合长度加1
        return true;//返回true表示插入成功
    }

    /**
     * 从集合中删除一个元素
     *
     * @param obj 欲删除的元素
     * @return 删除元素是否成功
     */
    public boolean remove(Object obj) {
        int i;
        for (i = 0; i < length; i++) {//遍历存储数据的数组
            if (setArray[i].equals(obj)) break;//若待删除的元素存在于数组中，则停止遍历
        }
        if (i < length) {//如果待删除的元素存在于数组中
            setArray[i] = setArray[length - 1];//将数组中最后一个值复制到待删除元素的位置上，将其覆盖
            length--;//数组长度减1
            return true;//返回真值表示操作成功			
        } else//如果待删除的数据不存在于数组中
            return false;//返回假值表示操作失败
    }

    /**
     * 判断一个元素是否属于集合
     *
     * @param obj 待判断的数据
     * @return 元素是否属于集合的结果
     */
    public boolean contains(Object obj) {
        for (int i = 0; i < length; i++)//遍历存储数据的数组
            if (setArray[i].equals(obj)) return true;//如果数组中某一位的数据与查询的数据相同，则返回true
        return false;//默认返回false，即元素不存在于该集合
    }

    /**
     * 返回集合中第i个元素的值
     *
     * @param i 指定的位置
     * @return 指定位置的元素的值
     */
    public Object value(int i) {
        if (i <= 0 && i > length) System.exit(1);//如果指定位置小于1或大于集合长度，则指定位置不合法
        return setArray[i - 1];//返回指定位置对应的下标处的数据
    }

    /**
     * 从集合中按值查找元素
     *
     * @param obj 指定的值
     * @return 查找的值是否存在
     */
    public Object find(Object obj) {
        for (int i = 0; i < length; i++)//遍历存储数据的数组
            if (setArray[i].equals(obj)) return setArray[i];//如果数组中某处值与指定元素相同则返回数组中的值
        return null;//默认返回空值
    }

    /**
     * 返回集合的长度
     *
     * @return 集合的长度
     */
    public int size() {
        return length;
    }

    /**
     * 判断集合是否为空
     *
     * @return 集合是否为空
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * 输出集合中的所有元素
     */
    public void output() {
        System.out.println("开始输出集合中元素：-----------");
        for (int i = 0; i < length; i++)
            System.out.println(setArray[i]);
    }

    /**
     * 求两个集合的并集
     *
     * @param set 参数集合
     * @return 两个集合的并集
     */
    public Set union(Set set) {
        sequenceSet setTemp = new sequenceSet(setArray.length);//使用一个缓存集合来存放结果
        System.arraycopy(setArray, 0, setTemp.setArray, 0, setArray.length);//将当前集合的元素全部复制进新集合
        setTemp.length = length;//设置新集合的长度
        sequenceSet dset = (sequenceSet) set;//将参数集合转换为顺序集合
        for (int i = 0; i < dset.length; i++)//向新集合中插入参数集合的元素
            setTemp.add(dset.setArray[i]);
        return setTemp;
    }

    /**
     * 求两个集合的交集
     *
     * @param set 参数集合
     * @return 两个集合的交集
     */
    public Set insertsection(Set set) {
        sequenceSet dset = (sequenceSet) set;//将参数集合转换为顺序集合
        int c = Math.min(dset.length, this.length);//将当前集合和参数集合的最小长度作为新集合长度
        sequenceSet setTemp = new sequenceSet(c);//使用一个缓存集合来存放结果
        for (int i = 0; i < setTemp.length; i++)//遍历
            if (this.contains(dset.setArray[i]))//若当前集合包含参数集合的某个值
                setTemp.add(dset.setArray[i]);//将该值放入新集合
        return setTemp;//返回新集合
    }

    /**
     * 清除集合的所有元素
     */
    public void clear() {
        length = 0;
    }

    /**
     * 测试函数，测试算法效果
     */
    public static void main(String[] args) {
        sequenceSet aSet = new sequenceSet();//新建一个线性集合
        aSet.output();

        aSet.add("第一个元素");//尝试添加元素
        aSet.add("第二个元素");
        aSet.add("第三个元素");
        aSet.add("第四个元素");
        aSet.output();//查看添加元素后的集合
        System.out.println("此时集合的长度为:" + aSet.size());

        aSet.remove("第三个元素");//尝试删除一个元素
        aSet.output();//查看删除元素后的集合
        System.out.println("此时集合的长度为:" + aSet.size());

        System.out.println("集合中是否包含'第三个元素':" + aSet.contains("第三个元素"));//测试contains方法

        aSet.add("第五个元素");//再次尝试添加一个元素
        aSet.output();//查看添加元素后的集合
        System.out.println("此时集合的长度为:" + aSet.size());//测试size方法

        System.out.println("集合中是否包含'第五个元素':" + aSet.find("第五个元素"));//测试find方法

        System.out.println("此时集合是否为空:" + aSet.isEmpty());

        System.out.println("集合中第三个元素的值为:" + aSet.value(3));//测试value方法


    }


}
