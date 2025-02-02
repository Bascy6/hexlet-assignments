package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int n) {
        List<Home> sortedAps = apartments.stream()
                .sorted((x, y) -> Double.compare(x.getArea(), y.getArea()))
                .toList();

        return sortedAps.stream()
                .limit(n)
                .map(Home::toString)
                .toList();
    }
}
// END
