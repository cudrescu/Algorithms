package sorting;

public class QuickSort {

	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	private static int choosePivot(int[] array, int start, int end) {
		int mid = start + (end-start)/2;

		if(array[start] > array[end]) {
			if(array[start] > array[mid]) {
				return array[mid] > array[end] ? mid : end;
			} else {
				return start;
			}
		} else {
			if(array[end] > array[mid]) {
				return array[mid] > array[start] ? mid : start;
			} else {
				return end;
			}
		}
	}

	private static int partition(int[] array, int start, int end) {

		int pivot = choosePivot(array, start, end);
		int referenceValue = array[pivot];
		swap(array, pivot, end);

		int i = start - 1;
		for(int j = start; j < end; j++) {
			if(array[j] <= referenceValue) {
				i++;
				swap(array, i, j);
			}
		}

		swap(array, i+1, end);

		return i+1;
	}

	private static void sort(int[] array, int start, int end) {

		if(start >= end) {
			return;
		}

		int pivot = partition(array, start, end);

		sort(array, start, pivot - 1);
		sort(array, pivot + 1, end);
	}

	public static void main(String[] args) {

		int[] input = new int[] { 2, 4, 6, 3, 8, 6, 1, 2, 1 };

		sort(input, 0, input.length - 1);
		for (Integer element : input) {
			System.out.print(element + " ");
		}

	}
}
