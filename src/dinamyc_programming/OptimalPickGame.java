package dinamyc_programming;

public class OptimalPickGame {

	private static int maximizeProfit(int[] arr) {

		/**
		 * maximize profit picking the first or last element from array at each step
		 *
		 * sol[i][j][0] = max(sol[i+1][j][1] + arr[i], sol[i][j-1][1] + arr[j])
		 * sol[i][j][1] = sol[i+1][j][0] || sol[i][j-1][0] depending on first sol[i][j][0]
		 *
		 */

		int n = arr.length;
		int[][][] sol = new int[n+1][n+1][2];

		for(int i = 0; i < n; i++) {
			sol[i+1][i+1][0] = arr[i];
			sol[i+1][i+1][1] = 0;
		}

		for(int i = 2; i <= n; i++) {
			for(int j = 1; j <= n-i + 1; j++) {
				int ln = j;
				int col = j+i-1;
				if(arr[ln-1] + sol[ln+1][col][1] >= arr[col - 1] + sol[ln][col-1][1]) {
					sol[ln][col][0] = arr[ln-1] + sol[ln+1][col][1];
					sol[ln][col][1] = sol[ln+1][col][0];
				} else {
					sol[ln][col][0] = arr[col-1] + sol[ln][col-1][1];
					sol[ln][col][1] = sol[ln][col-1][0];
				}
			}
		}

		for(int i = 0; i <= n; i++) {
			for(int j = 0; j <= n; j++) {
				System.out.print("(" + sol[i][j][0] + ", " + sol[i][j][1] + ") ");
			}
			System.out.println();
		}

		return sol[1][n][0];
	}

	public static void main(String[] args) {

		int[] input = new int[]{3, 9, 1, 2};

		System.out.println(maximizeProfit(input));
	}

}
