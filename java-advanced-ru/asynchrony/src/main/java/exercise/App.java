package exercise;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String sourcePath1, String sourcePath2, String sourcePath3) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Path path1 = Paths.get(sourcePath1);
                Path path2 = Paths.get(sourcePath2);
                Path path3 = Paths.get(sourcePath3);

                CompletableFuture<String> content1 = CompletableFuture.supplyAsync(() -> {
                   try {
                       return Files.readString(path1, StandardCharsets.UTF_8);
                   } catch (Exception e) {
                       throw new IllegalStateException(e);
                   }
                });

                CompletableFuture<String> content2 = CompletableFuture.supplyAsync(() -> {
                    try {
                        return Files.readString(path2, StandardCharsets.UTF_8);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });

                return content1.thenCombine(content2, (text1, text2) -> {
                    String result = text1 + text2;
                    try {
                        if (!Files.exists(path3)) {
                            Files.createFile(path3);
                        }
                        Files.writeString(path3, result, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                    return result;
                }).join();

            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }).exceptionally(ex -> {
            Throwable cause = ex.getCause();
            while (cause.getCause() != null) {
                cause = cause.getCause();
            }
            System.out.println(cause.getClass().getSimpleName() + ": " + cause.getMessage());
            return null;
        });
    }

    public static CompletableFuture<Long> getDirectorySize(String dirPath) {
        return CompletableFuture.supplyAsync(() -> {
           try {
               Path dir = Paths.get(dirPath);
               if (!Files.isDirectory(dir)) {
                   throw new IOException("Не является директорией");
               }

               return Files.list(dir)
                       .filter(Files::isRegularFile)
                       .mapToLong(path -> {
                           try {
                               return Files.size(path);
                           } catch (IOException e) {
                               return 0L;
                           }
                       })
                       .sum();

           } catch (IOException e) {
               return 0L;
           }
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        String file1 = "src/main/resources/file1.txt";
        String file2 = "src/main/resources/file2.txt";
        String dest = "src/main/resources/merged.txt";

        unionFiles(file1, file2, dest)
                .thenAccept(result -> {
                    if (result != null) {
                        System.out.println("Файлы успешно объединены!");
                    }
                });

        getDirectorySize("src/main/resources")
                .thenAccept(size -> System.out.println("Размер директории: " + size + " байт"));
        // END
    }
}

