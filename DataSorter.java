import java.util.*;

public class DataSorter {

    private int[] data;
    private Scanner scanner;

    public DataSorter() {
        scanner = new Scanner(System.in);
    }

 
    public void start() {

        while (true) {
            System.out.println("\n===== DATA SORTER – Sorting Algorithm Comparison Tool =====");
            System.out.println("1. Enter numbers manually");
            System.out.println("2. Generate random dataset");
            System.out.println("3. Run Sorting Comparison");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    data = manualInput();
                    break;

                case 2:
                    data = generateRandomData();
                    break;

                case 3:
                    if (data == null) {
                        System.out.println("Please enter or generate data first!");
                    } else {
                        runComparison();
                    }
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }


    private int[] manualInput() {
        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter numbers:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        return arr;
    }


    private int[] generateRandomData() {
        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        Random rand = new Random();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(1000);
        }

        System.out.println("Random dataset generated.");
        return arr;
    }

    private void runComparison() {

        int[] bubbleArr = data.clone();
        int[] mergeArr = data.clone();
        int[] quickArr = data.clone();

        long start, end;

        start = System.nanoTime();
        bubbleSort(bubbleArr);
        end = System.nanoTime();
        long bubbleTime = end - start;

        start = System.nanoTime();
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        end = System.nanoTime();
        long mergeTime = end - start;

        start = System.nanoTime();
        quickSort(quickArr, 0, quickArr.length - 1);
        end = System.nanoTime();
        long quickTime = end - start;

        System.out.println("\nSorted Output (Quick Sort):");
        printArray(quickArr);

        System.out.println("\n===== Performance Comparison=====");
        System.out.printf("%-15s %-20s\n", "Algorithm", "Execution Time");
        System.out.printf("%-15s %-20d\n", "Bubble Sort", bubbleTime);
        System.out.printf("%-15s %-20d\n", "Merge Sort", mergeTime);
        System.out.printf("%-15s %-20d\n", "Quick Sort", quickTime);
    }


    private void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }
            }

            if (!swapped)
                break;
        }
    }


    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {

            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {

        int[] L = Arrays.copyOfRange(arr, left, mid + 1);
        int[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < L.length && j < R.length) {
            arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        }

        while (i < L.length)
            arr[k++] = L[i++];

        while (j < R.length)
            arr[k++] = R[j++];
    }


    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    private void printArray(int[] arr) {
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }
}