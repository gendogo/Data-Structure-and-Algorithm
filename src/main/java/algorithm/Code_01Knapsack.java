package algorithm;

import java.io.*;
import java.util.Arrays;

/**
 * @Description: Test link: https://www.luogu.com.cn/problem/P1048
 * @Author: whj
 * @Date: 2024-03-21 14:55
 */
public class Code_01Knapsack {
    public static int MAXM = 101;

    public static int MAXT = 1001;

    public static int[] cost = new int[MAXM];

    public static int[] val = new int[MAXM];

    public static int[] dp = new int[MAXT];

    public static int t, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            t = (int) in.nval;
            in.nextToken();
            n = (int) in.nval;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                cost[i] = (int) in.nval;
                in.nextToken();
                val[i] = (int) in.nval;
            }
            out.println(compute2());
        }
        out.flush();
        out.close();
        br.close();
    }

    private static int compute1() {
        int[][] dp = new int[n + 1][t + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= t; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - cost[i] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cost[i]] + val[i]);
                }
            }
        }
        return dp[n][t];
    }

    private static int compute2() {
        Arrays.fill(dp, 0, t + 1, 0);
        for (int i = 1; i <= n; i++) {
            for (int j = t; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + val[i]);
            }
        }
        return dp[t];
    }

}
