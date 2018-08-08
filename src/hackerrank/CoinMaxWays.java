package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CoinMaxWays {

	// Complete the getWays function below.
	static long getWays(long n, long[] c) {

		//s[n] = s[n-1] + s[1] + s[n-2] + s[2] ... ;

		int[] sol = new int[(int) (n+1)];

		for(int i = 0; i <= n; i ++) {
			sol[i] = 0;
			for(int j = 0; j < c.length; j++) {
				if(i == c[j]) {
					sol[i] = 1;
				}
			}
		}

		for(int i = 1; i <= n; i++) {
			for(int j = 0; j < c.length; j++) {
				if(i - c[j] >= 0) {
					//if(sol[(int) (i-c[j])] != 0) {
						//if(sol[i] != 0) {
							//sol[i] = Math.max((sol[(int) (i-c[j])]) * sol[(int) c[j]], sol[i]);
							sol[i] += sol[(int) (i-c[j])] * sol[(int) c[j]];
						//} else {
						//	sol[i] = sol[(int) (i-c[j])];
						//}
					//}
				}
			}
		}


		return sol[(int) n];
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		int n = 4;

		long[] c = new long[]{1,2,3};

		long ways = getWays(n, c);

		System.out.println(ways);
	}
}
