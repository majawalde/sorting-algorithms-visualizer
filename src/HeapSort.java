import javax.swing.*;

public class HeapSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array, SortingPanel panel, int speed) throws InterruptedException {
        int n = array.length;


        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i, panel, speed);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            int finalI = i;
            SwingUtilities.invokeLater(() -> panel.setArray(array, 0, finalI, finalI));
            Thread.sleep(speed);

            heapify(array, i, 0, panel, speed);
        }

        panel.setArray(array, -1, -1, n - 1);
    }

    void heapify(int[] array, int n, int i, SortingPanel panel, int speed) throws InterruptedException {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && array[l] > array[largest]) {
            largest = l;
        }

        if (r < n && array[r] > array[largest]) {
            largest = r;
        }

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(array, n, largest, panel, speed);
        }

        final int finalCurrentIndex = i;
        final int finalLargestIndex = largest;
        final int finalSortedIndex = array.length - n - 1;
        SwingUtilities.invokeLater(() -> panel.setArray(array, finalCurrentIndex, finalLargestIndex, finalSortedIndex));
        Thread.sleep(speed);
    }
}

