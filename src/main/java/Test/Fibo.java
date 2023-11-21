package Test;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: whj
 * @Date: 2023-10-06 10:20
 */
public class Fibo {
    private static Long[] memo = new Long[100];
    public Long fibo(Long n) {
        if (n == 1 || n == 2) {
            return 1L;
        }
        if (memo[n.intValue()] != null) {
            return memo[n.intValue()];
        }
        memo[n.intValue()] = fibo(n - 1) + fibo(n - 2);
        return memo[n.intValue()];
    }

    public static void main(String[] args) {
        Fibo fibo = new Fibo();
        LocalDateTime start = LocalDateTime.now();
        System.out.println(fibo.fibo(10L));
        LocalDateTime end = LocalDateTime.now();
        System.out.println(end.getNano() - start.getNano());
    }
}
