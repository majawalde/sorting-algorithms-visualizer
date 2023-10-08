import javax.swing.*;

public class BubbleSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array, SortingPanel panel, int speed) {
        for (int sortedIndex = 0; sortedIndex < array.length - 1; sortedIndex++) {
            for (int currentIndex = 0; currentIndex < array.length - sortedIndex - 1; currentIndex++) {
                if (array[currentIndex] > array[currentIndex + 1]) {
                    int temp = array[currentIndex];
                    array[currentIndex] = array[currentIndex + 1];
                    array[currentIndex + 1] = temp;

                    try {
                        final int finalCurrentIndex = currentIndex;
                        final int finalSortedIndex = array.length - sortedIndex - 1;
                        // Adjusting the setArray call to provide both indices being compared
                        SwingUtilities.invokeLater(() -> panel.setArray(array, finalCurrentIndex, finalCurrentIndex + 1, finalSortedIndex));
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        // Clear highlighting and mark the whole array as sorted
        panel.setArray(array, -1, -1, array.length - 1);
    }

}



