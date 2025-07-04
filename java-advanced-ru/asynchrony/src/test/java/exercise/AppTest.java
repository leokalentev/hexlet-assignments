package exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.concurrent.ExecutionException;

class AppTest {
    private String destPath;

    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    @BeforeEach
    void beforeEach() throws Exception {
        destPath = Files.createTempFile("test", "tmp").toString();
    }

    @Test
    void testUnion() throws Exception {
        CompletableFuture<String> result = App.unionFiles(
            "src/test/resources/file1.txt",
            "src/test/resources/file2.txt",
            destPath
        );
        result.get();

        String actual = Files.readString(getFullPath(destPath));
        assertThat(actual).contains("Test", "Message");
    }

    @Test
    void testUnionWithNonExistedFile() throws Exception {

        String result = tapSystemOut(() -> {
            App.unionFiles("nonExistingFile", "file", destPath).get();
        });

        assertThat(result.trim()).contains("NoSuchFileException");
    }

    // BEGIN
    @Test
    public void testGetDirectorySize() throws ExecutionException, InterruptedException, IOException {
        Path tempDir = Files.createTempDirectory("testDir");
        Files.writeString(tempDir.resolve("a.txt"), "Hello");
        Files.writeString(tempDir.resolve("b.txt"), "World!");

        CompletableFuture<Long> sizeFuture = App.getDirectorySize(tempDir.toString());
        long size = sizeFuture.get();

        assertTrue(size > 0);
    }
    // END
}
