import java.util.Arrays;
import java.util.Random;

class SortingAlgorithms {
    public static void main(String[] args) {
        int[][] unsorteds = new int[][] {
                { 1, 3, 2, 6, 2, 9, 0 },
                { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 },
                { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 },
                randomList(100000),
                invertedOrderList(100000),
        };

        for (int i = 0; i < unsorteds.length; i++) {
            runSort("Selection Sort", unsorteds[i], (u) -> selectionSort(u));
            runSort("Bubble Sort", unsorteds[i], (u) -> boubleSort(u));
            runSort("Insertion Sort", unsorteds[i], (u) -> insertionSort(u));
            runSort("Merge Sort", unsorteds[i], (u) -> mergeSort(u));
            runSort("Quick Sort", unsorteds[i], (u) -> quickSort(u));
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

    static int[] invertedOrderList(int n) {
        int[] randomList = new int[n];
        int value = n;
        for (int i = 0; i < n; i++) {
            randomList[i] = value;
            value--;
        }
        return randomList;
    }

    static void runSort(String algorithmName, int[] unsorted, Sorteable sorteable) {
        long t1 = System.currentTimeMillis();
        int[] sorted = sorteable.sort(Arrays.copyOf(unsorted, unsorted.length));
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

    static int[] mergeSort(int[] list) {
        // split
        if (list.length < 2) {
            return list;
        }
        int mid = list.length / 2;
        int[] left = new int[mid];
        for (int i = 0; i < left.length; i++) {
            left[i] = list[i];
        }
        int[] right = new int[list.length - mid];
        for (int i = 0; i < right.length; i++) {
            right[i] = list[i + mid];
        }
        left = mergeSort(left);
        right = mergeSort(right);

        // merge
        int currentLeftIndex = 0;
        int currentRightIndex = 0;
        for (int i = 0; i < list.length; i++) {
            if (left.length == currentLeftIndex) {
                list[i] = right[currentRightIndex];
                currentRightIndex++;
            } else if (right.length == currentRightIndex) {
                list[i] = left[currentLeftIndex];
                currentLeftIndex++;
            } else if (left[currentLeftIndex] < right[currentRightIndex]) {
                list[i] = left[currentLeftIndex];
                currentLeftIndex++;
            } else {
                list[i] = right[currentRightIndex];
                currentRightIndex++;
            }
        }
        return list;
    }

    static void quickSortAux(int[] list, int start, int end) {
        if (start < end) {
            // partitioning
            int pivot = list[new Random().nextInt(start, end)];
            int pIndex = start;
            for (int i = start; i < end - 1; i++) {
                if (list[i] <= pivot) {
                    int beforePivot = list[i];
                    list[i] = list[pIndex];
                    list[pIndex] = beforePivot;
                    pIndex++;
                }
            }
            list[end - 1] = list[pIndex];
            list[pIndex] = pivot;

            // split
            quickSortAux(list, start, pIndex);
            quickSortAux(list, pIndex + 1, end);
        }
    }

    static int[] quickSort(int[] list) {
        quickSortAux(list, 0, list.length);
        return list;
    }
}

interface Sorteable {
    int[] sort(int[] unsorted);
}
