package exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Map;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class FileKVTest {

    private static final Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        Files.writeString(filepath, Utils.serialize(new HashMap<>()));
    }

    // BEGIN
    @Test
    void testSetAndGet() {
        KeyValueStorage storage = new FileKV(filepath, Map.of("key1", "value1", "key2", "value2"));

        assertThat(storage.get("key1", "default")).isEqualTo("value1");
        assertThat(storage.get("key2", "default")).isEqualTo("value2");
        assertThat(storage.get("key3", "default")).isEqualTo("default");

        storage.set("key3", "value3");
        assertThat(storage.get("key3", "default")).isEqualTo("value3");
    }

    @Test
    void testUnset() {
        KeyValueStorage storage = new FileKV(filepath, Map.of("key1", "value1", "key2", "value2"));

        storage.unset("key1");
        assertThat(storage.get("key1", "default")).isEqualTo("default");

        storage.unset("key3");
        assertThat(storage.get("key3", "default")).isEqualTo("default");
    }

    @Test
    void testToMap() {
        KeyValueStorage storage = new FileKV(filepath, Map.of("key1", "value1", "key2", "value2"));

        Map<String, String> expectedMap = Map.of("key1", "value1", "key2", "value2");
        assertThat(storage.toMap()).isEqualTo(expectedMap);

        storage.set("key3", "value3");
        Map<String, String> updatedMap = Map.of(
                "key1", "value1",
                "key2", "value2",
                "key3", "value3"
        );
        assertThat(storage.toMap()).isEqualTo(updatedMap);
    }

    @Test
    void testOverwriteFile() {
        KeyValueStorage storage = new FileKV(filepath, Map.of("key1", "value1"));

        storage.set("key1", "newValue");
        assertThat(storage.get("key1", "default")).isEqualTo("newValue");

        storage.unset("key1");
        assertThat(storage.get("key1", "default")).isEqualTo("default");
    }
    // END
}
