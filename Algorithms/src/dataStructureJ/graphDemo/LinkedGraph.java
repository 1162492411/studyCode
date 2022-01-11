package dataStructureJ.graphDemo;

import queueDemo.sequenceQueue;

import java.util.Stack;

/**
 * 图的邻接表存储类
 *
 * @author Mo
 */
public class LinkedGraph implements Graph {
    private int n;// 图的顶点数
    private int e;// 图的边数
    private int type;// 图的类型
    private EdgeNode[] aEdgeNodes;// 图的邻接表

    /**
     * 返回邻接表的引用，即浅表副本
     *
     * @return
     */
    public EdgeNode[] getArray() {
        return aEdgeNodes;
    }

    /**
     * 构造器
     */
    public LinkedGraph(int n, int type) {
        if (n < 1 || type < 0 || type > 3)// 参数检查
            System.exit(1);
        e = 0;
        this.n = n;
        this.type = type;
        aEdgeNodes = new EdgeNode[n];// 为邻接表的表头分配空间
        for (int i = 0; i < aEdgeNodes.length; i++) {
            aEdgeNodes[i] = null;// 将每个向量值置空
        }
    }

    /**
     * 根据图的边集数组建立按邻接表存储的图
     */
    public void createGraph(EdgeElement[] edges) {
        int i;
        for (i = 0; i < edges.length; i++) {// 每次处理边集数组中的一条边
            if (edges[i] == null)
                break;// 当没有边时退出循环
            int fromvex, endvex;// 用两个变量保存两个顶点
            fromvex = edges[i].fromVex;
            endvex = edges[i].endVex;
            // 对顶点进行合法性检查
            if (fromvex < 0 || fromvex > n - 1 || endvex < 0 || endvex > n - 1 || fromvex == endvex)
                System.exit(1);
            // 创建不同类型的图
            switch (type) {
                case 0:// 无向无权图
                    // 向两个顶点邻接表的表头插入结点
                    aEdgeNodes[fromvex] = new EdgeNode(endvex, aEdgeNodes[fromvex]);
                    aEdgeNodes[endvex] = new EdgeNode(fromvex, aEdgeNodes[endvex]);
                    break;
                case 1:// 无向有权图
                    aEdgeNodes[fromvex] = new EdgeNode(endvex, edges[i].weight, aEdgeNodes[fromvex]);
                    aEdgeNodes[endvex] = new EdgeNode(fromvex, edges[i].weight, aEdgeNodes[endvex]);
                    break;
                case 2:// 有向无权图
                    aEdgeNodes[fromvex] = new EdgeNode(endvex, aEdgeNodes[fromvex]);
                    break;
                case 3:// 有向有权图
                    aEdgeNodes[fromvex] = new EdgeNode(endvex, edges[i].weight, aEdgeNodes[fromvex]);
                    break;
                default:
                    break;
            }
        }
        e = i;// 把图中的边数修改为i的值
    }

    /**
     * 返回图的类型
     */
    public int graphType() {
        return type;
    }

    /**
     * 返回图的顶点数
     */
    public int vertices() {
        return n;
    }

    /**
     * 返回图的边数
     */
    public int edges() {
        return e;
    }

    /**
     * 从图中查找一条边是否存在 解释：1）在邻接表存储的图中，需要扫描一条边的开始顶点所对应的邻接表，
     * 若发现一个边结点中邻接点的值为要查找的一条边的终止顶点，则表明存在这条边，返回true 否则当扫描完所有边结点后应返回false表示不存在
     */
    public boolean find(int i, int j) {
        // 参数检查
        if (i < 0 || i > n - 1 || j < 0 || j > n - 1 || i == j)
            System.exit(1);
        EdgeNode temp = aEdgeNodes[i];// 将顶点i邻接表的表头指针赋给临时结点
        while (temp != null) {// 扫描顶点i的整个邻接表
            if (temp.adjVex == j)
                return true;// 当查找到邻接点j则返回true表示查找成功
            temp = temp.next;// 使结点继续向后走
        }
        return false;// 当遍历顶点i的整个邻接表后仍然为发现邻接点j，则返回false表示未查找到
    }

