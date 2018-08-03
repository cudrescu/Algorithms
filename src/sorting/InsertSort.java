package sorting;

public class InsertSort {

	/**
	 * Complexity O(N*N)
	 *
	 * @param array
	 * @return
	 */
	private static int[] sortAscending(int[] array) {

		if(array == null || array.length == 0) {
			return new int[0];
		}

		for(int i = 1; i < array.length; i++) {
			int key = array[i];
			int j = i - 1;
			while(j >= 0 && array[j] > key) {
				array[j + 1] = array[j];
				j --;
			}
			array[j + 1] = key;
		}

		return array;
	}

	private static int[] sortDescending(int[] array) {

		if(array == null || array.length == 0) {
			return new int[0];
		}

		for(int i = 1; i < array.length; i++) {
			int key = array[i];
			int j = i - 1;
			while(j >= 0 && array[j] < key) {
				array[j + 1] = array[j];
				j --;
			}
			array[j + 1] = key;
		}

		return array;
	}

	public static void main(String[] args) {

		int[] input = new int[]{2,4,6,3,8,6,1,2,1};

		input = sortAscending(input);
		System.out.println("Ascending:");
		for(Integer element : input) {
			System.out.print(element + " ");
		}

		System.out.println();

		input = sortDescending(input);
		System.out.println("Descending:");
		for(Integer element : input) {
			System.out.print(element + " ");
		}

	}

}
