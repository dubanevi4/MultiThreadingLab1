package lt.esdc.multithreading.lab1.task4;

public class MainThreadTask4 {

    public static final int THNUM = 5;

    public static void main(String[] args) {

        String[][] sequences = {
                {"A", "B", "C"},
                {"D", "E", "F"},
                {"1", "2", "3"},
                {"Red", "Green", "Blue"},
                {"First", "Second", "Third"}
        };

        for (int i = 0; i < THNUM; i++) {
            Thread thread = new Thread(new WalkThreadTask4(sequences[i]));
            thread.start();
        }
    }
}
