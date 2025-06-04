package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {
    private int[] numbers;
    private int min;

    MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        System.out.println("INFO: Thread " + this.getName() + " started");
        int result = Arrays.stream(numbers).min().getAsInt();
        min = result;
        System.out.println("INFO: Thread " + this.getName() + " finished");
    }

    public int getMin() {
        return min;
    }
}
// END
