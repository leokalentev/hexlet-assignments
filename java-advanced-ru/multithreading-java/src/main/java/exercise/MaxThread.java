package exercise;

import java.util.logging.Logger;

// BEGIN
public class MaxThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(MinThread.class.getName());
    private int[] numbers;
    private int maxNum;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        LOGGER.info("Thread " + Thread.currentThread().getName() + " started");
        maxNum = -10000;
        for (int number : numbers) {
            if (number > maxNum) {
                maxNum = number;
            }
        }
        LOGGER.info("Thread " + Thread.currentThread().getName() + " finished");
    }

    public int getMax() {
        return maxNum;
    }
}
// END
