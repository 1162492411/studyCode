package operatingSystem.Ex3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 名称：动态分区分配之适应算法
 * 本类使用到了Java8的lambda，需要JDK8才能运行
 */
public class Ex3 {
    private ArrayList<Partition> partitions;//总空间

    //初始化数据--分区大小
    public void init() {
        Scanner scanner = new Scanner(System.in);//提示用户输入初始化分区大小
        System.out.println("请输入初始分区大小:");
        Integer size = scanner.nextInt();
        partitions = new ArrayList<>();//初始化分区并加入链表
        partitions.add(new Partition(0, size - 1, size, null));
    }

    //添加作业
    public void add(Task task) {
        if (-1 == indexOf(task.getName()))//当不存在与待添加作业同名的作业时
            allocation(task);//为该作业分配空间
    }

    //检测指定作业名的作业的下标
    private int indexOf(String name) {
        for (int i = 0; i < partitions.size(); i++) {
            if (name.equals(partitions.get(i).getWorkingName())) return i;
        }
        return -1;//默认返回-1表示不存在
    }

    //分配空间
    private boolean allocation(Task task) {
        for (int i = 0; i < partitions.size(); i++) {
            Partition tempPartition = partitions.get(i);
            if (null == tempPartition.getWorkingName()) {//对于空闲区
                if (tempPartition.getLength() > task.getSize()) {//若空闲区有足够空间
                    partitions.add(new Partition(tempPartition.getStart(), tempPartition.getStart() + task.getSize() - 1, task.getSize(), task.getName()));
                    tempPartition.setStart(tempPartition.getStart() + task.getSize());//更新分区信息
                    tempPartition.setLength(tempPartition.getLength() - task.getSize());
                    partitions.sort((o1, o2) -> o1.getStart() - o2.getStart());
                    return true;
                }
            }
        }
        System.out.println("剩余空间不足！");
        return false;
    }

    //回收作业
    private void remove(String name) {
        int position = indexOf(name);//查找指定名称的作业在分区中的位置
        if (-1 != position)//当作业存在时
            recycle(position);//回收空间
    }

    //回收指定位置的分区的空间
    private void recycle(int position) {
        Partition partition = partitions.get(position);
        String name = partition.getWorkingName();
        partition.setWorkingName(null);//清空指定位置分区的作业名
        System.out.println("已回收" + name);
        merge(position);//将指定位置前后分区中的空闲分区个病
    }

    //合并空间判断
    private void merge(int position) {
        //若指定分区为第一个分区且下一分区也为空闲区合两分区
        if (0 == position && null == partitions.get(position + 1).getWorkingName())
            mergeAction(position, position + 1);
        //若指定分区为最后一个分区且上一分区也为空闲区合并两分区
        if (partitions.size() - 1 == position && null == partitions.get(position - 1).getWorkingName())
            mergeAction(position - 1, position);
        //当指定分区是第二分区至倒数第二分区时
        if (position > 0 && position < partitions.size()) {
            //当该分区的下一分区是空闲区时合并两分区
            if (null == partitions.get(position + 1).getWorkingName())
                mergeAction(position, position + 1);
            //当该分区的上一分区是空闲区时合并两分区
            if (null == partitions.get(position - 1).getWorkingName())
                mergeAction(position - 1, position);
        }
    }

    //进行合并空间的操作
    private void mergeAction(int i, int j) {
        Partition partition = partitions.get(i);
        Partition temp = partitions.get(j);
        partition.setEnd(temp.getEnd());
        partition.setLength(partition.getLength() + temp.getLength());
        partitions.remove(j);
        System.out.println("已合并" + partition.getStart() + "至" + partition.getEnd() + "的分区");
    }

    //打印当前总分区信息
    public void printCurrentInfo() {
        System.out.println("【当前分区信息】");
        for (int i = 0; i < partitions.size(); i++) {
            System.out.println(partitions.get(i));
        }
        System.out.println("------------------------------");
    }

    public static void main(String[] args) {
        Ex3 Ex3 = new Ex3();
        Ex3.init();
        Ex3.add(new Task("作业1", 20));
        Ex3.printCurrentInfo();
        Ex3.add(new Task("作业2", 10));
        Ex3.printCurrentInfo();
        Ex3.add(new Task("作业3", 10));
        Ex3.printCurrentInfo();
        Ex3.add(new Task("作业4", 20));
        Ex3.printCurrentInfo();
        Ex3.remove("作业1");
        Ex3.printCurrentInfo();
        Ex3.remove("作业3");
        Ex3.printCurrentInfo();
        Ex3.remove("作业2");
        Ex3.printCurrentInfo();
    }

}

class Partition {

    private int start;//分区的起始位置

    private int end;//分区的结束位置

    private int length;//分区的长度

    private String workingName;//分区工作的作业号

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getWorkingName() {
        return workingName;
    }

    public void setWorkingName(String workingName) {
        this.workingName = workingName;
    }

    public Partition() {
    }

    public Partition(int start, int end, int length, String workingName) {
        this.start = start;
        this.end = end;
        this.length = length;
        this.workingName = workingName;
    }

    @Override
    public String toString() {
        return "Partition{" +
                "start=" + start +
                ", end=" + end +
                ", length=" + length +
                ", workingName='" + workingName + '\'' +
                '}';
    }
}

class Task {

    private String name;//作业名

    private int size;//作业大小

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Task() {

    }

    public Task(String name, int size) {
        this.name = name;
        this.size = size;
    }
}

