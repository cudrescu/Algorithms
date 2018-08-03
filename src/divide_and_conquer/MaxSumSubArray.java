package divide_and_conquer;

public class MaxSumSubArray {

	private static int findMaxSumCrossingSubArray(int[] array, int start, int mid, int end) {

		int leftSum = Integer.MIN_VALUE;
		int rightSum = Integer.MIN_VALUE;

		int sum = 0;
		for(int i = mid; i >= start; i--) {
			sum += array[i];
			if(sum > leftSum) {
				leftSum = sum;
			}
		}

		sum = 0;
		for(int i = mid+1; i <= end; i++) {
			sum += array[i];
			if(sum > rightSum) {
				rightSum = sum;
			}
		}

		return leftSum + rightSum;
	}

	private static int findMaximumSumSubArray(int[] array, int start, int end) {

		if(start == end) {
			return array[start];
		}

		int mid = start + (end - start)/2;

		int leftSum = findMaximumSumSubArray(array, start, mid);
		int rightSum = findMaximumSumSubArray(array, mid+1, end);
		int crossSum = findMaxSumCrossingSubArray(array, start, mid, end);

		return Math.max(crossSum, Math.max(leftSum, rightSum));
	}

	public static void main(String[] args) {

		int[] input = new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

		System.out.println("Result: " + findMaximumSumSubArray(input, 0, input.length-1));
	}


}
