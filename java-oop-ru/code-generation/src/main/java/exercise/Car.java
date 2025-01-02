package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN

import java.io.IOException;
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка при сериализации объекта Car", e);
        }
    }

    public static Car deserialize(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, Car.class);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при десериализации строки JSON", e);
        }
    }
    // END
}
