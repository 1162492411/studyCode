package operatingSystem.Ex1;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 名称 : 短作业调度算法
 * 伪代码 :
 * start
 * while (就绪队列非空)
 * 将就绪队列中的作业按服务时间升序排序
 * 取出就绪队列队首的作业并执行
 * 更新就绪队列的作业状态
 * 根据当前时间动态添加作业
 * end
 * 本类使用到了Java8的lambda，需要JDK8才能运行
 **/

public class Ex1 {
    private LinkedList<Process> all;//所有元素
    private LinkedList<Process> waiting;//就绪队列
    private LinkedList<Process> finished;//已完成作业队列
    private float currentTime;//模拟时间

    //构造器，进行一些变量的初始化
    public Ex1() {
        all = new LinkedList<>();
        waiting = new LinkedList<>();
        finished = new LinkedList<>();
        currentTime = 0;
    }

    //Ex1算法执行
    private void start() {
        virtualTime();//根据当前时间动态添加作业
        while (hasRemains()) {//当就绪队列非空
            currentTime += 1;//模拟运行一个时间单位
            sort();//排序
            runFirst();//运行就绪队列中的第一个作业
            updateStatus();//更新就绪队列中的作业的状态
            printCurrentProcess();
            virtualTime();//根据当前时间动态添加作业
        }
        printResult();
    }

    //向队列中添加新作业
    private void add(Process p) {
        all.add(p);
    }

    //就绪队列中是否存在剩余作业
    private boolean hasRemains() {
        return !waiting.isEmpty();
    }

    //将就绪队列中的所有作业按预计执行时间升序排序
    private void sort() {
        Collections.sort(waiting, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                float r1 = o1.getServiceTime() - o1.getHasServicedTime();
                float r2 = o2.getServiceTime() - o2.getHasServicedTime();
                return (int) (r1 - r2);
            }
        });
    }

    //模拟时间流逝来添加进程
    private void virtualTime() {
        Iterator iterator = all.iterator();
        while (iterator.hasNext()) {//遍历就绪队列是否有新进程到达
            Process temp = (Process) iterator.next();
            if (currentTime == temp.getArriveTime()) {
                waiting.add(temp);//添加新进程
            }
        }
    }

    //执行就绪队列队首的作业
    private void runFirst() {
        Process process = waiting.getFirst();//取出就绪队列队首作业
        waiting.getFirst().setHasServicedTime(process.getHasServicedTime() + 1);
    }

    //更新就绪队列作业信息
    private void updateStatus() {
        Process process = waiting.getFirst();//取出就绪队列队首作业
        if (process.getHasServicedTime() == process.getServiceTime()) {
            process.setFinishTime(currentTime);
            process.setTurnOverTime();
            process.setWeightTurnOverTime();
            process.setFinished();
            waiting.removeFirst();//将该作业移除出就绪队列
            finished.add(process);//将已执行作业加入已执行作业队列
        }
    }

    //打印当前信息
    private void printCurrentProcess() {
        System.out.println("【当前时刻】" + currentTime + "\n【就绪队列】");
        Iterator iterator = waiting.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("--------------------------");
    }

    //打印最终信息
    private void printResult() {
        System.out.println("【最终信息】");
        Iterator iterator = finished.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }

    //测试函数，测试算法执行过程及结果
    public static void main(String[] args) {
        Ex1 Ex1 = new Ex1();
        Ex1.add(new Process("1", 0, 4));
        Ex1.add(new Process("2", 1, 3));
        Ex1.add(new Process("3", 2, 5));
        Ex1.add(new Process("4", 3, 2));
        Ex1.add(new Process("5", 4, 4));
        Ex1.start();
    }

}

class Process {

    private String name;//名称

    private float arriveTime;//到达时间

    private float serviceTime;//服务时间

    private float hasServicedTime;//已服务时间

    private float finishTime;//完成时间

    private float turnOverTime;//周转时间

    private float weightTurnOverTime;//带权周转时间

    private boolean finished;//是否完成

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(float arriveTime) {
        this.arriveTime = arriveTime;
    }

    public float getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(float serviceTime) {
        this.serviceTime = serviceTime;
    }

    public float getHasServicedTime() {
        return hasServicedTime;
    }

    public void setHasServicedTime(float hasServicedTime) {
        this.hasServicedTime = hasServicedTime;
    }

    public float getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(float finishTime) {
        this.finishTime = finishTime;
    }

    public float getTurnOverTime() {
        return turnOverTime;
    }

    public void setTurnOverTime() {
        turnOverTime = finishTime - arriveTime;
    }

    public float getWeightTurnOverTime() {
        return weightTurnOverTime;
    }

    public void setWeightTurnOverTime() {
        weightTurnOverTime = turnOverTime / serviceTime;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished() {
        this.finished = true;
    }

    public Process() {
        super();
    }

    public Process(String name, float arriveTime, float serviceTime) {
        super();
        this.name = name;
        this.arriveTime = arriveTime;
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return "[name=" + name + ", arrive=" + arriveTime + ", service=" + serviceTime
                + ", finish=" + finishTime + ", turnOver="
                + turnOverTime + ", weightTurnOver=" + weightTurnOverTime + "]";
    }


}
