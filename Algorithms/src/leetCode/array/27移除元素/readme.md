# 27移除元素

## 题目
给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。

不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。

元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

 

示例 1:

给定 nums = [3,2,2,3], val = 3,

函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。

你不需要考虑数组中超出新长度后面的元素。
示例 2:

给定 nums = [0,1,2,2,3,0,4,2], val = 2,

函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。

注意这五个元素可为任意顺序。

你不需要考虑数组中超出新长度后面的元素。
 

说明:

为什么返回数值是整数，但输出的答案是数组呢?

请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

// nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
int len = removeElement(nums, val);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}

## 思路一 : 双指针
### 思路
保留两个指针 i 和 j，其中 i 是慢指针，j 是快指针，当 nums[j] 与给定的值相等时，递增 j 以跳过该元素。只要 nums[j]不等于valnums[j] ，我们就复制 nums[j] 到 nums[i] 并同时递增两个索引。重复这一过程，直到 j 到达数组的末尾，该数组的新长度为 i

### 代码
```java
public int removeElement(int[] nums, int val) {
    int i = 0;
    for (int j = 0; j < nums.length; j++) {
        if (nums[j] != val) {
            nums[i] = nums[j];
            i++;
        }
    }
    return i;
}
```
 
### 复杂度分析
时间复杂度：O(n)，假设数组总共有 n 个元素，i 和 j 至少遍历 2n2n 步。

空间复杂度：O(1)
                              
## 思路二 : 双指针进行交换移除，遇到要删除的值时将它和数组最后一个元素交换然后删除交换后数组的最后一个元素
### 思路
上一种思路中，如果删除的数据很少，那么会导致很多不需要删除的数据也进行了移动位置的操作。
当我们遇到 nums[i] = valnums[i]=val 时，我们可以将当前元素与最后一个元素进行交换，并释放最后一个元素。这实际上使数组的大小减少了 1。

请注意，被交换的最后一个元素可能是您想要移除的值。但是不要担心，在下一次迭代中，我们仍然会检查这个元素

### 代码
```java
public int removeElement(int[] nums, int val) {
    int i = 0;
    int n = nums.length;
    while (i < n) {
        if (nums[i] == val) {
            nums[i] = nums[n - 1];
            // reduce array size by one
            n--;
        } else {
            i++;
        }
    }
    return n;
}
```
### 复杂度分析
时间复杂度：O(n)，i 和 n 最多遍历 n 步。在这个方法中，赋值操作的次数等于要删除的元素的数量。因此，如果要移除的元素很少，效率会更高。

空间复杂度：O(1)

链接：https://leetcode-cn.com/problems/remove-element/solution/yi-chu-yuan-su-by-leetcode/

## 思路三 ： 拷贝覆盖，保存一个下标变量，遍历时如果发现元素和需要移除的值不相等就把该数组下标变量位置处的值覆盖为该元素

### 思路
主要思路是遍历数组 nums，每次取出的数字变量为 num，同时设置一个下标 ans
在遍历过程中如果出现数字与需要移除的值不相同时，则进行拷贝覆盖 nums[ans] = num，ans 自增 1
如果相同的时候，则跳过该数字不进行拷贝覆盖，最后 ans 即为新的数组长度
这种思路在移除元素较多时更适合使用，最极端的情况是全部元素都需要移除，遍历一遍结束即可

### 代码
```
const removeElement_1 = (nums: number[], val: number) => {
    if (nums.length === 0 || !nums) return 0

    let i = nums.length - 1
    let len = nums.length

    for (let j = len; j >= 0; j--) {

        if (nums[i] !== val) {
            // 往后退
            i--
        } else {
            // 直接删除
            nums.splice(i, 1)
            len--
        }

    }
    return i + 1
}
```

### 复杂度分析
时间复杂度：O(n)，空间复杂度：O(1)

