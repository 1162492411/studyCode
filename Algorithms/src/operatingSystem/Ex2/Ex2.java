package operatingSystem.Ex2;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 名称 : 动态高优先权调度算法
 * 伪代码 :
 * start
 * while (就绪队列非空)
 * 每运行一时间单位
 * 将就绪队列中的作业按优先权降序排序
 * 取出就绪队列队首的作业并执行一个时间单位
 * 更新所有作业优先权
 * 如果作业执行完毕
 * 将作业移除出就绪队列并更新作业信息
 * end
 * 本类使用到了Java8的lambda，需要JDK8才能运行
 **/
public class Ex2 {
    private LinkedList<PriorityProcess> all;//所有元素
    private LinkedList<PriorityProcess> waiting;//就绪队列
    private LinkedList<PriorityProcess> finished;//已完成作业队列
    private float currentTime;//模拟时间
    private int stepPriority;//每次更新的优先权

    //构造器，进行变量初始化
    public Ex2() {
        all = new LinkedList<>();
        waiting = new LinkedList<>();
        finished = new LinkedList<>();
        currentTime = 0;
        stepPriority = 1;
    }

    //核心运行函数
    public void start() {
        virtualTime();//根据当前时间动态添加作业
        while (hasRemains()) {//当就绪队列非空
            currentTime += 1;//模拟运行一个时间单位
            sort();//将就绪队列中的作业按优先权降序排列
            runFirst();//运行就绪队列中的第一个作业，即优先权最高的作业
            updatePriority();//更新就绪队列中的作业优先权
            updateStatus();//更新就绪队列中的作业的状态
            printCurrentProcess();
            virtualTime();//根据当前时间动态添加作业
        }
        printResult();
    }

    //就绪队列中是否还存在进程
    public Boolean hasRemains() {
        return !waiting.isEmpty();
    }

    //模拟时间流逝来添加进程
    private void virtualTime() {
        Iterator iterator = all.iterator();
        while (iterator.hasNext()) {//遍历就绪队列是否有新进程到达
            PriorityProcess temp = (PriorityProcess) iterator.next();
            if (currentTime == temp.getArriveTime()) {
                waiting.add(temp);//添加新进程
            }
        }
    }

    //将就绪队列中的作业按优先权降序排列
    public void sort() {
        waiting.sort((o1, o2) -> o2.getCurrentPriority() - o1.getCurrentPriority());
    }

    //运行就绪队列中的第一个作业，即优先权最高的作业
    public void runFirst() {
        PriorityProcess pp = waiting.getFirst();
        pp.setHasServicedTime(pp.getHasServicedTime() + 1);
    }

    //更新就绪队列中的作业优先权
    public void updatePriority() {
        Iterator iterator = waiting.iterator();
        while (iterator.hasNext()) {
            PriorityProcess pp = (PriorityProcess) iterator.next();
            pp.setCurrentPriority(pp.getCurrentPriority() + stepPriority);
        }
        PriorityProcess first = waiting.getFirst();
        first.setCurrentPriority(first.getCurrentPriority() - 2 * stepPriority);
    }

    //更新就绪队列中的作业的状态
    public void updateStatus() {
        PriorityProcess pp = waiting.getFirst();
        if (pp.getHasServicedTime() == pp.getServiceTime()) {
            pp.setFinishTime(currentTime);
            pp.setTurnOverTime();
            pp.setWeightTurnOverTime();
            pp.setFinished();
            waiting.removeFirst();//将该作业移除出就绪队列
            finished.add(pp);//将已执行作业加入已执行作业队列
        }
    }

    //添加作业
    public void add(PriorityProcess pp) {
        all.add(pp);
    }

    //打印函数--打印当前时刻就绪队列作业信息
    private void printCurrentProcess() {
        System.out.println("【当前时刻】" + currentTime + "\n【就绪队列】");
        Iterator iterator = waiting.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("--------------------------");
    }

    //打印函数--打印最终汇总信息
    private void printResult() {
        System.out.println("【汇总信息】");
        Iterator iterator = finished.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    public static void main(String[] args) {
        Ex2 Ex2 = new Ex2();
        Ex2.add(new PriorityProcess("A", 0, 3, 5));
        Ex2.add(new PriorityProcess("B", 0, 5, 10));
        Ex2.add(new PriorityProcess("C", 0, 2, 0));
        Ex2.start();
    }

}

class PriorityProcess extends Process {

    private int originalPriority;//初始优先权

    private int currentPriority;//当前优先权

    public PriorityProcess() {

    }

    public PriorityProcess(String name, float arriveTime, float serviceTime, int originalPriority) {
        super(name, arriveTime, serviceTime);
        this.originalPriority = originalPriority;
        this.currentPriority = originalPriority;
    }


    public int getOriginalPriority() {
        return originalPriority;
    }

    public void setOriginalPriority(int originalPriority) {
        this.originalPriority = originalPriority;
    }

    public int getCurrentPriority() {
        return currentPriority;
    }

    public void setCurrentPriority(int currentPriority) {
        this.currentPriority = currentPriority;
    }

    @Override
    public String toString() {

        return super.toString() + "{" +
                "originalPriority=" + originalPriority +
                ", currentPriority=" + currentPriority +
                '}';
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
