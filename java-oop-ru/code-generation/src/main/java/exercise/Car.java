package exercise;

import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

// BEGIN
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
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (IOException e) {
            throw new RuntimeException("Error serializing Car object", e);
        }
    }

    public static Car deserialize(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, Car.class);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing Car object", e);
        }
    }
    // END
}
