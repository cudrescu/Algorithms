package hackerrank;

public class PerfectSquareSum {

	private static int getMinNrOfPerfectSquares(int sum) {

		int max = (int)Math.sqrt((double) sum);

		int[] squares = new int[max+1];

		for(int i = 0; i <= max; i++) {
			squares[i] = i*i;
		}

		int sol[] = new int[sum + 1];
		for(int i = 0; i <= max; i++) {
			sol[squares[i]] = 1;
		}

		for(int i = 1; i <= sum; i++) {
			for(int j = 1; j <= max; j++) {
				if(i - squares[j] > 0 && sol[i - squares[j]] != 0) {
					if(sol[i] != 0) {
						sol[i] = Math.min(sol[i], sol[i-squares[j]] + 1);
					} else {
						sol[i] = sol[i-squares[j]] + 1;
					}
				}
			}
		}

		return sol[sum];
	}

	public static void main(String[] args) {
		System.out.println(getMinNrOfPerfectSquares(7));
	}

}
