package dinamyc_programming;

import java.util.HashSet;
import java.util.Set;

public class WordBreak {

	private static boolean isCompoundWord(String str, Set<String> dictionary) {

		/**
		 *
		 * determine if a word is made of dictionary words. Ex: Iamace
		 *
		 *	recurence: if s[i...j] in dict => sol[i][j] = true;
		 *	else if exists k such that s[i][k] && s[k+1][j] => s[i][j] = true;
		 * 	else s[i][j] = false;
		 */

		char[] arr = str.toCharArray();
		int n = arr.length;

		boolean[][] sol = new boolean[n+1][n+1];

		for(int i = 0; i < n; i++) {
			if(dictionary.contains(str.substring(i,i))) {
				sol[i+1][i+1] = true;
			}
		}

		for(int i = 2; i <= n; i++) {
			for(int j = 1; j <= n-i+1; j++) {
				sol[j][j+i-1] = false;
				if(dictionary.contains(str.substring(j-1, j+i-2))) {
					sol[j][j+i-1] = true;
				} else {
					for(int k = j; k < j+i-1; k++) {
						if(sol[j][k] && sol[k+1][j+i-1]) {
							sol[j][j+i-1] = true;
							break;
						}
					}
				}
			}
		}

		for(int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				System.out.print((sol[i][j] ? 1 : 0) + " ");
			}
			System.out.println();
		}

		return sol[1][n];
	}

	public static void main(String[] args) {

		Set<String> dictionary = new HashSet<>();
		dictionary.add("I");
		dictionary.add("a");
		dictionary.add("am");
		dictionary.add("ace");
		dictionary.add("mace");

		String str = "Iamace";

		System.out.println(isCompoundWord(str, dictionary));
	}

}
