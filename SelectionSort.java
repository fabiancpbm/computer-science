import java.util.Arrays;

class SelectionSort {
    public static void main(String[] args) {
        int[][] unsorteds = new int[][]{
            {1, 3, 2, 6, 2, 9, 0},
            {10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
            {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 9}
        };

        for (int i = 0; i < unsorteds.length; i++) {
            long t1 = System.currentTimeMillis();
            int[] sorted = sort(unsorteds[i]);
            long tSpent = System.currentTimeMillis() - t1;
            System.out.printf("[RESULT] %s\n[TIME] %d\n\n", Arrays.toString(sorted), tSpent);
        }
    }

    public static int[] sort(int[] list) {
        for (int i = 0; i < list.length; i++) {
            int min = Integer.MAX_VALUE;                // C1 = 1
            int index = -1;                             // C1 = 1
            for (int j = i + 1; j < list.length; j++) { // C3 = (n-1)C2 + (n-2)C2 + (n-3)C2 + ... + 1C2 => C3 = n/2*(n - 1)C2
                if (list[j] <= min) {                   // C2 = 1
                    min = list[j];                      // C2 = 1
                    index = j;                          // C2 = 1
                }
            }
            list[index] = list[i];                      // C1 = 1
            list[i] = min;                              // C1 = 1
        }
        return list;
    }
    // C3 + 3C1 => n/2*(n-1)C2 + 3C1 => (nˆ2 - n)/2 * C2 + 3C1 => nˆ2(C2) - n(C2) - C3 => O(nˆ2)
}
