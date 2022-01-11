package dataStructureJ.treeDemo;

/**
 * 二叉搜索树的链接存储类
 *
 * @author Mo
 */
public class linkBinarySearchTree extends linkBinaryTree implements BinarySearchTree {

    //构造函数
    public linkBinarySearchTree() {
        super();
    }

    @SuppressWarnings("unchecked")
    //查找值为obj的结点，成功则返回结点值，否则返回null
    public Object find(Object obj) {
        if (getRoot() == null) return null;//若根节点为空，则返回null
        BTreeNode temp = getRoot();//定义临时结点temp
        while (temp != null) {//当临时结点非空时
            if ((((Comparable<Object>) obj).compareTo(((Comparable<Object>) temp.element))) == 0)
                return temp.element;//查找成功则返回结点值
            else if ((((Comparable<Object>) obj).compareTo(((Comparable<Object>) temp.element))) < 0)
                temp = temp.left;//否则查找左子树
            else
                temp = temp.right;//否则查找右子树
        }
        return null;//查找失败返回null
    }

    //更新值为obj的结点值，更新成功则返回原值，否则返回空值
    public Object update(Object obj) {
        if (getRoot() == null) return null;//若根节点为空，则返回null
        BTreeNode temp = getRoot();//定义临时结点temp
        while (temp != null) {//当临时结点非空时
            if ((((Comparable<Object>) obj).compareTo(((Comparable<Object>) temp.element))) == 0) {//若查找到给定结点
                Object xObject = temp.element;//更新值
                temp.element = obj;
                return xObject;//返回原值
            } else if ((((Comparable<Object>) obj).compareTo(((Comparable<Object>) temp.element))) < 0)
                temp = temp.left;//否则查找左子树
            else
                temp = temp.right;//否则查找右子树
        }
        return null;//更新失败返回null
    }

    //插入值为obj的结点，若插入成功则返回true，否则返回false
    public boolean insert(Object obj) {
        BTreeNode temp = root, parent = null;//定义临时结点temp,定义parent作为临时结点的父结点
        while (temp != null) {//当临时结点非空时
            parent = temp;
            if (((Comparable<Object>) obj).compareTo((Comparable<Object>) temp.element) == 0)
                return false;//存在重复元素时，返回false表示插入失败
            else if (((Comparable<Object>) obj).compareTo((Comparable<Object>) temp.element) < 0)
                temp = temp.left;//否则查找左子树
            else
                temp = temp.right;//否则查找右子树
        }
        BTreeNode aNode = new BTreeNode(obj);//根据指定值构建一个新结点
        if (parent == null) root = aNode; //将新结点作为数根插入
        else if ((((Comparable<Object>) obj).compareTo(((Comparable<Object>) parent.element))) < 0)
            parent.left = aNode;//将新结点作为左孩子插入
        else
            parent.right = aNode;//将新结点作为右孩子插入
        return true;//返回真值表示插入成功
    }

    //删除值为obj的结点，成功则返回true，否则返回false
    public boolean delete(Object obj) {
        if (getRoot() == null) return false;//若根节点为空，则返回null
        BTreeNode temp = getRoot(), parent = null;//定义临时结点temp，定义父结点
        while (temp != null) {//当临时结点非空时
            if ((((Comparable<Object>) obj).compareTo(((Comparable<Object>) temp.element))) == 0)
                break;
            else if ((((Comparable<Object>) obj).compareTo(((Comparable<Object>) temp.element))) < 0) {
                parent = temp;
                temp = temp.left;
            } else {
                parent = temp;
                temp = temp.right;
            }
        }
        if (temp == null) return false;//若没有找到待删除的结点，则返回false
        //分三种情况删除已查找到的结点
        if (temp.left == null && temp.right == null) {
            //若待删除的结点为叶子时
            if (temp == root) root = null;//特殊处理根结点
            else if (temp == parent.left) parent.left = null;
            else if (temp == parent.right) parent.right = null;
        } else if (temp.left == null || temp.right == null) {
            //若待删除的结点为单分支时
            //特殊处理根结点
            if (temp == root) {
                if (temp.left == null) temp.right = null;
                else if (temp.right == null) temp.left = null;
            }
            //对于其他结点，分四种不同的单分支情况进行不同处理
            else if (parent.left == temp && temp.left == null) parent.left = temp.right;
            else if (parent.left == temp && temp.right == null) parent.left = temp.left;
            else if (parent.right == temp && temp.left == null) parent.right = temp.right;
            else if (parent.right == temp && temp.right == null) parent.right = temp.left;
        } else if (temp.left != null && temp.right != null) {
            //若待删除的结点为双分支时
            BTreeNode s1 = temp, s2 = temp.left;//定义两个结点
            while (s2.right != null) {//沿左孩子的右子树查找其中序前驱
                s1 = s2;
                s2 = s2.right;
            }
            temp.element = s2.element;//将中序前驱结点s2的值赋给临时结点
            //删除右子树为空的s2结点，使它的左子树链接到它所在的链接位置
            if (s1 == temp)
                temp.left = s2.left;//对临时结点的中序前驱结点就是临时结点的左孩子的情况进行处理
            else
                s1.right = s2.left;
        }
        return true;
    }

    //获取一组数量在10 ~ 20之间的真随机数，数值在0 ~ 100
    public static int[] getRandomNumbers() {
        int[] result = new int[10];
        int count = 0;
        while (count < 10) {
            int num = (int) (Math.random() * 100);
            boolean flag = true;
            for (int j = 0; j < 10; j++) {
                if (num == result[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[count] = num;
                System.out.println("产生了随机数：" + num);
                count++;
            }
        }
        return result;
    }


    //测试函数
    public static void main(String[] args) {
        int[] a = {23, 45, 89, 40, 73, 12, 49, 72, 20, 44};
        int[] b = getRandomNumbers();//获取随机关键字
        linkBinarySearchTree aTree = new linkBinarySearchTree();
        for (int i : a) {//将随机关键字插入二叉搜索树
            aTree.insert(i);
        }
        aTree.printBTree(aTree.getRoot());//打印二叉搜索树
        System.out.println();
        System.out.println("是否删除结点20:" + aTree.delete(20));
        System.out.println("是否删除结点45:" + aTree.delete(45));


    }

}