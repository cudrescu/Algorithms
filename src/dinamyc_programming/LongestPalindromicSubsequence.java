package dinamyc_programming;

public class LongestPalindromicSubsequence {

	/**
	 *
	 * recursion:
	 * if(s[i] == s[j]) sol[i][j] = 2 + sol[i+1][j-1]
	 * else sol[i][j] = max(sol[i][j-1], sol[i+1][j])
	 *
	 * @param str
	 * @return
	 */
	private static int lps(String str) {

		int n = str.length();
		int[][] sol = new int[n][n];

		for(int i = 0; i < n; i++) {
			sol[i][i] = 1;
		}

		for(int j = 2; j <= n; j++) {
			for(int i = 0; i < n-j+1; i++) {
				if(str.charAt(i) == str.charAt(i+j-1)) {
					sol[i][i+j-1] = 2 + sol[i+1][i+j-2];
				} else {
					sol[i][i+j-1] = Math.max(sol[i][i+j-2], sol[i+1][i+j-1]);
				}
			}
		}

		return sol[0][n-1];
	}

	public static void main(String[] args) {
		String input = "agbdba";
		System.out.println(lps(input));
	}

}
