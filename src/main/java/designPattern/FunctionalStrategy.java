package designPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 函数式策略模式写法，修改成本比较低，可以业务初期使用
 * @Author: whj
 * @Date: 2023-11-11 15:54
 */
public class FunctionalStrategy {
    private static Map<String, Runnable> strategyMap;


    static {
        strategyMap = new HashMap<>();
        strategyMap.put("add", FunctionalStrategy::add);
        strategyMap.put("commit", FunctionalStrategy::commit);
    }

    public static void doStrage(String strageName) {
        Runnable strategy = strategyMap.get(strageName);
        strategy.run();
    }

    private static void add() {
        System.out.println("add");
    }

    private static void commit() {
        System.out.println("commit");
    }

    public static void main(String[] args) {
        FunctionalStrategy.doStrage("add");
        System.out.println(10 * 1024 / 60);
    }
}
