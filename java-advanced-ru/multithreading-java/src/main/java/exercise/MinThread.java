package exercise;

import java.util.logging.Logger;

// BEGIN
public class MinThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(MinThread.class.getName());
    private int[] numbers;
    private int minNum;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        LOGGER.info("Thread " + Thread.currentThread().getName() + " started");
        minNum = 10000;
        for (int number : numbers) {
            if (number < minNum) {
                minNum = number;
            }
        }
        LOGGER.info("Thread " + Thread.currentThread().getName() + " finished");
    }

    public int getMin() {
        return minNum;
    }
}
// END
