# Sorting algorithms

## Params of classification

The sorting algorithms can be classified in terms of time complexity, space complexity, stability, internal vs external sort, and recursive vs non-recursive approach.

### Time complexity

Classify the sorting algorithm according to the relation between the numbers of the input and the units of time spent to perform the sorting. This classificarions uses the BigO notation.

### Space complexity

Classify the sorting algorithm according to the relation between the numbers of the input and the units of memory used to perform the sorting. This classificarions uses the BigO notation.

### Stability

Classify as stable when the sorting algorithm preserves the original order between items with tie ordering. E.g, when the sorting algorithm convers `[6, 9, 3, 6*]` into `[3, 6*, 6, 9]`, it is unstable because 6 and 6* changes are tied in the sorting, but they switch its positions in the sorted list. A stable algorithm converts it to `[3, 6, 6*, 9]`.

### Internal vs External sort

Classify the sorting algorith between the memory usage: RAM or rigid disk. The first one can be used when all the memory usage fits in the RAM, but to sort a large volume of data, there are sorting algorithms that use external memory.

### Resursive or non-recursive

Classify the sorting algorithm between recursive or not.

## Selection sort

```java
static int[] sort(int[] list) {
    for (int i = 0; i < list.length; i++) {
        int min = Integer.MAX_VALUE;  
        int index = -1;   
        for (int j = i + 1; j < list.length; j++) { 
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
```

#### Time complexity

$$
TimeComplexity.SelectionSort(n) = n((n-1)c'') + nc' + C\\
= n(nc'' - c'') + nc' + C\\
= n^2c'' - nc'' + nc' + C\\
= n^2c'' + n(-c'' + c') + C → n^2 + n + C\\
TimeComplexity.SelectionSort(n) = BigO(n^2)
$$

## Bubble  sort

```java
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
```

#### Time complexity

$$
TimeComplexity.BubbleSort(n) = (n-1)(c') + (n-1)((n-1)c'')\\
= nc'- c' + (n-1)(nc''-c'')+C\\
= n^2c''-nc''-nc''+c''+nc'-c'+C\\
= n^2c''-2nc''+nc'+c''-c'+C → n^2c''+n(-2c''+c')+C\\
= n^2+n+C\\
TimeComplexity.BubbleSort(n) = BigO(n^2)
$$

## Insertion Sort

```java
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
```

#### Time complexity

$$
TimeComplexity.InsertionSort = (n-1)c'+kc''\\
k=1+2+3+...+(n-2)→{(n-2)\over2}(n-2+1)\\
k={n^2-3n+2\over2}\\
TimeComplexity.InsertionSort = nc'-c'+{n^2-3n+2\over2}c''\\
= {c''\over2}n^2-{3c''\over2}n+c''+nc'-c'\\
= {c''\over2}n^2-n({3c''\over2}+c')+c''-c'\\
= n^2-n+C\\
TimeComplexity.InsertionSort = BigO = n^2
$$

## Merge sort

```java
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
```

### Time complexity

$$
TimeComplexity.MergeSort(n) = M(n)\\
M(n)=
\left\{
\begin{array}{l}
  2M({n\over2})+n(c')+c'',\ when\ n > 1\\ 
  C,\ when\ n = 1
\end{array}
\right\}\\

→ M(n) = 2(2M({n\over16})+{n(c')\over4}+{c''\over4}) = 4M({n\over4})+{n(c')\over4}+{c''\over4}\\
→ M(n) = 8M({n\over8})+{n(c')\over8}+{c''\over8}\\
→ M(n) = 16M({n\over16})+{n(c')\over16}+{c''\over16}\\
→ M(n) = 2^kM({n\over2^k})+{n(c')\over2^k}+{c''\over2^k}\\
{n\over2^k} = logn\\
TimeComplexity.MergeSort(n) = BigO(n logn)\\
$$

## Quick sort

```java
static void quickSortAux(int[] list, int start, int end) {
    if (start < end) {
        // partitioning
        int pivot = list[new Random().nextInt(start, end)]; // random pivot help us to more close to BigO(nlogn) than BigO(n^2)
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
```

## Heap sort

## Counting sort

## Radiz sort
