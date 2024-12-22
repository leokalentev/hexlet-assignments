package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage dictionary) {
        Map<String, String> dict = new HashMap<>(dictionary.toMap());
        dict.forEach((key, value) -> {
            dictionary.unset(key);
            dictionary.set(value, key);
        });
    }
}
// END
