package sorting;

public class HeapSort {

	private static void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}

	private static void maxHeapify(int[] input, int i, int heapSize) {

		int left = i * 2;
		int right = i * 2 + 1;
		int indexOfMax;

		if(left < heapSize) {
			if(right < heapSize) {
				if (input[left] > input[right]) {
					indexOfMax = left;
				} else {
					indexOfMax = right;
				}
			} else {
				indexOfMax = left;
			}
			if(input[indexOfMax] > input[i]) {
				swap(input, indexOfMax, i);
				maxHeapify(input, indexOfMax, heapSize);
			}
		}
	}

	private static void buildMaxHeap(int[] input, int heapSize) {
		for(int i = heapSize/2; i >=0 ; i--) {
			maxHeapify(input, i, heapSize);
		}
	}

	private static void sort(int[] input) {
		buildMaxHeap(input, input.length);
		for(int i = input.length-1; i >= 1; i--) {
			swap(input, i, 0);
			maxHeapify(input, 0, i);
		}
	}

	public static void main(String[] args) {

		int[] input = new int[] { 2, 4, 6, 3, 8, 6, 1, 2, 1 };

		sort(input);
		for (Integer element : input) {
			System.out.print(element + " ");
		}
	}

}
