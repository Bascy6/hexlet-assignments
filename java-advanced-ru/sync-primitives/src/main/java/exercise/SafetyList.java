package exercise;

class SafetyList {
    // BEGIN
    private int[] array = new int[10];
    private int size;

    public int getSize() {
        return size;
    }

    public int get(int index) {
        return array[index];
    }

    public synchronized void add(int element) {
        if (array.length == size) {
            int[] extendedArray = new int[array.length * 2];
            System.arraycopy(array, 0, extendedArray, 0, array.length);
            array = extendedArray;
        }
        array[size++] = element;
    }
    // END
}
