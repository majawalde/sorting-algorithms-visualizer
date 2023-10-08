import javax.swing.*;

public class QuickSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array, SortingPanel panel, int speed) {
        quickSort(array, 0, array.length - 1, panel, speed);
        // Clear highlighting and mark whole array as sorted
        panel.setArray(array, -1, -1, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high, SortingPanel panel, int speed) {
        if (low < high) {
            int pivot = partition(array, low, high, panel, speed);
            quickSort(array, low, pivot - 1, panel, speed);
            quickSort(array, pivot + 1, high, panel, speed);
        }
    }

    private int partition(int[] array, int low, int high, SortingPanel panel, int speed) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;

                try {
                    final int finalCurrentIndex = i;
                    final int finalSortedIndex = j;
                    SwingUtilities.invokeLater(() -> panel.setArray(array, finalCurrentIndex, finalSortedIndex, high));
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        try {
            final int finalCurrentIndex = i + 1;
            final int finalSortedIndex = high;
            SwingUtilities.invokeLater(() -> panel.setArray(array, finalCurrentIndex, finalSortedIndex, high));
            Thread.sleep(speed);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return i + 1;
    }
}
