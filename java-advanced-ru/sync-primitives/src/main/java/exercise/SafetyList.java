package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {
    // BEGIN
    private List<Integer> safetyList;

    public SafetyList() {
        this.safetyList = new ArrayList<>();
    }

    public synchronized void add(int num) {
        safetyList.add(num);
    }

    public int get(int index) {
        return safetyList.get(index);
    }

    public int size() {
        return safetyList.size();
    }
    // END
}
