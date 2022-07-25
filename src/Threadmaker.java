package src;
public class Threadmaker {
    private static int counter = 0;
    private static Thread thread;

    public static void runInThread(Runnable runnable) {
        counter++;
        if (counter > 4)
            counter = 1;

        thread = new Thread(runnable);
        thread.start();
        thread.setName("" + counter);
    }
}
