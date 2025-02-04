package exercise;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();
    private KeyValueStorage storage;

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
        storage = new FileKV(filepath.toString(), new HashMap<>());
    }

    @Test
    public void testGet() {
        storage.set("key", "value");
        assertThat(storage.get("key", "default")).isEqualTo("value");
        assertThat(storage.get("unknown", "default")).isEqualTo("default");
    }

    @Test
    public void testSet() {
        storage.set("key2", "value2");
        assertThat(storage.get("key2", "default")).isEqualTo("value2");
    }

    @Test
    public void testUnset() {
        storage.set("key", "value");
        storage.unset("key");
        assertThat(storage.get("key", "default")).isEqualTo("default");
    }

    @Test
    public void testToMap() {
        storage.set("key", "value");
        Map<String, String> expected = Map.of("key", "value");
        assertThat(storage.toMap()).isEqualTo(expected);
    }
}
