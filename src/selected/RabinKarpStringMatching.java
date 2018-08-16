package selected;

public class RabinKarpStringMatching {

	private static void rabinKarp(String s1, String s2) {

		char[] s1Chars = s1.toCharArray();
		char[] s2Chars = s2.toCharArray();

		int n = s1Chars.length;
		int m = s2Chars.length;

		if(n < m) {
			System.out.println("No match");
		}

		int d = 256;
		int q = 13;
		int h = 1;

		for(int i = 0; i < m-1; i++) {
			h = (h * d) % q;
		}

		int patternHash = 0;
		int windowHash = 0;

		for(int i = 0; i < m; i++) {
			patternHash = (d * patternHash + s2Chars[i]) % q;
			windowHash = (d * windowHash + s1Chars[i]) % q;
		}

		int i = 0;
		while(i < n-m) {
			if(windowHash == patternHash) {
				boolean match = true;
				for(int j = 0; j < m; j++) {
					if(s2Chars[j] != s1Chars[i+j]) {
						match = false;
						break;
					}
				}
				if(match) {
					System.out.println("Found match at index:" + i);
				}
			}

			windowHash = (d * (windowHash - s1Chars[i] * h) + s1Chars[i+m]) % q;
			i++;
			if (windowHash < 0) {
				windowHash = (windowHash + q);
			}
		}

	}

	public static void main(String[] args) {

		String txt = "GEEKS FOR GEEKS";
		String pat = "GEEK";

		rabinKarp(txt, pat);
	}

}
