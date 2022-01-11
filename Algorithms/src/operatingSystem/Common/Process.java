package operatingSystem.Common;

/*
 * 类名 : 作业/进程
 * 相关公式 :
 * 周转时间 = 完成时间 - 到达时间
 * 带权周转时间 = 周转时间 / 服务时间
 *
 */
public class Process {

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
