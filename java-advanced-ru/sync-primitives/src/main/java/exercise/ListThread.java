package exercise;

import java.util.Random;

// BEGIN
public class ListThread extends Thread{
    private SafetyList safetyList;

    private Random random;

    public ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1000);
                safetyList.add(random.nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
// END
