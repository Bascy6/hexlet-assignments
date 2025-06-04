package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {
    private int[] numbers;
    private int max;

    MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        System.out.println("INFO: Thread " + this.getName() + " started");
        int result = Arrays.stream(numbers).max().getAsInt();
        max = result;
        System.out.println("INFO: Thread " + this.getName() + " finished");
    }

    public int getMax() {
        return max;
    }
}
// END
