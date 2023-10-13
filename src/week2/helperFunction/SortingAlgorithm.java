package week2.helperFunction;

public interface SortingAlgorithm {
    void bubbleSort(int[] array);
    void mergeSort(int[] array, int left, int right);
    void quickSort(int[] array, int low, int high);
}
