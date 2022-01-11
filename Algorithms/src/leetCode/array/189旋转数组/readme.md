# 189 旋转数组

## 题目
给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

示例 1:

输入: [1,2,3,4,5,6,7] 和 k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
示例 2:

输入: [-1,-100,3,99] 和 k = 2
输出: [3,99,-1,-100]
解释: 
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]
说明:

尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
要求使用空间复杂度为 O(1) 的 原地 算法。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rotate-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路一 : 暴力法，后移k次，每次将数组中的值后移1个位置

### 思路
内层循环每次将数组中各个位置的值都后移一位，特殊注意点是已经在尾部的值需要挪动到头部(将其理解成在环上进行移动)，内层循环中我们只对每个元素移动了一个位置，那么将内层循环循环k次就可以完成

### 代码
```java
public class Solution {
    public void rotate(int[] nums, int k) {
        int temp, previous;
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }
}
```


### 复杂度分析

时间复杂度：O(n*k) 。每个元素都被移动 1 步（O(n)） k次（O(k)） 。
空间复杂度：O(1) 。没有额外空间被使用。

来源：https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode/

##思路二 : 先在新数组中将原来的元素放在计算后的位置，然后将新数组的值都替换到旧数组

### 思路
我们可以用一个额外的数组来将每个元素放到正确的位置上，也就是原本数组里下标为 ii 的我们把它放到 (i+k)\%数组长度(i+k)%数组长度 的位置。然后把新的数组拷贝到原数组中。

### 代码
```java
public class Solution {
    public void rotate(int[] nums, int k) {
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
    }
}
```

### 复杂度分析
时间复杂度： O(n)。将数字放到新的数组中需要一遍遍历，另一边来把新数组的元素拷贝回原数组。
空间复杂度： O(n)。另一个数组需要原数组长度的空间。

作者：https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode/

## 思路三 : 分成两组进行反转,对原数组反转所有数字后，先反转前k个数字，再反转剩下的数字

### 思路
这个方法基于这个事实：当我们旋转数组 k 次， k\%nk%n 个尾部元素会被移动到头部，剩下的元素会被向后移动。

在这个方法中，我们首先将所有元素反转。然后反转前 k 个元素，再反转后面 n-kn−k 个元素，就能得到想要的结果。

假设 n=7n=7 且 k=3k=3 。


原始数组                  : 1 2 3 4 5 6 7
反转所有数字后             : 7 6 5 4 3 2 1
反转前 k 个数字后          : 5 6 7 4 3 2 1
反转后 n-k 个数字后        : 5 6 7 1 2 3 4 --> 结果

### 代码
```java
public class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
```

### 复杂度分析
时间复杂度：O(n) 。 n 个元素被反转了总共 3 次。
空间复杂度：O(1) 。 没有使用额外的空间。

作者 ： https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode/

## 思路四 : 环状替换
### 思路
官方题解已经提供了四种方法，我在这里主要就第三种 环状替代 方法做一下图解，希望能帮助到没理解的朋友
我们假设现在有 A 、B 、C、 D、 E 五名同学，今天考试完，老师要求换座位，每个同学向后移动3个座位
于是就从 A 同学开始换座位了... （下图左边）

A同学 非常自觉，看了看自己座位号（0），根据老师要求，他走到了3 号位置，即 D 同学的位置，同时他把D 同学赶到了角落，自己坐在了 3 号位置，第一个完成任务真爽！
D同学 一看，不行啊，我咋能呆在角落，于是D同学也按要求理直气壮来到了1号位置，同样把B同学赶到了角落，猛男落泪...
B同学 当然也不干，气汹汹走到了4号位置，"E同学，麻烦起来一下，角落给你收拾好了：)",于是 E同学来到角落..
E同学 一想：不行呀，我这么帅，必须有座位！站起来跑到了2号位置，二话不说，赶走了C，坐上上去，一下子舒服了...
C同学 此时来了角落，想：不是每个人都有座位吗？？还需要抢？于是C从容的来到了0号位置，至此，所有同学都坐好了。
以上是 下图中左边 的情况，这个算法还会遇到另外一种情况，就是今天E同学请假，老师说那就每个人往后移动 2个座位。于是大家开始行动，和上面发生的一样，不过，当第二轮C同学坐好了以后，角落没人了，大家都有位子，就没人闹意见了...
老师一看，我精心布置的局，咋就停了呢，于是吼了一嗓子："咳咳！还有谁没换？第二组打头的那个！B同学！是不是你！你们第二组学学第一组，第一组早早的换完了，你们组还一个没动！"
B同学一听到喊自己的名字，秒怂...本来想偷懒，无奈换起来座位..就这样，随着第二组的同学换完座位，最终大家完成了换座位
(上面老师喊的那一嗓子，就是我们内循环退出，即C回到了起始位置0位置，这时候我们就将起始位置 Start + 1)



（补充）关于上述两种情况何时出现：
其实是这样的，对于一个长度为 nn 的数组，整体移动 kk 个位置

当 nn 和 kk 的最大公约数 等于 1 的时候：1 次遍历就可以完成交换；比如 n = 5, k = 3n=5,k=3
当 nn 和 kk 的最大公约数 不等于 1 的时候：1 次遍历是无法完成的所有元素归位，需要 mm (最大公约数) 次
所以在最大公约数不为 1 的时候
比如 [A,B,C,D,E,F][A,B,C,D,E,F] 此时 n = 6 \ , k = 4n=6 ,k=4 ，其最大公约数为 22 ,因此需要 22 轮循环
我们就可以把这个数组分成两部分来看：
第 11 轮循环（分组1）： A \ E \ C \ [A]A E C [A]
第 22 轮循环（分组2）： \ \ \ \ B \ F \ D \ [B]    B F D [B]

即：每一轮循环只会在自己的那一组上不停的遍历。所以
数组的前 mm 个元素，其实就是每一个分组的第一个元素，我们控制流程在每次发现一轮循环走到原点时+1

那么如何判断所有的分组都执行归位了呢？ 可以有两种方法来控制

第一种：我们就用最大公约数 mm 来控制外循环，代表总共有 mm 轮循环
第二种：由于nn个元素归位需要nn次交换，所以我们定义一个count代表交换次数，当 count = n 时完成

### 代码
```java
    public void rotate(int[] nums, int k) {
       int len  = nums.length;
       k = k % len;
       int count = 0;         // 记录交换位置的次数，n个同学一共需要换n次
        for(int start = 0; count < len; start++) {
            int cur = start;       // 从0位置开始换位子
            int pre = nums[cur];   
            do{
                int next = (cur + k) % len;
                int temp = nums[next];    // 来到角落...
                nums[next] = pre;
                pre = temp;
                cur = next;
                count++;
            }while(start != cur)  ;     // 循环暂停，回到起始位置，角落无人
             
        }   
    }  
}
```

### 复杂度分析
时间复杂度：O(n) 。只遍历了每个元素一次。
空间复杂度：O(1)。使用了常数个额外空间。

作者 ： https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-yuan-di-huan-wei-xiang-xi-tu-jie/

