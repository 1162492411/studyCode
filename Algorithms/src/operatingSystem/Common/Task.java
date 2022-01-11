package operatingSystem.Common;

/**
 * Created by Mo on 2017/4/27.
 */
public class Task {

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
