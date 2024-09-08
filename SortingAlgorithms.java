import java.util.Arrays;
import java.util.Random;

class SortingAlgorithms {
    public static void main(String[] args) {
        int[][] unsorteds = new int[][] {
                { 1, 3, 2, 6, 2, 9, 0 },
                { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 },
                { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 },
                randomList(100),
                randomList(1000),
                randomList(10000),
                randomList(100000),
                randomList(1000000)
        };

        for (int i = 0; i < unsorteds.length; i++) {
            runSort("Selection Sort", unsorteds[i], (u) -> selectionSort(u));
            runSort("Bubble Sort", unsorteds[i], (u) -> boubleSort(u));
            runSort("Insertion Sort", unsorteds[i], (u) -> insertionSort(u));
            System.out.println("===========================================================\n");
        }
    }

    static int[] randomList(int n) {
        Random random = new Random();
        int[] randomList = new int[n];
        for (int i = 0; i < n; i++) {
            randomList[i] = random.nextInt();
        }
        return randomList;
    }

    static void runSort(String algorithmName, int[] unsorted, Sorteable sorteable) {
        long t1 = System.currentTimeMillis();
        int[] sorted = sorteable.sort(unsorted);
        long tSpent = System.currentTimeMillis() - t1;

        System.out.printf(
            "[ALGORITHM]:    %s\n[LIST_LENGTH]:  %d\n[TIME]:         %dms\n[SORTED_ARRAY]: %s\n\n",
            algorithmName,
            sorted.length,
            tSpent,
            sorted.length <= 20 ? Arrays.toString(sorted) : "undefined");
    }

    static int[] selectionSort(int[] list) {
        for (int i = 0; i < list.length; i++) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            for (int j = i; j < list.length; j++) {
                if (list[j] <= min) {
                    min = list[j];
                    index = j;
                }
            }
            list[index] = list[i];
            list[i] = min;
        }
        return list;
    }

    static int[] boubleSort(int[] list) {
        for (int j = 0; j < list.length - 1; j++) {
            boolean sorted = true;
            for (int i = 0; i < list.length - 1; i++) {
                if (list[i] > list[i + 1]) {
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
        return list;
    }

    static int[] insertionSort(int[] list) {
        for (int i = 1; i < list.length; i++) {
            int value = list[i];
            for (int j = i - 1; j >= 0; j--) {
                if (value < list[j]) {
                    list[j + 1] = list[j];
                    list[j] = value;
                } else {
                    break;
                }
            }
        }
        return list;
    }
}

interface Sorteable {
    int[] sort(int[] unsorted);
}
