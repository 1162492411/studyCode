package dataStructureJ.graphDemo;

import java.util.LinkedList;

/**
 * 图的邻接矩阵存储类
 *
 * @author Mo
 */
public class AdjacencyGraph implements Graph {
    private final int MaxValue = 100;// 不存在的边所使用的权值
    private int n;// 图的顶点数
    private int e;// 图的边数
    private int type;// 图的类型
    private int a[][];// 图的邻接矩阵
    // 本类特有，返回最大值常量

    public int getMaxValue() {
        return MaxValue;
    }

    // 本类特有，返回邻接矩阵的浅表引用
    public int[][] getArray() {
        return a;
    }

    // 构造函数
    public AdjacencyGraph(int n, int t) {
        if (n < 1 || t < 0 || t > 3)
            System.exit(-1);
        this.n = n;
        e = 0;
        type = t;
        a = new int[n][n];
        for (int i = 0; i < n; i++) {// 初始化数组中每个元素的值
            for (int j = 0; j < n; j++) {
                if (i == j)
                    a[i][j] = 0;// 对角线元素初始化为0
                else if (type == 0 || type == 2)
                    a[i][j] = 0;// 无权图的元素初始化为0
                else
                    a[i][j] = MaxValue;// 对带权图的元素初始化为0
            }
        }
    }

    // 根据一个图的边集数组建立按邻接矩阵存储的图
    public void createGraph(EdgeElement[] d) {
        int i;
        for (i = 0; i < d.length; i++) {
            if (d[i] == null)
                break;
            int v1, v2;// 定义两个变量存储边的两个顶点
            v1 = d[i].fromVex;
            v2 = d[i].endVex;
            if (v1 < 0 || v1 > n - 1 || v2 < 0 || v2 > n - 1 || v1 == v2)
                System.exit(-1);
            if (type == 0)
                a[v1][v2] = a[v2][v1] = 1;// 无向无权图
            else if (type == 1)
                a[v1][v2] = a[v2][v1] = d[i].weight;// 无向有权图
            else if (type == 2)
                a[v1][v2] = 1;// 有向无权图
            else
                a[v1][v2] = d[i].weight;// 有向有权图
        }
        e = i;// 将图中的边数修改为i的值
    }

    // 返回图的类型
    public int graphType() {
        return type;
    }

    // 返回图的顶点数
    public int vertices() {
        return n;
    }

    // 返回图的边数
    public int edges() {
        return e;
    }

    @Override
    public boolean find(int i, int j) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void putEdge(EdgeElement theEdge) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeEdge(int i, int j) {
        // TODO Auto-generated method stub

    }

    @Override
    public int degree(int i) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int inDegree(int i) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int outDegree(int i) {
        // TODO Auto-generated method stub
        return 0;
    }

    // 输出用邻接矩阵表示一个图所对应的顶点集和边集
    public void output() {
        int i, j;
        System.out.printf("V = {");// 开始输出顶点集
        for (i = 0; i < n - 1; i++)
            System.out.printf(i + ",");
        System.out.println((n - 1) + "}");// 输出顶点集结束

        System.out.printf("E = {");// 开始输出边集
        if (type == 0 || type == 2) {// 处理无权图
            for (i = 0; i < n; i++)
                for (j = 0; j < n; j++)
                    if (a[i][j] != 0 && a[i][j] != MaxValue)
                        if (type == 0) {// 处理无向无权图
                            if (i < j)
                                System.out.printf("(" + i + "," + j + "),");
                        } else if (type == 2) // 处理有向无权图
                            System.out.printf("<" + i + "," + j + ">,");
        } else if (type == 1 || type == 3) {// 处理有权图
            for (i = 0; i < n; i++)
                for (j = 0; j < n; j++)
                    if (a[i][j] != 0 && a[i][j] != MaxValue)
                        if (type == 1) {// 处理无向有权图
                            if (i < j)
                                System.out.printf("(" + i + "," + j + ")" + a[i][j] + ",");
                        } else if (type == 3)
                            System.out.printf("<" + i + "," + j + ">" + a[i][j] + ",");
        }
        System.out.println("}");// 输出边集的最后一个花括号
    }