    // 向图中加入一条边
    public void putEdge(EdgeElement theEdge) {

    }

    // 从图中删除一条边
    public void removeEdge(int i, int j) {
        // TODO Auto-generated method stub

    }

    /**
     * 返回顶点i的度
     */
    public int degree(int i) {
        if (i < 0 || i > n - 1)
            System.exit(1);// 参数检查
        int k = 0;// 使用k作为统计变量
        if (type == 0 || type == 1) {// 对无向图
            EdgeNode temp = aEdgeNodes[i];
            while (temp != null) {
                temp = temp.next;
                k++;
            }
            return k;
        } else// 对有向图进行处理
            return inDegree(i) + outDegree(i);
    }

    /**
     * 返回顶点i的入度
     */
    public int inDegree(int i) {
        if (i < 0 || i > n - 1)
            System.exit(1);// 参数检查
        if (type == 0 || type == 1) {// 对于无向图
            return -1;// 返回-1表示不存在
        }
        int k = 0;// 使用k作为计数器
        for (int j = 0; j < n; j++) {
            EdgeNode temp = aEdgeNodes[j];
            while (temp != null) {
                if (temp.adjVex == i) {
                    k++;
                }
                temp = temp.next;
            }
        }
        return k;
    }

    /**
     * 返回顶点i的出度
     */
    public int outDegree(int i) {
        if (i < 0 || i > n - 1)
            System.exit(1);// 参数检查
        if (type == 0 || type == 1)
            return -1;// 无向图返回-1表示不存在
        int k = 0;// 使用k作为计数器
        EdgeNode temp = aEdgeNodes[i];
        while (temp != null) {// 统计出邻接表中的边结点个数
            k++;
            temp = temp.next;
        }
        return k;
    }

    /**
     * 以图的顶点集和边集的形式输出一个图
     */
    public void output() {
        // 输出以邻接表表示一个图所对应的顶点集和边集
        int i, j;
        System.out.printf("顶点集 V = {");// 开始输出顶点集
        for (i = 0; i < n - 1; i++)
            System.out.printf(i + ", ");
        System.out.println(n - 1 + "}");// 结束输出顶点集
        System.out.printf("边集 E = {");// 开始输出边集
        for (i = 0; i < n; i++) {
            EdgeNode temp = aEdgeNodes[i];
            while (temp != null) {
                j = temp.adjVex;
                switch (type) {
                    case 0:// 无向无权图
                        if (i < j)
                            System.out.printf("(" + i + ", " + j + "), ");
                        break;
                    case 1:// 无向有权图
                        if (i < j)
                            System.out.printf("(" + i + ", " + j + ") " + temp.weight + ", ");
                        break;
                    case 2:// 有向无权图
                        System.out.printf("<" + i + "," + j + ">, ");
                        break;
                    case 3:// 有向有权图
                        System.out.printf("<" + i + "," + j + "> " + temp.weight + ", ");
                        break;
                    default:
                        break;
                }
                temp = temp.next;
            }
        }
        System.out.println("}");// 结束边集输出
    }

    // 输出邻接表
    public void outputLink() {
        System.out.println("输出该图的邻接表：");
        for (int i = 0; i < aEdgeNodes.length; i++) {
            System.out.printf(i + "  ");
            while (aEdgeNodes[i] != null) {
                System.out.printf("[" + aEdgeNodes[i].adjVex + "");// 输出邻接边
                if (aEdgeNodes[i].next != null)// 若仍存在邻接边
                    System.out.printf("-->]");// 输出箭头
                else// 若不存在邻接边
                    System.out.println(" ^]");// 输出^
                aEdgeNodes[i] = aEdgeNodes[i].next;
            }
        }
    }

    @Override
    public void outputAdjacency() {
        // TODO Auto-generated method stub

    }

    /**
     * 从顶点v开始深度优先搜索遍历图
     *
     * @param v 初始点的下标
     */
    public void depthFirstSearch(int v) {
        boolean[] visited = new boolean[n];// 定义辅助数组记录是否被遍历过
        for (int i = 0; i < n; i++) {
            visited[i] = false;// 初始化元素为false
        }
        dfs(v, visited);// 调用进行深度优先搜索遍历图
        System.out.println();
    }

