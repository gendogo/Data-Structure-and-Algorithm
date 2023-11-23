package algorithm;

/**
 * @Description: from 代码随想录
 * @Author: whj
 * @Date: 2023-11-23 20:07
 */
public class KMP {
    private String pat;
    private int[] next;

    public KMP(String pat) {
        this.pat = pat;
        next = new int[pat.length()];
        getNext(next,pat);
    }

    public static void main(String[] args) {
        KMP kmp = new KMP("aabaa");
        int index = kmp.strStr("aabafaabaaf");
        System.out.println(index);
    }

    public void getNext(int[] next, String s) {
        //next[i] 表示 i（包括i）之前最长相等的前后缀长度（其实就是j）
        //j指向前缀末尾位置，i指向后缀末尾位置
        int j = 0;
        next[0] = 0;
        //i既然是后缀末尾，那么肯定从1开始，而不是0
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) { // j要保证大于0，因为下面有取j-1作为数组下标的操作
                j = next[j - 1]; // 注意这里，是要找前一位的对应的回退位置了，退无可退，那就是next[0] = 0
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
    }

   public int strStr(String origin) {
        if (pat.length() == 0) {
            return 0;
        }
        int j = 0;
        //i是不断加1的，j一直在0到pat.length()之间横跳
        for (int i = 0; i < origin.length(); i++) {
            while (j > 0 && origin.charAt(i) != pat.charAt(j)) {
                j = next[j - 1];
            }
            if (origin.charAt(i) == pat.charAt(j)) {
                j++;
            }
            if (j == pat.length()) {
                return (i - pat.length() + 1);
            }
        }
        return -1;
    }
}
