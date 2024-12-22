package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    private Map<String,String> dictionary;

    public InMemoryKV(Map<String, String> dictionary) {
        this.dictionary = new HashMap<>(dictionary);
    }

    @Override
    public void set(String key, String value) {
        dictionary.put(key, value);
    }

    @Override
    public String get(String key, String defaultValue) {
        if (dictionary.containsKey(key)) {
            return dictionary.get(key);
        } else {
            return defaultValue;
        }
    }

    @Override
    public void unset(String key) {
        dictionary.remove(key);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(dictionary);
    }

}
// END
