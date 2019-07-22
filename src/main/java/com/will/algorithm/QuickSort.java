package com.will.algorithm;

public class QuickSort {

	public static void sort(int[] array, int low, int high) {
		int key = array[low];
		int start = low, end = high;
		while (start < end) {
			while(start < end && array[end] >= key) {
				end--;
			}
			if (array[end] <= key) {
				int temp = array[end];
				array[end] = array[start];
				array[start] = temp;
			}
			while(start < end && array[start] <= key) {
				start++;
			}
			if (array[start] >= key) {
				int temp = array[start];
				array[start] = array[end];
				array[end] = temp;
			}
		}
		if (start > low) 
			sort(array, low, start - 1);
		if (end < high)
			sort(array, end + 1, high);
	}
	
	public static void main(String[] args) {
		int[] array = {6, 3, 5, 2, 4, 9, 10, 1, 11, 8};
		quicksort(array, 0 , array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }
	
	public static void quicksort(int[] array, int low, int high) {
		int key = array[low];
		int start = low, end = high;
		while (start < end) {
			while(start < end && array[end] >= key) {
				end--;
			}
			while(start < end && array[start] <= key) {
				start++;
			}
			if (start < end) {
				int temp = array[start];
				array[start] = array[end];
				array[end] = temp;
			}
		}
		
		int temp = array[start];
		array[start] = array[low];
		array[low] = temp;

		if (start > low) 
			quicksort(array, low, start - 1);
		if (end < high)
			quicksort(array, end + 1, high);
	}
}
