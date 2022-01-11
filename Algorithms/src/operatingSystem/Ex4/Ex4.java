package operatingSystem.Ex4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * 名称 : 基本分页存储管理
 * 本类使用到了Java8的lambda，需要JDK8才能运行
 */
public class Ex4 {
    int status[][];//矩阵
    int m, n;//矩阵的行数和列数
    HashMap<Integer, String> data;//物理块信息

    //用户初始化
    public void init() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入物理块的行数");
        m = scanner.nextInt();
        System.out.println("请输入物理块的列数");
        n = scanner.nextInt();
        status = new int[m][n];
        data = new HashMap<>(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("请输入第" + transToPosition(i, j) + "块的状态");
                int s = scanner.nextInt();
                status[i][j] = s;//修改矩阵中的值
            }
        }
    }

    //模拟初始化
    public void virtualInit() {
        m = 6;
        n = 5;
        status = new int[m][n];
        data = new HashMap<>(m * n);
    }

    //分配作业
    public void paging(String name, int c) {
        int freeCount = 0;//检测空闲物理块数量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (status[i][j] == 0) freeCount++;
            }
        }
        //如果空间充足
        if (freeCount > c) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (status[i][j] == 0 && c > 0) {
                        status[i][j] = 1;//修改矩阵信息
                        data.put(transToPosition(i, j), name);
                        c--;
                    }
                }
            }
        } else
            System.out.println("剩余空间不足，无法分配" + name);
        printCurrent();
    }

    //回收作业
    public void restory(String name) {
        List<Integer> list = new ArrayList<>();//创建用于保存作业所使用的物理块集合
        data.forEach((k, v) -> {
            if (name.equals(v)) list.add(k);
        });//遍历将作业使用的物理块号加入集合中
        for (Integer t : list) {
            data.remove(t);//删除作业所占用的物理块
            status[transToX(t)][transToY(t)] = 0;//更新矩阵信息
        }
        printCurrent();
    }

    //查看当前信息
    public void printCurrent() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf(status[i][j] + "\t");
            }
            System.out.println();
        }
        data.forEach((k, v) -> System.out.printf(k + "--" + v + "\t"));
        System.out.println();
        System.out.println("---------------------");
    }


    //将xy坐标转化为物理块号
    private int transToPosition(int x, int y) {
        return x * n + y;
    }

    //取出物理块号的x坐标(行)
    private int transToX(int position) {
        return position / n;
    }

    //取出物理块号的y坐标(列)
    private int transToY(int position) {
        return position % n;
    }


    public static void main(String[] args) {
        Ex4 basicPaging = new Ex4();
        basicPaging.virtualInit();
        basicPaging.paging("作业1", 3);
        basicPaging.paging("作业2", 6);
        basicPaging.paging("作业3", 5);
        basicPaging.restory("作业2");
        basicPaging.paging("作业4", 11);
    }

}
