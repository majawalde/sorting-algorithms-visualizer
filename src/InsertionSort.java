import javax.swing.*;

public class InsertionSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array, SortingPanel panel, int speed) {
        for (int sortedIndex = 1; sortedIndex < array.length; sortedIndex++) {
            int current = array[sortedIndex];
            int currentIndex = sortedIndex;


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


            try {
                int finalCurrentIndex = currentIndex;
                int finalSortedIndex = sortedIndex;
                SwingUtilities.invokeLater(() -> panel.setArray(array, finalCurrentIndex, -1, finalSortedIndex));
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        panel.setArray(array, -1, -1, array.length - 1);
    }
}

