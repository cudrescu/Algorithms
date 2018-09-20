package dinamyc_programming;

import java.util.Arrays;

public class MinJumpsToReachEnd {

	/**
	 * get nr of min jumps to get from 0 to n knowing you can jump between 1 and a[i] at each step
	 *
	 * recursion:
	 * if(j + a[j] <= i) sol[i] = sol[j] + 1;
	 *
	 * @param arr
	 * @return
	 */
	private static int getMinJumps(int[] arr) {

		int n = arr.length;
		int[] sol = new int[n];

		for(int i = 1; i < n; i++) {
			for(int j = 0; j < i; j++) {
				if(j + arr[j] >= i) {
					sol[i] = sol[i] != 0 ? Math.min(sol[i], sol[j] + 1) : sol[j] + 1;
				}
			}
		}

		System.out.println(Arrays.toString(sol));

		return sol[n-1];
	}

	public static void main(String[] args) {

		int[] input = new int[] {2, 3, 1, 1, 2, 4, 2, 0, 1, 1};

		System.out.println("Min jumps: " + getMinJumps(input));
	}
}
