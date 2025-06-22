package exercise;

import java.util.logging.Logger;

// BEGIN
public class MinThread extends Thread {
    private static Logger LOGGER = Logger.getLogger(MinThread.class.getName());
    private int[] numbers;
    private int minNum;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        LOGGER.info("Thread " + Thread.currentThread().getName() + " started");
        for (int i = 0; i < numbers.length; i++) {
            minNum = 100000;
            if (minNum > numbers[i]) {
                minNum = numbers[i];
            }
        }
        LOGGER.info("Thread " + Thread.currentThread().getName() + " finished");
    }

    public int getMin() {
        return minNum;
    }
}
// END
