package dinamyc_programming;

public class LongestCommonSubsequence {

	private static String getCommonSubstring(int[][] sol, char[] s1, int i, int j) {

		if(i == 0 || j == 0) {
			return "";
		}

		if(sol[i][j] == sol[i-1][j-1] + 1) {
			return getCommonSubstring(sol, s1, i-1, j-1) + s1[i - 1];
		} else {
			if(sol[i][j] == sol[i-1][j]) {
				return getCommonSubstring(sol, s1, i-1, j);
			} else {
				return getCommonSubstring(sol, s1, i, j-1);
			}
		}
	}

	private static String lcs(char[] s1, char[] s2) {

		int[][] sol = new int[s1.length + 1][s2.length + 1];

		for(int i = 1; i <= s1.length; i++) {
			for(int j = 1; j <= s2.length; j++) {
				if(s1[i-1] == s2[j-1]) {
					sol[i][j] = sol[i-1][j-1] + 1;
				} else {
					sol[i][j] = Math.max(sol[i-1][j], sol[i][j-1]);
				}
			}
		}

		return getCommonSubstring(sol, s1, s1.length, s2.length);
	}

	public static void main(String[] args) {

		String s1 = "ACCGGTCGAGTGCGCGGAAGCCGGCCGAA";
		String s2 = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";

		String lcs = lcs(s1.toCharArray(), s2.toCharArray());

		System.out.println(lcs);

	}

}
