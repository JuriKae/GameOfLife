package src;
public class Threadmaker {
    private static Thread thread;

    public static void runInThread(Runnable runnable) {
        thread = new Thread(runnable);
        thread.start();
    }
}
