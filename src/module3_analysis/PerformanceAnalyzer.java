package module3_analysis;

import java.util.Random;
import util.TimeUtil;

public class PerformanceAnalyzer {

    public static void start() {
        int[] sizes = {100, 500, 1000};

        System.out.println("Input-Size | Search-Time (ns) | Sort-Time (ns)");
        System.out.println("---------------------------------------------");

        for (int size : sizes) {
            int[] data = new Random().ints(size, 1, 10000).toArray();
            int key = data[size / 2];

            long startSearch = TimeUtil.start();
            LinearSearch.search(data, key);
            long searchTime = TimeUtil.end(startSearch);

            long startSort = TimeUtil.start();
            bubbleSort(data.clone());
            long sortTime = TimeUtil.end(startSort);

            System.out.printf("%-10d | %-15d | %-13d%n", size, searchTime, sortTime);
        }
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