    @Override
    public void outputLink() {
    }

    //输出图的邻接矩阵
    public void outputAdjacency() {
        System.out.println("输出该图的邻接矩阵：");
        for (int i = 0; i < a.length; i++) {
            System.out.printf("[");
            for (int j = 0; j < a.length; j++) {
                if (a[i][j] > 0)//大于0的输出1
                    System.out.printf("%4d", 1);
                else//等于0的按原值输出
                    System.out.printf("%4d", 0);
//				System.out.printf("%4d",a[i][j]);
            }
            System.out.println("]");
        }
    }

    // 深度优先搜索
    public void depthFirstSearch(int v) {
        boolean[] visited = new boolean[n];// 定义辅助数组
        for (int i = 0; i < n; i++)
            visited[i] = false;// 每个元素被初始化为false
        dfs(v, visited);// 深度遍历
        System.out.println();// 输出空行
    }

    // 从初始点i开始深度优先搜索由邻接矩阵表示的图
    private void dfs(int i, boolean[] visited) {
        System.out.printf(i + " ");// 假定访问顶点vi以输出
        visited[i] = true;// 标记改点已被访问
        for (int j = 0; j < n; j++) {// 依次搜索该点的所有邻接点
            if (a[i][j] != 0 && a[i][j] != MaxValue && !visited[j])// 若该点的一个有效邻接点未被访问过
                dfs(j, visited);// 则从该点开始，递归访问
        }
    }

    // 广度优先搜索
    public void breadthFirstSearch(int v) {
        boolean[] visited = new boolean[n];// 定义辅助数组
        for (int i = 0; i < n; i++)
            visited[i] = false;// 每个元素被初始化为false
        bfs(v, visited);// 深度遍历
        System.out.println();// 输出空行
    }

    // 从初始点i开始广度优先搜索由邻接矩阵表示的图
    private void bfs(int i, boolean[] visited) {
        // sequenceQueue aQueue = new sequenceQueue();//创建一个空队列
        LinkedList<Integer> aList = new LinkedList<Integer>();// 将linkedList当做队使用
        System.out.printf(i + " ");// 访问初始点vi，以输出序号代之
        visited[i] = true;// 标记该点已被访问
        aList.add(i);// 将已访问过的初始点序号入队
        while (!aList.isEmpty()) {
            int k = aList.removeFirst();// 删除队首元素
            for (int j = 0; j < n; j++) {
                if (a[k][j] != 0 && a[k][j] != MaxValue && !visited[j]) {
                    System.out.printf(j + " ");// 访问顶点vj，以输出序号代之
                    visited[j] = true;// 标记该点已被访问
                    aList.add(j);// 将访问过的序号入队
                }
            }
        }
    }


    // 测试函数
    public static void main(String[] args) {
        // 定义一个5个结点的有向有权图
        AdjacencyGraph aGraph = new AdjacencyGraph(5, 3);
        // 定义其边集数组
        int[][] a = {{0, 1, 2}, {0, 2, 3}, {0, 3, 8}, {1, 3, 12}, {2, 0, 6}, {2, 3, 6}, {2, 4, 1},
                {3, 4, 4}};
        EdgeElement[] dd = new EdgeElement[a.length];
        for (int i = 0; i < a.length; i++) {
            dd[i] = new EdgeElement(a[i][0], a[i][1], a[i][2]);
        }
        aGraph.createGraph(dd);// 根据边集数组创建图
        aGraph.output();// 输出图
//		System.out.println("顶点0的度、入度、出度分别为：");// 求图的度、入度、出度
//		System.out.println(aGraph.degree(0) + " " + aGraph.inDegree(0) + " " + aGraph.outDegree(0));
//		// 从0开始深度/广度优先遍历图
//		System.out.println("从0开始深度优先遍历：");
//		aGraph.depthFirstSearch(0);
//		System.out.println("从0开始广度优先遍历：");
//		aGraph.breadthFirstSearch(0);
        aGraph.outputAdjacency();
    }


}
