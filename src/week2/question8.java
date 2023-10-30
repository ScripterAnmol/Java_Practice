package week2;

import week2.helperFunction.BubbleSort;
import week2.helperFunction.MergeSort;
import week2.helperFunction.QuickSort;
import week2.helperFunction.SortingAlgorithm;

import java.util.Arrays;

public class question8 {
    public static void main(String[] args) {
        int[] array = {5, 3, 9, 1, 7};

//        TODO: change interface and implement sort method
        // Bubble Sort
        SortingAlgorithm bubbleSort = new BubbleSort();
        int[] bubbleSorted = array.clone();
        bubbleSort.bubbleSort(bubbleSorted);
        System.out.println("Bubble Sort: " + Arrays.toString(bubbleSorted));

        // Merge Sort
        SortingAlgorithm mergeSort = new MergeSort();
        int[] mergeSorted = array.clone();
        mergeSort.mergeSort(mergeSorted, 0, mergeSorted.length - 1);
        System.out.println("Merge Sort: " + Arrays.toString(mergeSorted));

        // Quick Sort
        SortingAlgorithm quickSort = new QuickSort();
        int[] quickSorted = array.clone();
        quickSort.quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("Quick Sort: " + Arrays.toString(quickSorted));

        // Original array remains unchanged
        System.out.println("Original Array: " + Arrays.toString(array));
    }
}
