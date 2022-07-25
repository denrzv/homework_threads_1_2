

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int THREADS = 4;
        final long SLEEP_TIMEOUT = 3000;

        ThreadGroup threadGroup = new ThreadGroup("Messaging Threads Group");
        System.out.println("Создаю потоки...");
        for (int i = 0; i < THREADS; i++) {
            new MessagingThread(threadGroup, SLEEP_TIMEOUT,"Поток " + (i + 1)).start();
        }

        Thread.sleep(THREADS * SLEEP_TIMEOUT);
        System.out.println("Завершаю потоки...");
        threadGroup.interrupt();
    }
}