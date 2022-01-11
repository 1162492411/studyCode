package dataStructureJ.treeDemo;

import queueDemo.sequenceQueue;

import java.util.Stack;

/**
 * 链接存储的二叉树类
 *
 * @author Mo
 */
public class linkBinaryTree implements BinaryTree {
    public BTreeNode root;//定义二叉树的树根指针

    /**
     * 无参的构造函数
     */
    public linkBinaryTree() {
        root = null;
    }

    /**
     * 返回二叉树的根指针
     */
    public BTreeNode getRoot() {
        return root;
    }

    /**
     * 创建二叉树
     */
    public void creatBTree(String gt) {
        Stack<BTreeNode> bStack = new Stack<BTreeNode>();
        //sequenceStack aStack = new sequenceStack();//定义并创建一个保存结点指针的栈
        root = null;//根节点置空
        BTreeNode temp = null;//定义一个临时结点
        int k = 1;//用k作为处理结点的左子树与右子树的标记
        char[] a = gt.toCharArray();//将字符串还原为char数组，对字符进行逐个处理
        for (int i = 0; i < a.length; i++) {
            switch (a[i]) {
                case ' '://不处理空格
                    break;
                case '('://处理左括号
                    bStack.push(temp);
                    k = 1;
                    break;
                case ')'://处理右括号
                    if (bStack.isEmpty()) System.exit(1);
                    bStack.pop();
                    break;
                case ','://处理逗号
                    k = 2;
                    break;
                default://处理其他字符
                    temp = new BTreeNode(a[i]);
                    if (root == null) root = temp;
                    else {
                        if (k == 1) ((BTreeNode) bStack.peek()).left = temp;
                        else ((BTreeNode) bStack.peek()).right = temp;
                    }
                    break;
            }
        }
    }


    /**
     * 按照一定次序遍历二叉树
     */
    public void traverseBTree(String s) {
        switch (s) {
            case "preOrder":
                preOrder(root);
                break;
            case "inOrder":
                inOrder(root);
                break;
            case "postOrder":
                postOrder(root);
                break;
            case "levelOrder":
                levelOrder(root);
                break;
            default:
                break;
        }

    }


    /**
     * 先序遍历二叉树--递归
     */
    public void preOrder(BTreeNode rt) {
        if (rt != null) {
            System.out.print(rt.element + "  ");//打印根节点
            preOrder(rt.left);//先序遍历左子树
            preOrder(rt.right);//先序遍历右子树
        }
    }


    /**
     * 中序遍历二叉树--递归
     */
    public void inOrder(BTreeNode rt) {
        if (rt != null) {
            inOrder(rt.left);//中序遍历左子树
            System.out.print(rt.element + "  ");//打印根节点
            inOrder(rt.right);//中序遍历右子树
        }
    }

    /**
     * 后序遍历二叉树--递归
     */
    public void postOrder(BTreeNode rt) {
        if (rt != null) {
            postOrder(rt.left);//后序遍历左子树
            postOrder(rt.right);//后序遍历右子树
            System.out.print(rt.element + "  ");//打印根节点
        }
    }

    /**
     * 层次遍历--非递归
     */
    private void levelOrder(BTreeNode root) {
        sequenceQueue aQueue = new sequenceQueue();//创建队列
        BTreeNode temp = null;
        aQueue.enter(root);//将根节点入队
        while (!aQueue.isEmpty()) {
            temp = (BTreeNode) aQueue.leave();//删除队首元素赋给临时结点
            System.out.print(temp.element + "  ");//将临时结点的值输出
            if (temp.left != null) aQueue.enter(temp.left);//左孩子入队
            if (temp.right != null) aQueue.enter(temp.right);//右孩子入队	
        }
    }

    /**
     * 判断二叉树是否为空
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 求二叉树深度
     * 二叉树深度等于左子树和右子树中的最大深度加1
     */
    public int depthBTree(BTreeNode rt) {
        if (root == null) {
            return 0;
        } else {
            int dep1 = depthBTree(rt.left);
            int dep2 = depthBTree(rt.right);
            return (dep1 > dep2) ? dep1 + 1 : dep2 + 1;
        }
    }

    /**
     * 求二叉树结点总数
     * 二叉树结点总数等于左子树结点数加右子树结点数加1
     */
    public int countBTree(BTreeNode rt) {
        if (rt == null) return 0;
        else return countBTree(rt.left) + countBTree(rt.right) + 1;
    }

    /**
     * 打印二叉树
     */
    public void printBTree(BTreeNode rt) {
        if (rt != null) {
            System.out.printf(rt.element + "");
            if (rt.left != null || rt.right != null) {
                System.out.print('(');
                printBTree(rt.left);
                if (rt.right != null)
                    System.out.print(',');
                printBTree(rt.right);
                System.out.print(')');
            }
        }
    }

    /**
     * 清除二叉树
     */
    public void clearBTree() {
        root = null;//将根指针置为空
    }

    /**
     * 根据指定值查找二叉树中的结点
     */
    public Object findBTree(BTreeNode rt, Object obj) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 测试函数
     */
    public static void main(String[] args) {
        linkBinaryTree aTree = new linkBinaryTree();
        String s = "a(b(c),d(e(f,g),h(,i)))";
        aTree.creatBTree(s);
        System.out.println("创建的二叉树广义表为:" + s);
        System.out.println("先序遍历二叉树--递归");
        aTree.traverseBTree("preOrder");
        System.out.println();
        System.out.println("中序遍历二叉树--递归");
        aTree.traverseBTree("inOrder");
        System.out.println();
        System.out.println("后序遍历二叉树--递归");
        aTree.traverseBTree("postOrder");
        System.out.println();
        System.out.println("层次遍历二叉树--非递归");
        aTree.traverseBTree("levelOrder");
        System.out.println();


    }


}
