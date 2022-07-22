public class Threadmaker {


    public static void runInThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }

}
