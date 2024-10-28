package com.smirnov;

import java.util.Comparator;

/**
 * Реализует сортировку слиянием.
 */
public abstract class MergeSort {

    private MergeSort() {
    }

    public static <T> void mergeSort(T[] array, Comparator<? super T> comparator) {
        int length = array.length;
        if (length < 2) {
            return;
        }
        int mid = length / 2;
        T[] left = (T[]) new Object[mid];
        T[] right = (T[]) new Object[length - mid];
        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, length - mid);
        mergeSort(left, comparator);
        mergeSort(right, comparator);
        merge(array, left, right, comparator);
    }

    private static <T> void merge(T[] array, T[] left, T[] right, Comparator<? super T> comparator) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            array[k++] = comparator.compare(left[i], right[j]) <= 0 ? left[i++] : right[j++];
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
}
