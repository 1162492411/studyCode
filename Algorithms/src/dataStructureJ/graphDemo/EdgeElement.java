package dataStructureJ.graphDemo;

/**
 * 边集数组的元素定义
 *
 * @author Mo
 */
public class EdgeElement {
    int fromVex;//边的起点域
    int endVex;//边的终点域
    int weight;//边的权值域,无向图的边权值固定为1

    /**
     * 无向图的边的构造器
     *
     * @param fromVex 边的起点
     * @param endVex  边的终点
     */
    public EdgeElement(int fromVex, int endVex) {
        this.fromVex = fromVex;
        this.endVex = endVex;
        weight = 1;
    }

    /**
     * 有向图的边的构造器
     *
     * @param fromVex 边的起点
     * @param endVex  边的终点
     * @param weight  边的权值
     */
    public EdgeElement(int fromVex, int endVex, int weight) {
        this.fromVex = fromVex;
        this.endVex = endVex;
        this.weight = weight;
    }
}
