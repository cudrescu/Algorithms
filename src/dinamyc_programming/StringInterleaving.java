package dinamyc_programming;

public class StringInterleaving {

	private static boolean interleave(String str, String s1, String s2) {

		/**
		 * recurence:
		 * str[i+j] == s1[i] => sol[i][j] = sol[i-1][j];
		 * str[i+j] == s2[j] => sol[i][j] = sol[i][j-1];
		 * else sol[i][j] = false
		 */

		int n = str.length();

		int m = s1.length();
		int k = s2.length();

		if(n != m+k) {
			return false;
		}


		boolean[][] solution = new boolean[k+1][m+1];
		solution[0][0] = true;

		for(int i = 1; i <= k; i ++) {
			if(str.charAt(i-1) == s2.charAt(i-1)) {
				solution[i][0] = solution[i-1][0];
			}
		}

		for(int i = 1; i <= m; i ++) {
			if(str.charAt(i-1) == s1.charAt(i-1)) {
				solution[0][i] = solution[0][i-1];
			}
		}

		for(int i = 1; i <= k; i ++) {
			for(int j = 1; j <= m; j++) {
				if(str.charAt(i+j - 1) == s1.charAt(j-1)) {
					solution[i][j] = solution[i][j-1];
				} else {
					if(str.charAt(i+j - 1) == s2.charAt(i-1)) {
						solution[i][j] = solution[i-1][j];
					} else {
						solution[i][j] = false;
					}
				}
			}
		}

		return solution[k][m];
	}



	public static void main(String[] args) {

		String strToCheck = "axaaby";

		String s1 = "aab";
		String s2 = "axy";

		System.out.println(interleave(strToCheck, s1, s2));
	}

}
