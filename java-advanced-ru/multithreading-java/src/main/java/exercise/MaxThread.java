package exercise;

import java.util.logging.Logger;

// BEGIN
public class MaxThread extends Thread {
    private static Logger LOGGER = Logger.getLogger(MinThread.class.getName());
    private int[] numbers;
    private int maxNum;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        LOGGER.info("Thread " + Thread.currentThread().getName() + " started");
        for (int i = 0; i < numbers.length; i++) {
            maxNum = -100000;
            if (maxNum < numbers[i]) {
                maxNum = numbers[i];
            }
        }
        LOGGER.info("Thread " + Thread.currentThread().getName() + " finished");
    }

    public int getMax() {
        return maxNum;
    }
}
// END
