package dataStructureJ.graphDemo;

/**
 * 边结点的定义
 *
 * @author Mo
 */
public class EdgeNode {
    int adjVex;//邻接点
    int weight;//边的权值
    EdgeNode next;//指向下一个边结点的链接

    /**
     * 无向图中的边结点的构造器
     *
     * @param adjVex 邻接点
     * @param next   下一个边结点
     */
    public EdgeNode(int adjVex, EdgeNode next) {
        this.adjVex = adjVex;
        this.next = next;
        weight = 1;
    }

    /**
     * 有向图的边结点的构造器
     *
     * @param adjVex 邻接点
     * @param weight 权值
     * @param next   下一个边结点
     */
    public EdgeNode(int adjVex, int weight, EdgeNode next) {
        this.adjVex = adjVex;
        this.weight = weight;
        this.next = next;
    }
}
