package exercise;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileKV implements KeyValueStorage {
    private final Path filepath;

    public FileKV(Path filepath, Map<String, String> dictionary) {
        this.filepath = filepath;
        Utils.writeFile(filepath.toString(), Utils.serialize(dictionary));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> data = Utils.deserialize(Utils.readFile(filepath.toString()));
        data.put(key, value);
        Utils.writeFile(filepath.toString(), Utils.serialize(data));
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> data = Utils.deserialize(Utils.readFile(filepath.toString()));
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public void unset(String key) {
        Map<String, String> data = Utils.deserialize(Utils.readFile(filepath.toString()));
        data.remove(key);
        Utils.writeFile(filepath.toString(), Utils.serialize(data));
    }

    @Override
    public Map<String, String> toMap() {
        return Utils.deserialize(Utils.readFile(filepath.toString()));
    }
}
