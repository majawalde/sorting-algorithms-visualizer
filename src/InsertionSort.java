import javax.swing.*;

public class InsertionSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array, SortingPanel panel, int speed) {
        for (int sortedIndex = 1; sortedIndex < array.length; sortedIndex++) {
            int current = array[sortedIndex];
            int currentIndex = sortedIndex;

            // Highlight the current element being inserted
            try {
                int finalCurrentIndex3 = currentIndex;
                int finalSortedIndex2 = sortedIndex;
                SwingUtilities.invokeLater(() -> panel.setArray(array, finalCurrentIndex3, -1, finalSortedIndex2 - 1));
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            while (currentIndex > 0 && array[currentIndex - 1] > current) {
                array[currentIndex] = array[currentIndex - 1];
                currentIndex--;

                // Highlight the current element and the element being compared
                try {
                    int finalCurrentIndex1 = currentIndex;
                    int finalCurrentIndex2 = currentIndex;
                    int finalSortedIndex1 = sortedIndex;
                    SwingUtilities.invokeLater(() -> panel.setArray(array, finalCurrentIndex1, finalCurrentIndex2 - 1, finalSortedIndex1 - 1));
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            array[currentIndex] = current;

            // Highlight the element in its correct position
            try {
                int finalCurrentIndex = currentIndex;
                int finalSortedIndex = sortedIndex;
                SwingUtilities.invokeLater(() -> panel.setArray(array, finalCurrentIndex, -1, finalSortedIndex));
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Clear highlighting and mark whole array as sorted
        panel.setArray(array, -1, -1, array.length - 1);
    }
}

