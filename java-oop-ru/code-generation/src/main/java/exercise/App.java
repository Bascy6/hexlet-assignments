package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

// BEGIN
public class App {

    public static void save(Path path, Car car) {
        try {
            String json = car.serialize();
            Files.write(path, json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error saving Car object to file", e);
        }
    }

    public static Car extract(Path path) {
        try {
            String json = new String(Files.readAllBytes(path));
            return Car.deserialize(json);
        } catch (IOException e) {
            throw new RuntimeException("Error extracting Car object from file", e);
        }
    }
}
// END
