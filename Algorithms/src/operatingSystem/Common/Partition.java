package operatingSystem.Common;

/**
 * 名称：分区
 * 作用：用于动态分区分配算法，即实验三
 * Created by Mo on 2017/4/27.
 */
public class Partition {

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
