package operatingSystem.Common;

public class PriorityProcess extends Process {

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
