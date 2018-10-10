package dinamyc_programming;

public class PalindromePartitioning {

	/**
	 * get min nr of cuts so that all substrings are palindromes
	 *
	 * recursion:
	 * if(palindrom(i,j)) {
	 *     sol[i][j] = 0;
	 * } else {
	 *     find k such that sol[i][j] = min(sol[i][k] + sol[k+1][j] + 1
	 * }
	 *
	 * @param str
	 * @return
	 */
	private static int getMinPartition(String str) {
		int n = str.length();

		int[][] sol = new int[n][n];
		for(int i = 0; i < n; i++) {
			sol[i][i] = 0;
		}

		for(int j = 2; j <= n; j++) {
			for(int i = 0; i < n-j+1; i++) {
				if(palindrome(str, i, i+j-1)) {
					sol[i][i+j-1] = 0;
				} else {
					int k = i;
					while(k < i+j-1) {
						if(sol[i][i+j-1] != 0) {
							sol[i][i + j - 1] = Math.min(sol[i][i + j - 1], sol[i][k] + sol[k + 1][i + j - 1] + 1);
						} else {
							sol[i][i + j - 1] = sol[i][k] + sol[k + 1][i + j - 1] + 1;
						}
						k++;
					}
				}
			}
		}

		return sol[0][n-1];
	}

	private static boolean palindrome(String str, int i, int j) {
		while(i < j) {
			if(str.charAt(i) != str.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}

		return true;
	}

	public static void main(String[] args) {
		String input = "abcbm";

		System.out.println(getMinPartition(input));
	}

}
