import javax.swing.*;

public class HeapSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array, SortingPanel panel, int speed) throws InterruptedException {
        int n = array.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i, panel, speed);
        }

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Display this state
            int finalI = i;
            SwingUtilities.invokeLater(() -> panel.setArray(array, 0, finalI, finalI));
            Thread.sleep(speed);

            // Call max heapify on the reduced heap
            heapify(array, i, 0, panel, speed);
        }

        // Clear highlighting and mark whole array as sorted
        panel.setArray(array, -1, -1, n - 1);
    }

    void heapify(int[] array, int n, int i, SortingPanel panel, int speed) throws InterruptedException {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && array[l] > array[largest]) {
            largest = l;
        }

        // If right child is larger than largest so far
        if (r < n && array[r] > array[largest]) {
            largest = r;
        }

        // If largest is not root
        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(array, n, largest, panel, speed);
        }

        // Highlighting the current and largest for comparison and final sorted index for marking sorted segments
        final int finalCurrentIndex = i;
        final int finalLargestIndex = largest;
        final int finalSortedIndex = array.length - n - 1;
        SwingUtilities.invokeLater(() -> panel.setArray(array, finalCurrentIndex, finalLargestIndex, finalSortedIndex));
        Thread.sleep(speed);
    }
}

