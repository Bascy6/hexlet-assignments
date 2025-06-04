package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        Map<String, Integer> result = new HashMap<>();
        MaxThread threadForMax = new MaxThread(numbers);
        MinThread threadForMin = new MinThread(numbers);
        threadForMax.start();
        threadForMin.start();

        try {
            threadForMin.join();
            threadForMax.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result.put("min", threadForMin.getMin());
        result.put("max", threadForMax.getMax());

        return result;
    }
    // END
}