    /**
     * 广度优先搜索遍历由邻接表所表示的图
     *
     * @param i       初始点的下标
     * @param visited 该点是否被访问过
     */
    private void dfs(int i, boolean[] visited) {
        System.out.printf(i + " ");// 假定访问顶点vi以输出该顶点的序号代之
        visited[i] = true;// 标记vi被访问过
        EdgeNode temp = aEdgeNodes[i];// 将vi邻接表的表头指针赋给temp
        while (temp != null) {// 搜索vi的所有邻接点
            int j = temp.adjVex;// 得到vi的一个邻接点
            if (!visited[j]) {// 若vi的一个有效邻接点vj未被访问过
                dfs(j, visited);// 则从vj出发进行递归调用
            }
            temp = temp.next;// 将temp指向下一结点
        }
    }

    /**
     * 从顶点v开始广度优先搜索遍历图
     *
     * @param v 初始点的下标
     */
    public void breadthFirstSearch(int v) {
        boolean[] visited = new boolean[n];// 定义辅助数组记录是否被遍历过
        for (int i = 0; i < n; i++) {
            visited[i] = false;// 初始化元素为false
        }
        bfs(v, visited);// 调用进行广度优先搜索遍历图
        System.out.println();

    }

    /**
     * 广度优先搜索遍历由邻接表所表示的图
     *
     * @param i       初始点的下标
     * @param visited 该点是否被访问过
     */
    private void bfs(int i, boolean[] visited) {
        sequenceQueue queue = new sequenceQueue();// 声明并创建一个空队列
        System.out.printf(i + " ");// 访问初始点
        visited[i] = true;// 将该点标记为已访问
        queue.enter(i);// 将已访问的点的下标入队
        while (!queue.isEmpty()) {// 队列非空时循环
            int k = (Integer) queue.leave();// 删除队首元素
            EdgeNode temp = aEdgeNodes[k];// 将顶点vk的表头指针赋给temp
            while (temp != null) {// 搜索vk所有邻接点
                int j = temp.adjVex;// 取出一个vk的邻接点
                if (!visited[j]) {// 若其未被访问过
                    System.out.printf(j + " ");// 访问该点
                    visited[j] = true;// 将其标记为已访问
                    queue.enter(j);// 将其下标入队
                }
                temp = temp.next;// 使边结点向后走
            }
        }
    }

    // 拓扑排序
    public void TopologicalSort(LinkedGraph Graph) {
        System.out.println("输出拓扑排序：");
        int[] inDegrees = new int[aEdgeNodes.length];// 定义数组存储各顶点入度
        for (int i = 0; i < inDegrees.length; i++) {
            inDegrees[i] = Graph.inDegree(i);// 求各顶点的入度
            System.out.println(inDegrees[i]);
        }
        Stack<EdgeNode> aStack = new Stack<>();// 定义一个栈
        // 将所有入度为0的顶点入栈
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0)
                aStack.push(aEdgeNodes[i]);
        }
        // 重复以下：
        while (!aStack.isEmpty()) {
            EdgeNode temp = aStack.pop();//栈顶元素出栈存入temp
            System.out.printf(temp.weight + " ");//输出顶点temp
            while (temp.adjVex != -1) {//删除所有temp发出的弧
                inDegrees[temp.adjVex] = inDegrees[temp.adjVex] - 1;
                if (inDegrees[temp.adjVex] == 0)//若存在某点入度为0
                    aStack.push(aEdgeNodes[temp.adjVex]);//将其入栈
                temp = aEdgeNodes[temp.adjVex];//用temp的下一个边代替temp
            }
        }

    }

    /**
     * 测试函数
     */
    public static void main(String[] args) {
        // 定义一个5个结点的有向有权图
        LinkedGraph aGraph = new LinkedGraph(5, 3);
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
        // 从0开始深度/广度优先遍历图
//		System.out.println("从0开始深度优先遍历：");
//		aGraph.depthFirstSearch(0);
//		System.out.println("从0开始广度优先遍历：");
//		aGraph.breadthFirstSearch(0);
        aGraph.outputLink();//输出邻接表

    }

}
