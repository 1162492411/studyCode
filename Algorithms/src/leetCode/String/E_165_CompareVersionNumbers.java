package leetCode.String;

/**
 * 问题描述： Compare two version numbers version1 and version2. If version1 >
 * version2 return 1, if version1 < version2 return -1, otherwise return 0.
 * <p>
 * You may assume that the version strings are non-empty and contain only digits
 * and the . character. The . character does not represent a decimal point and
 * is used to separate number sequences. For instance, 2.5 is not
 * "two and a half" or "half way to version three", it is the fifth second-level
 * revision of the second first-level revision.
 * <p>
 * Here is an example of version numbers ordering:
 * <p>
 * 0.1 < 1.1 < 1.2 < 13.37
 *
 * @author Mo
 */
public class E_165_CompareVersionNumbers {

    public static int CompareVersion(String version1, String version2) {
        //先取两个版本号.左侧数值比较，再取两个版本号.右侧数值进行比较
        int v1pre = 0, v2pre = 0;//定义两个int，保存两个版本号.之前的数值
        int v1suf = 0, v2suf = 0;//定义两个int，保存两个版本号.左侧数值的位数
        for (; v1suf < version1.length() && v2suf < version2.length(); v1suf++, v2suf++) {
            v1pre = v1suf;
            v2pre = v2suf;
            while (v1suf < version1.length() && version1.charAt(v1suf) != '.') v1suf++;//确认版本1.左侧数值位数
            while (v2suf < version2.length() && version2.charAt(v2suf) != '.') v2suf++;//确认版本2.左侧数值位数
            int result = comparePre(version1, v1pre, v1suf, version2, v2pre, v2suf);
            if (result != 0)//比较版本1，2左侧数值
                return result;

        }
        return 0;
    }

    //比较版本1，2数值
    private static int comparePre(String version1, int beginIndex1, int endIndex1, String version2, int beginIndex2, int endIndex2) {
        if (version1 == null || version2 == null) System.exit(-1);
        if (endIndex1 > version1.length() || beginIndex1 > endIndex1) System.exit(-1);
        if (endIndex2 > version2.length() || beginIndex2 > endIndex2) System.exit(-1);

        int v1 = Integer.parseInt(version1.substring(beginIndex1, endIndex1));
        int v2 = Integer.parseInt(version2.substring(beginIndex2, endIndex2));
        if (v1 - v2 > 0) return 1;
        else if (v1 - v2 < 0) return -1;
        else return 0;
    }

    public static void main(String[] args) {
        System.out.println("******");
        System.out.println(CompareVersion("11", "11.1"));
        System.out.println("-----");
    }

}
