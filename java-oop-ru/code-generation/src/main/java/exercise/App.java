package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void save(Path path, Car car) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(car);
            Files.writeString(path, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка сериализации объекта Car", e);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи файла", e);
        }
    }
    public static Car extract(Path path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = Files.readString(path);
            return objectMapper.readValue(json, Car.class);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла или десериализации объекта Car", e);
        }
    }
}
// END
