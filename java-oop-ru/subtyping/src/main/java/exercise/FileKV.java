package exercise;

// BEGIN
import java.util.HashMap;
import java.util.Map;

public class FileKV implements KeyValueStorage {
    private final String filePath;
    private Map<String, String> storage;

    public FileKV(String filePath, Map<String, String> initialData) {
        this.filePath = filePath;
        this.storage = new HashMap<>(initialData);
        saveToFile();
    }

    @Override
    public void set(String key, String value) {
        storage.put(key, value);
        saveToFile();
    }

    @Override
    public void unset(String key) {
        storage.remove(key);
        saveToFile();
    }

    @Override
    public String get(String key, String defaultValue) {
        return storage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(storage);
    }

    private void saveToFile() {
        String json = Utils.serialize(storage);
        Utils.writeFile(filePath, json);
    }

    public void loadFromFile() {
        String json = Utils.readFile(filePath);
        if (json != null && !json.isEmpty()) {
            storage = Utils.deserialize(json);
        }
    }
}
// END
