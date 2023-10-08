import javax.swing.*;

public class MergeSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array, SortingPanel panel, int speed) {
        mergeSort(array, 0, array.length - 1, panel, speed);
        // Clear highlighting and mark whole array as sorted
        panel.setArray(array, -1, -1, array.length - 1);
    }

    void mergeSort(int[] array, int left, int right, SortingPanel panel, int speed) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergeSort(array, left, middle, panel, speed);
            mergeSort(array, middle + 1, right, panel, speed);

            merge(array, left, middle, right, panel, speed);
        }
    }

    void merge(int[] array, int left, int middle, int right, SortingPanel panel, int speed) {
        int[] leftArray = new int[middle - left + 1];
        int[] rightArray = new int[right - middle];

        System.arraycopy(array, left, leftArray, 0, leftArray.length);
        for (int i = 0; i < rightArray.length; i++) {
            rightArray[i] = array[middle + 1 + i];
        }

        int leftIndex = 0, rightIndex = 0;

        for (int i = left; i <= right; i++) {
            try {
                final int finalCurrentIndex = i;
                final int compareIndex = (leftIndex < leftArray.length && rightIndex < rightArray.length)
                        ? ((leftArray[leftIndex] <= rightArray[rightIndex])
                        ? left + leftIndex
                        : middle + 1 + rightIndex)
                        : -1; // Use -1 when there is no comparison
                SwingUtilities.invokeLater(() -> panel.setArray(array, finalCurrentIndex, compareIndex, right));
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
                if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < leftArray.length) {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }

}
