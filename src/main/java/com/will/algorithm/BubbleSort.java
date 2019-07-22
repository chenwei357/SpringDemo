package com.will.algorithm;

public class BubbleSort {

	public static void sort(int[] array) {
		for (int i = 0, len = array.length; i < len -1; i++) {
			for (int j = 1; j < len - i; j++) {
				if (array[j - 1] > array[j]) {
					int temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
				}
			}
		}
		//return array;
	}
	
	public static void main(String[] args) {
		int[] array = {1, 3, 5, 2, 4, 9, 10, 6, 11, 8};
		sort(array);
		for (int i = 0, len = array.length; i < len; i++) {			
			System.out.print(array[i] + ",");
		}
	}
	
}
