package sorting;

public class MergeSort {

	/**
	 * Complexity O(N*log N)
	 *
	 * @param input
	 * @param start
	 * @param end
	 */
	private static void sort(int[] input, int start, int end) {

		if(start >= end) {
			return;
		}

		if(end - start == 1) {
			if(input[start] > input[end]) {
				swap(input, start, end);
			}
			return;
		}

		int mid = start + (end - start)/2;

		sort(input, start, mid);
		sort(input, mid+1, end);

		merge(input, start, mid, end);
	}

	private static void merge(int[] input, int start, int mid, int end) {

		int[] left = new int[mid - start + 1];
		for(int i = start; i <= mid; i++) {
			left[i-start] = input[i];
		}

		int[] right = new int[end - mid];
		for(int i = mid+1; i <= end; i++) {
			right[i - mid - 1] = input[i];
		}

		int i = 0;
		int j = 0;

		for(int k = start; k <= end; k++) {

			if(i < left.length && j >= right.length) {
				input[k] = left[i];
				i++;
				continue;
			}

			if(i >= left.length && j < right.length) {
				input[k] = right[j];
				j++;
				continue;
			}

			if(left[i] <= right[j]) {
				input[k] = left[i];
				i++;
			} else {
				input[k] = right[j];
				j++;
			}
		}

	}

	private static void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}

	public static void main(String[] args) {

		int[] input = new int[]{2,4,6,3,8,6,1,2,1};

		sort(input, 0, input.length-1);
		for(Integer element : input) {
			System.out.print(element + " ");
		}

	}
}
