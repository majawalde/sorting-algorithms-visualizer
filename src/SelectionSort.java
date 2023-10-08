import javax.swing.*;

public class SelectionSort implements SortingAlgorithm {
    @Override
    public void sort(int[] array, SortingPanel panel, int speed) {
        for (int sortedIndex = 0; sortedIndex < array.length - 1; sortedIndex++) {
            int minIndex = sortedIndex;
            for (int currentIndex = sortedIndex + 1; currentIndex < array.length; currentIndex++) {
                if (array[currentIndex] < array[minIndex]) {
                    minIndex = currentIndex;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[sortedIndex];
            array[sortedIndex] = temp;

            try {
                final int finalCurrentIndex = minIndex;
                final int finalSortedIndex = sortedIndex;
                SwingUtilities.invokeLater(() -> panel.setArray(array, finalCurrentIndex, finalSortedIndex, array.length - 1));
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Clear highlighting and mark the whole array as sorted
        panel.setArray(array, -1, -1, array.length - 1);
    }
}
