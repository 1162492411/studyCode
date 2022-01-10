# String类
----------
## <a name="TOP">目录</a>
### [0.总述](#title_0)
### [1.成员变量](#title_1)
### [2.构造方法](#title_2)
### [3. 常用方法](#title_3)
#### [3.1 字符串比较](#title_3.1)
#### [3.2 字符串查找](#title_3.2)
#### [3.3 字符串截取](#title_3.3)
#### [3.4 字符串替换](#title_3.4)
#### [3.5 字符串转化](#title_3.5)
#### [3.6 转为字符串](#title_3.6)

-----
## <a name = "title_0"> 0.总述  </a>
## <a name = "title_1"> 1.成员变量  </a>

```
public final class String implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
    private final char value[];

    /** Cache the hash code for the string */
    private int hash; // Default to 0

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -6849794470754667710L;
```
&emsp;&emsp;查看上述JDK1.8的源代码可知，String类实质是对字符数组的封装；String类有三个成员变量,但Stirng类并没有提供对应的get/set方法，而且String类的value[]被final修饰,因此，String类对象是不可变的。

-------------

## <a name = "title_2"> 2.构造方法  </a>

&emsp;&emsp;String的构造方法如下：

    1) String()//构造一个空字符串对象
    2) String(String original)//构造一个original的副本，拷贝一个original
    3) String(char value[])//通过char数组构造字符串对象
    4) String(char value[], int offset, int count)//通过char数组，从offset开始，总共count长的字节构造字符串对象
    5）String(char[] value, boolean share)//该方法似乎没有完成
    6) String(int[] codePoints, int offset, int count)
    7) String(byte bytes[])//通过byte数组构造字符串对象,实际调用方法8
    8) String(byte bytes[], Charset charset)//通过byte数组构造对象并指定字符串格式，实际调用方法11
    9) String(byte bytes[], String charsetName)throws UnsupportedEncodingException//通过byte数组构造字符串对象并指定自定义的字符串格式，实际调用方法12
    10) String(byte bytes[], int offset, int length)//通过byte数组，从offset开始，总共length长的字节构造字符串对象，实际调用StringCoding的内置方法
    11) String(byte bytes[], int offset, int length, Charset charset)//通过byte数组，从offset开始，总共length长的字节构造字符串对象，并指定字符串格式,实际调用StringCode内置的方法
    12) String(byte bytes[], int offset, int length, String charsetName)throws UnsupportedEncodingException//通过byte数组，从offset开始，总共length长的字节构造字符串对象，并指定自定义的字符串格式,实际调用StringCode内置的方法
    13) String(byte ascii[], int hibyte)//实际调用方法14
    14) String(byte ascii[], int hibyte, int offset, int count)//将形参数组通过一些操作赋值给目标数组(内部的操作涉及单目运算符和位运算符)
    15) String(StringBuffer buffer)//加上自身的锁后实际调用Arrays内置方法
    16) String(StringBuilder builder)//实际调用Arrays内置方法

&emsp;&emsp;对其构造方法进行总结归纳如下:
> * String 类提供处理 Unicode 代码点（即字符）和 Unicode 代码单元（即 char 值）的方法

> * String类可以将char数组、byte数组、int数组、动态字符串封装为字符串;
> * String类通过内置的私有方法checkBounds来校检指定的截取位置是否合理，不合理时抛出异常;
> * String类在对形参的值进行封装时,实质上是调用Arrays、StringCode等方法

## <a name = "title_3"> 3.常用方法  </a>
### <a name = "title_3.1"> 3.1 字符串比较  </a>

    1) boolean equals(Object anObject)//判断内容是否相等，实质是在两个对象均为字符串的情况下将两个字符串的内容放入新建的两个char数组中逐个比较
    2) boolean equalsIgnoreCase(String anotherString)//判断内容是否相等时忽略大小写,实际调用String内置的regionMatches方法
    3) int compareTo(String anotherString)实质返回两个字符串中第一个不相等字符处的差值或两个字符串除内容相等部分外的长度之差
    4) int compareToIgnoreCase(String str)//比较时忽略字母大小写，实际调用String类内置的compare方法
    5) boolean regionMatches(int toffset, String other, int ooffset,int len)//对字符串中的部分内容是否相同进行比较,实际将两字符串指定部分分别复制进两个char数组进行比较
    6) boolean regionMatches(boolean ignoreCase, int toffset,String other, int ooffset, int len)//对字符串中的部分内容是否相同进行比较,实际将两字符串指定部分分别复制进两个char数组并根据是否忽略大小写进行比较

注：regionMatches()的两个方法内部实现除是否忽略大小写外相同，个人认为可将其合并为一个 方法

