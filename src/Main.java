import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final int THREADS = 4;
        final long SLEEP_TIMEOUT = 3000;

        List<CallableMessagingTask> tasks = new ArrayList<>(THREADS);
        System.out.println("Создаю потоки...");

        for (int i = 0; i < THREADS; i++) {
            tasks.add(new CallableMessagingTask("поток " + (i + 1), SLEEP_TIMEOUT,
                    (long) new Random().nextInt((int) SLEEP_TIMEOUT, (int) (SLEEP_TIMEOUT * THREADS))));
        }

        ExecutorService pool = Executors.newFixedThreadPool(THREADS);
        List<Future<Integer>> result = pool.invokeAll(tasks);

        System.out.println("Работа потоков завершена - получаю результат...");

        result.parallelStream()
                .forEach(task -> {
                    try {
                        System.out.println("Я поток - " +  (result.indexOf(task) + 1) +
                                ". Количество отправленных сообщений - " + task.get());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        System.out.println("Получение результата одного потока. Запуск потоков...");
        int taskResult = pool.invokeAny(tasks);
        System.out.println("Количество отправленных сообщений потоком - " + taskResult);
    }
}