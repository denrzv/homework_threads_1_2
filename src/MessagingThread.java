public class MessagingThread extends Thread {
    private final long sleepTimeout;

    public MessagingThread(ThreadGroup threadGroup, Long sleepTimeout, String name) {
        super(threadGroup, name);
        this.sleepTimeout = sleepTimeout;
    }

    @Override
    public void run() {

        try {
            while (!isInterrupted()) {
                sleep(sleepTimeout);
                System.out.println("Я " + getName() + ". Всем привет!");
            }
        } catch (InterruptedException e) {
        }
        finally {
            System.out.println(getName() + " завершён.");
        }
    }

}
