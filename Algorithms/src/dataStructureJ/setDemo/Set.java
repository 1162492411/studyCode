package dataStructureJ.setDemo;

/**
 * 集合接口
 *
 * @author Mo
 */
public interface Set {
    boolean add(Object obj);//向集合中插入一个元素

    boolean remove(Object obj);//从集合中删除一个元素

    boolean contains(Object obj);//判断一个元素是否属于集合

    Object value(int i);//返回集合中第i个元素的值

    Object find(Object obj);//从集合中按值查找元素并返回

    int size();//求出集合中元素的个数

    boolean isEmpty();//判断集合是否为空

    void output();//输出集合中的所有元素

    Set union(Set set);//求两个集合的并集并返回

    Set insertsection(Set set);//求两个集合的交集并返回

    void clear();//清除集合中的所有元素
}
