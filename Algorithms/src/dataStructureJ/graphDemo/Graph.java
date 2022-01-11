package dataStructureJ.graphDemo;

/**
 * 图的抽象接口
 *
 * @author Mo
 */
public interface Graph {
    void createGraph(EdgeElement[] edges);//根据边集数组建立一个图

    int graphType();//返回图的类型

    int vertices();//返回图的顶点数

    int edges();//返回图的边数

    boolean find(int i, int j);//从图中查找一条边(i,j)是否存在

    void putEdge(EdgeElement theEdge);//向图中加入一条边

    void removeEdge(int i, int j);//从图中删除一条边(i,j)

    int degree(int i);//返回顶点i的度

    int inDegree(int i);//返回顶点i的入度

    int outDegree(int i);//返回顶点i的出度

    void output();//以图的顶点集和边集的形式输出一个图

    void outputLink();//输出图的邻接表

    void outputAdjacency();//输出图的邻接矩阵

    void depthFirstSearch(int v);//从顶点v开始深度优先搜索遍历图

    void breadthFirstSearch(int v);//从顶点v开始广度优先搜索遍历图
}
