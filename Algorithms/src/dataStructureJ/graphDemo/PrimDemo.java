package dataStructureJ.graphDemo;

/**
 * prim算法
 *
 * @author Mo
 */
public class PrimDemo {
    // prim
    public static void prim(AdjacencyGraph graph, EdgeElement[] ed) {
        if (graph.graphType() != 1)
            System.exit(-1); // 不是连通网，无法生成树
        int n = graph.vertices();// 存储顶点个数
        int[][] a = graph.getArray();// 取出邻接矩阵的引用
        for (int i = 0; i < n - 1; i++) // 初始化
            ed[i] = new EdgeElement(0, i + 1, a[0][i + 1]);
        for (int k = 1; k < n; k++) {
            // 进行n-1'次循环，每次求出最小生成树中的第k条边
            // 从ed[k-1] ~ ed[n-2]中查找最短边ed[m]
            int min = graph.getMaxValue();
            int j, m = k - 1;
            for (j = k - 1; j < n - 1; j++)
                if (ed[j].weight < min) {
                    min = ed[j].weight;
                    m = j;
                }
            // 将最短边对调到下标为k-1的元素位置
            EdgeElement temp = ed[k - 1];
            ed[k - 1] = ed[m];
            ed[m] = temp;
            // 把新并入最小生成树T中的顶点序号赋给j
            j = ed[k - 1].endVex;
            // 修改LW中的有关边，使T中到T外的每个顶点保持一条目前最短的边
            for (int i = k; i < n - 1; i++) {
                int t = ed[i].endVex, w = a[j][t];
                if (w < ed[i].weight) {
                    ed[i].weight = w;
                    ed[i].fromVex = j;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n, t;
        AdjacencyGraph aGraph = new AdjacencyGraph(7, 1);
        int[][] a = {{0, 1, 8}, {0, 3, 5}, {1, 2, 12}, {1, 3, 3}, {1, 4, 10}, {2, 4, 6}, {2, 5, 2},
                {3, 5, 7}, {3, 6, 15}, {4, 5, 9}};
        EdgeElement[] dd = new EdgeElement[a.length];
        for (int i = 0; i < a.length; i++)
            dd[i] = new EdgeElement(a[i][0], a[i][1], a[i][2]);
        aGraph.createGraph(dd);
        // 创建保存图的最小生成树的边集对象ed
        EdgeElement[] ed = new EdgeElement[aGraph.vertices() - 1];
        // 调用prim算法
        prim(aGraph, ed);
        System.out.println("得到的最小生成树的边集为：");
        for (int i = 0; i < ed.length; i++) {
            System.out.printf("(" + ed[i].fromVex + "," + ed[i].endVex + ")" + ed[i].weight + " ");
        }
    }
}
