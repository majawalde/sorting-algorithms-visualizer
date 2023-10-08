public class SortingAlgorithmFactory {
    public static SortingAlgorithm create(String algorithmName) {
        return switch (algorithmName) {
            case "Bubble Sort" -> new BubbleSort();
            case "Insertion Sort" -> new InsertionSort();
            case "Selection Sort" -> new SelectionSort();
            case "Merge Sort" -> new MergeSort();
            case "Quick Sort" -> new QuickSort();
            case "Heap Sort" -> new HeapSort();
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithmName);
        };
    }
}
