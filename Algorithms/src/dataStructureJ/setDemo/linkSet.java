package dataStructureJ.setDemo;

/**
 * 链接集合类的定义及实现
 */
public class linkSet implements Set {

    public int length;// 长度
    public Node head;// 头结点

    /**
     * 初始化集合为空
     */
    public linkSet() {
        length = 0;// 长度置为0
        head = new Node(null);// 建立由head指向的附加头结点
    }

    /**
     * 向集合中插入一个元素
     *
     * @param obj 插入的元素
     * @return 插入元素是否成功 首先通过结点来遍历是否存在该元素 若不存在，在集合末尾插入新元素，将集合长度增加1，返回真值 默认返回假值
     */

    public boolean add(Object obj) {
        Node temp = head;// 定义一个临时结点
        while (temp.next != null) {// 遍历集合
            if (temp.next.element.equals(obj))
                return false;// 若临时结点的下一个结点的值与欲添加的元素值相同，则返回false
            else
                temp = temp.next;// 否则以临时结点的下一结点代替临时结点
        }
        temp.next = new Node(obj, null);// 将待插入元素的值赋予元素末尾的结点
        length++;// 将集合长度加1
        return true;// 返回真值表示操作成功
    }

    /**
     * 从集合中删除一个元素
     *
     * @param obj 待删除的元素
     * @return 删除元素是否成功 通过结点来遍历集合，若发现某一结点的值与元素的值相同，则结束遍历，否则继续遍历
     * 将该结点上一结点的指针域指向该结点的下一结点，删除该结点 将集合长度减1，并返回真值表示操作成功 默认返回假值表示操作失败
     */
    public boolean remove(Object obj) {
        Node temp = head;// 定义一个临时结点
        while (temp.next != null) {// 遍历集合
            if (temp.next.element.equals(obj))
                break;// 若发现集合某处结点的值与元素相同，则结束遍历
            else
                temp = temp.next;// 否则继续遍历
        }
        if (temp.next != null) {// 条件成立时，临时结点的指针域指向待删除的结点
            temp.next = temp.next.next;// 将临时结点的指针域指向待删除结点的下一结点
            length--;// 集合长度减1
            return true;// 返回真值表示操作成功
        } else
            return false;// 条件不成立时返回假值表示操作失败
    }

    /**
     * 返回集合中第i个元素的值
     *
     * @param i 指定的位置
     * @return 返回的值 首先检查指定位置是否合法，若不合法则停止运行 若指定位置合法，遍历集合到指定的位置，返回指定位置的值
     * 默认返回null表示查找失败
     */
    public Object value(int i) {
        if (i <= 0 || i > length)
            System.exit(1);// 检查位置是否合法
        int c = 0;
        Node temp = head;
        while (temp.next != null) {
            if (c == i)
                break;
            else {
                c++;
                temp = temp.next;
            }
        }
        return temp.element;
    }

    public boolean contains(Object obj) {
        Node temp = head;// 定义临时结点
        while (temp.next != null) {// 遍历集合
            if (temp.element.equals(obj))
                return true;// 如果临时结点的值与查询的值相同，则返回真值表示包含此元素
            else
                temp = temp.next;// 否则将临时结点向后指
        }
        return false;// 默认返回假值表示查找失败
    }

    /**
     * 从集合中按值查找元素
     *
     * @param obj 欲查找的值
     * @return 查找的元素 默认返回null表示未查找到元素
     */
    public Object find(Object obj) {
        Node temp = head;// 定义临时结点指向头结点
        while (temp != null) {// 遍历集合
            if (temp.element.equals(obj)) {// 如果临时结点的值与欲查找的值相同
                return temp.element;// 则返回欲查找的值
            } else// 如果临时结点的值与欲查找的值不同
                temp = temp.next;// 否则将临时结点向后指
        }
        return null;// 默认返回null表示未查找到元素
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
     * 求两个集合的并集
     *
     * @param set 参数集合
     * @return 两个集合的并集
     */
    public Set union(Set set) {
        linkSet setTemp = new linkSet();//定义缓存集合存储两个集合的并集
        Node p = head.next;//p指向表头结点
        Node q = setTemp.head;//q指向结果集合的表头
        while (p != null) {
            Node r = new Node(p.element, null);//根据p的值建立新结点
            p = p.next;//使p指向下一个待复制的结点
            q.next = r;//将r结点链接到结果集合的结尾
            q = r;//使q指向结果集合的表尾结点
        }
        setTemp.length = length;
        linkSet dset = (linkSet) set;//转型
        p = dset.head;
        while (p != null) {
            setTemp.add(p.element);
            p = p.next;
        }
        return setTemp;
    }

    /**
     * 求两个集合的交集
     */
    public Set insertsection(Set set) {
        linkSet setTemp = new linkSet();//定义缓存集合存储两个集合的并集
        linkSet dset = (linkSet) set;
        Node p = dset.head.next;//p初始指向参数链表中的表头结点
        Node q = setTemp.head;//q初始指向结果集合的附加头结点
        while (p != null) {
            Object x = p.element;//取出结点的值
            boolean b = contains(x);//从当前集合中查找值为x的结点是否存在
            if (b) {//当该结点在两个集合中都存在时
                q.next = new Node(x, null);//将该结点插入结果集合
                q = q.next;//使q指向新的结果集合的表尾结点
                setTemp.length++;
            }
            p = p.next;
        }
        return setTemp;
    }


    /**
     * 输出集合所有元素
     */
    public void output() {
        Node temp = head;//定义一个临时结点
        while (temp != null) {//当临时结点不为空，即临时结点仍指向集合中内容时，
            System.out.println(temp.element);//输出此时临时结点对应的值
            temp = temp.next;//将临时结点向后走
        }
    }

    /**
     * 清除集合中的所有元素
     */
    public void clear() {
        length = 0;
        head.next = null;
    }

    /**
     * 测试函数，测试算法效果
     */
    public static void main(String[] args) {
        linkSet aSet = new linkSet();
        aSet.add("a");
        aSet.add("b");
        aSet.add("c");
        aSet.add("d");
        System.out.println(aSet.size());
        aSet.remove("c");
        aSet.output();
        System.out.println(aSet.size());
        aSet.remove("d");
        aSet.output();
        System.out.println(aSet.size());

    }


}
