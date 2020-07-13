package org.example.algorithm.template.sort;

import java.util.List;

/**
 * 퀵소트 구현
 *
 * @author Changhee Choi
 * @since 13/07/2020
 */
public class QuickSort {
    //Array 타입
    private <T extends Comparable<T>> int partition(T[] arr, int start, int end) {
        T pivot = arr[(start + end) / 2];

        while (start <= end) {
            while (arr[start].compareTo(pivot) < 0) start++;
            while (arr[end].compareTo(pivot) > 0) end--;

            if (start <= end) {
                T temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            }
        }

        return start;
    }

    public <T extends Comparable<T>> void sort(T[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private <T extends Comparable<T>> void sort(T[] arr, int start, int end) {
        int pivotIdx = partition(arr, start, end);

        if (start < pivotIdx - 1) {
            sort(arr, start, pivotIdx - 1);
        }
        if (pivotIdx < end) {
            sort(arr, pivotIdx, end);
        }
    }

    //List 타입
    private <T extends Comparable<T>> int partition(List<T> list, int start, int end) {
        T pivot = list.get((start + end) / 2);

        while (start <= end) {
            while (list.get(start).compareTo(pivot) < 0) start++;
            while (list.get(end).compareTo(pivot) > 0) end--;

            if (start <= end) {
                T temp = list.get(start);
                list.set(start, list.get(end));
                list.set(end, temp);
                start++;
                end--;
            }
        }

        return start;
    }

    public <T extends Comparable<T>> void sort(List<T> list) {
        sort(list, 0, list.size() - 1);
    }

    private <T extends Comparable<T>> void sort(List<T> list, int start, int end) {
        int pivotIdx = partition(list, start, end);

        if (start < pivotIdx - 1) {
            sort(list, start, pivotIdx - 1);
        }
        if (pivotIdx < end) {
            sort(list, pivotIdx, end);
        }
    }
}
