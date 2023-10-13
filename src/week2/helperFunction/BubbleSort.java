package week2.helperFunction;

public class BubbleSort implements SortingAlgorithm{
    @Override
    public void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (array[i - 1] > array[i]) {
                    // Swap elements if they are in the wrong order
                    int temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    @Override
    public void mergeSort(int[] array, int left, int right) {

    }

    @Override
    public void quickSort(int[] array, int low, int high) {

    }
}
