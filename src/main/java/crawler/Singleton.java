package crawler;

/**
 * @Description: The Singleton pattern with a static inner class ensures safety
 *               in a multi-thread environment without the use of any locks,
 *               and it doesn't have any impact on performance or waste any space.
 * @Author: whj
 * @Date: 2024-02-03 21:25
 */
public class Singleton {
    private Singleton() {

    }

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
