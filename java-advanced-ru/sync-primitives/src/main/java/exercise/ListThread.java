package exercise;

// BEGIN
public class ListThread implements Runnable {
    private SafetyList list;

    ListThread(SafetyList list) {
        this.list = list;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(i);
        }
    }
}
// END
