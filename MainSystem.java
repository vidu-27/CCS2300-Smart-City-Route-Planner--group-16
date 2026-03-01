import java.util.*;

public class MainSystem {
    private static Scanner sc = new Scanner(System.in);
    
    // Instances of each member's module
    private static SmartCityGraph city = new SmartCityGraph();      // Member 1
    private static DataSorter sorter = new DataSorter();            // Member 2
    private static LocationManagerAVL avlManager = new LocationManagerAVL(); // Member 3

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n==============================================");
            System.out.println("   CCS2300: DATA STRUCTURES & ALGORITHMS");
            System.out.println("           Smart city route planner");
            System.out.println("==============================================");
            System.out.println("1. [Module 1] Smart City Route Planner");
            System.out.println("2. [Module 2] Sorting Algorithm Tool");
            System.out.println("3. [Module 3] Algorithm Performance Analyzer");
            System.out.println("4. Exit System");
            System.out.print("\nPlease select an option (1-4): ");

            int choice = getValidChoice();

            switch (choice) {
                case 1:
                    runSmartCityModule();
                    break;
                case 2:
                    // Directly calls Member 2's start method
                    sorter.start();
                    break;
                case 3:
                    runPerformanceAnalyzer();
                    break;
                case 4:
                    System.out.println("Terminating system... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * MODULE 1: SMART CITY ROUTE PLANNER
     * Combines Member 1's Graph logic with Member 3's AVL storage
     */
    private static void runSmartCityModule() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- MODULE 1: SMART CITY PLANNER ---");
            System.out.println("1. Add New Location");
            System.out.println("2. Add Road (Connection)");
            System.out.println("3. Remove Road");
            System.out.println("4. Display City Map");
            System.out.println("5. Search Location (AVL Tree Search)");
            System.out.println("6. Traverse Route (BFS)");
            System.out.println("7. Back to Main Menu");
            System.out.print("Choice: ");

            int m1Choice = getValidChoice();
            switch (m1Choice) {
                case 1:
                    System.out.print("Enter Location Name: ");
                    String loc = sc.next();
                    city.addLocation(loc);      // Member 1 Logic
                    avlManager.insert(loc);     // Member 3 Logic (Balanced Storage)
                    break;
                case 2:
                    System.out.print("Source: "); String src = sc.next();
                    System.out.print("Destination: "); String dest = sc.next();
                    city.addRoad(src, dest);
                    break;
                case 3:
                    System.out.print("Source: "); String s = sc.next();
                    System.out.print("Destination: "); String d = sc.next();
                    city.removeRoad(s, d);
                    break;
                case 4:
                    city.displayConnection();
                    break;
                case 5:
                    System.out.print("Enter Location to search: ");
                    String find = sc.next();
                    boolean exists = avlManager.search(find); // Member 3 Search
                    System.out.println(exists ? "SUCCESS: Location found in registry." : "ERROR: Location not found.");
                    break;
                case 6:
                    System.out.print("Start Location: ");
                    city.traverseBFS(sc.next());
                    break;
                case 7:
                    back = true;
                    break;
            }
        }
    }

    /**
     * MODULE 3: PERFORMANCE ANALYZER
     * Uses Member 3's Binary Search to test complexity
     */
    private static void runPerformanceAnalyzer() {
        System.out.println("\n--- MODULE 3: PERFORMANCE ANALYZER ---");
        System.out.println("Comparing Binary Search performance across input sizes...");
        
        int[] sizes = {100, 500, 1000};
        Random rand = new Random();

        System.out.printf("%-10s | %-20s\n", "Size", "Binary Search Time (ns)");
        System.out.println("------------------------------------------");

        for (int size : sizes) {
            // Generate sorted array for Binary Search
            int[] testData = rand.ints(size, 0, 10000).sorted().toArray();
            int target = testData[rand.nextInt(size)];

            long startTime = System.nanoTime();
            avlManager.binarySearch(testData, target); // Member 3 Algorithm
            long endTime = System.nanoTime() - startTime;

            System.out.printf("%-10d | %-20d\n", size, endTime);
        }
        System.out.println("\nAnalysis: Binary Search maintains O(log n) efficiency.");
    }

    private static int getValidChoice() {
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number.");
            sc.next();
        }
        return sc.nextInt();
    }
}