### <a name = "title_3.2"> 3.2 字符串查找  </a>

    1) char charAt(int index)//返回指定索引处的字符
    2) int codePointAt(int index)//返回指定索引处的字符，实际调用Character的内置方法
    3) int codePointBefore(int index)//返回指定索引前的字符，实际调用Character的内置方法
    4) int codePointCount(int beginIndex, int endIndex)//返回从beginIndex到endIndex之间的字符数量，，实际调用Character的内置方法
    5) int offsetByCodePoints(int index, int codePointOffset)//返回从指定索引处偏离指定距离后的下标，实际调用Character的内置方法
    6) int lastIndexOf(String str)//从字符串开始检索str，并返回最后出现的位置，未出现返回-1，实际调用方法7
    7) int lastIndexOf(String str, int fromIndex)//返回指定字符串在字符串中从指定位置开始，最后一次出现的位置，实际调用方法8
    8)static int lastIndexOf(char[] source, int sourceOffset, int sourceCount,char[] target, int targetOffset, int targetCount,int fromIndex)//
    9) int lastIndexOf(int ch)//返回指定的字符在字符串中最后一次出现的位置，实际调用方法10
    10) int lastIndexOf(int ch, int fromIndex)//返回指定字符在字符串中从指定位置开始，最后一次出现的位置,否则返回-1
    11) int indexOf(String str)//返回指定字符串在整个字符串中的首次出现的位置，实际调用方法12
    12) int indexOf(String str, int fromIndex)//返回指定字符串在整个字符串中从指定位置开始，首次出现的位置，实际调用方法15
    13) int indexOf(char ch)//返回指定字符在整个字符串中的首次出现位置，实际调用方法15
    14) int indexOf(int ch,int fromindex)//返回指定字符从质地更位置开始在整个字符串中的首次出现位置，实际调用方法15
    15)static int indexOf (char[] source, int sourceOffset, int sourceCount,char[] target, int targetOffset, int targetCount,int fromIndex)//
    16) boolean startsWith(String prefix)//测试字符串是否以指定前缀开始，实际调用方法17
    17) boolean startsWith(String prefix, int toffset)//测试字符串是否在指定位置以指定前缀开始，实际将两个字符串复制进两个char数组逐个进行比较
    18) boolean endsWith(String suffix)//测试指定字符串是否以指定后缀结束，实际调用String类的starWith方法

### <a name = "title_3.3"> 3.3 字符串截取  </a>

    1) String substring(int beginIndex)//从指定位置开始截取字符串，返回的是新建的字符串
    2) String substring(int beginIndex, int endIndex)//从指定开始位置到指定结束位置截取字符串，返回的值是新建的字符串
    3) String[] split(String regex)//返回根据正则表达式所切割的子字符串的合集，实际调用方法4
    4) String[] split(String regex, int limit)//返回根据正则表达式所切割的子字符串的合集
    5) String trim()//去除字符串左右两侧的空格,有空格则调用String类的substring进行截取并返回新字符串，否则返回原字符串
    6) charSequence subSequence(int fromIndex,int endIndex)//从指定开始位置到指定结束位置截取字符序列，实际调用方法4



### <a name = "title_3.4"> 3.4 字符串替换  </a>

    1) String replace(char oldChar, char newChar)//返回一个新的字符串,用指定的字符newChar替换此字符串中出现的所有字符oldChar
    2) String replace(CharSequence target, CharSequence replacement)//返回一个新的字符串，将字符串中出现的target替换成replacement，实际调用util.regex.Matcher的String replaceAll(String replacement)
    3) String replaceAll(String regex, String replacement) //返回一个新的字符串，运用正则表达式regex，将字符串中匹配的子字符串替换为replacement，实际调用util.regex.Matcher的String replaceAll(String replacement)
    4) String replaceFirst(String regex, String replacement)//返回一个新字符串，使用给定的 replacement 替换此字符串匹配给定的正则表达式的第一个子字符串，实际调用util.regex.Matcher的String replaceAll(String replacement)

注：我们可以看到，replaceAll这种支持正则表达式的函数比replace更为强大，但是使用所有替换方法时，需注意避免Java保留字符

### <a name = "title_3.5"> 3.5 字符串转化  </a>

    1) String toLowerCase()//返回一个新的字符串，将该字符串全部大写字母转为小写字母，实际调用方法2
    2) String toLowerCase(Locale locale)//返回一个新的字符串，根据指定的locale将该字符串全部大写字母转为小写字母
    3) String toUpperCase()//返回一个大写的字符串，将该字符串全部小写字母转为大写字母,实际调用方法4
    4) String toUpperCase(Locale locale)//返回一个大写的字符串，根据指定的locale将该字符串全部小写字母转为大写字母
    5) String toString()//返回该字符串
    6) char[] toCharArray()//转换为char数组
    7）void getChars(int srcBegin, int srcEnd, char dst[], int dstBegin)//将字符从此字符串的srcBigin开始，到srcend-1为止，复制到目标字符数组从索引 dstBegin 处开始
    9) byte[] getBytes()//返回byte数组，实际调用StringCoding内置方法
    10) byte[] getBytes(Charset charset)//根据指定的编码格式返回byte数组，实际调用StringCoding内置方法
    11) byte[] getBytes(String charsetName)//根据指定的编码格式字符串返回byte数组，实际调用StringCoding内置方法

	

### <a name = "title_3.6"> 3.6 转为字符串  </a>

    1)static String valueOf(boolean b)//将布尔值转化字符串
    2)static String valueOf(char c)//将字符转化字符串
    3)static String valueOf(char[] data)//将字符数组转化字符串
    4)static String valueOf(char[] data ,int offset, int count)//将字符数组指定部分转化字符串
    5)static String valueOf(double d)//将double值转化字符串
    6)static String valueOf(float f)//将float值转化字符串
    7)static String valueOf(int i)//将整数转化字符串
    8)static String valueOf(long l)//将long值转化字符串
    9)statuc String valueOf(object obj)//将对象转化成字符串