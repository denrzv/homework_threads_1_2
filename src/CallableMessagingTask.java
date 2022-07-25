import java.time.Instant;
import java.util.concurrent.Callable;

public class CallableMessagingTask implements Callable<Integer> {
    private final String name;
    private final long sleepTimeout;
    private final long timeToLive;

    public CallableMessagingTask(String name, Long sleepTimeout, Long timeToLive) {
        this.name = name;
        this.sleepTimeout = sleepTimeout;
        this.timeToLive = timeToLive;
    }

    @Override
    public Integer call() throws Exception {
        int result = 0;
        long timeToEnd = Instant.now().getEpochSecond() + timeToLive / 1000;
        while (getTimeToLiveLeft(timeToEnd) > 0) {
            System.out.println("Я " + name + ". Всем привет! Мне осталось работать: " +
                    (getTimeToLiveLeft(timeToEnd)) + " сек");
            result++;
            Thread.sleep(sleepTimeout);
        }
        return result;
    }

    private long getTimeToLiveLeft(long timeToEnd) {
        return timeToEnd - Instant.now().getEpochSecond();
    }
}
