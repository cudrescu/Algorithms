package dinamyc_programming;

import java.util.Arrays;

public class TextJustification {

	private static void justify(String[] words, int rowSize) {

		/**
		 * arrange words such that number of spaces (n) on each row is minimum (computed by sum(n^2))
		 * build cost matrix C[][]
		 *
		 * recursion: min(n[j] + C[i][j-1] where j = i+1...len
		 */

		int n = words.length;
		int[][] cost = new int[n+1][n+1];

		for(int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				cost[i][j] = -1;
			}
		}

		for(int i = 1; i <= n; i++) {
			for(int j = i; j <= n; j++) {
				if(cost[i][j-1] == -1) {
					cost[i][j] = rowSize - words[j-1].length();
					cost[i][j] *= cost[i][j];
					continue;
				}
				if(cost[i][j-1] == Integer.MAX_VALUE || words[j-1].length() > (int)Math.sqrt((double)cost[i][j-1]) - 1)  {
					cost[i][j] = Integer.MAX_VALUE;
				} else {
					cost[i][j] = (int)Math.sqrt((double)cost[i][j-1]) - words[j-1].length() - 1;
					cost[i][j] *= cost[i][j];
				}
			}
		}

		for(int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				System.out.print((cost[i][j] == Integer.MAX_VALUE ? "INF" : cost[i][j]) + " ");
			}
			System.out.println();
		}

		int minCost[] = new int[n+1];
		int result[] = new int[n+1];
		for(int i = words.length; i >= 1 ; i--){
			minCost[i] = cost[i][n];
			result[i] = n;
			for(int j=n; j > i; j--){
				if(cost[i][j-1] == Integer.MAX_VALUE){
					continue;
				}
				if(minCost[i] > minCost[j] + cost[i][j-1]){
					minCost[i] = minCost[j] + cost[i][j-1];
					result[i] = j-1;
				}
			}
		}

		System.out.println("Result:");
		System.out.println(Arrays.toString(result));

	}

	public static void main(String[] args) {

		String[] input = new String[]{"Tushar", "Roy", "likes", "to", "code"};

		justify(input, 10);

	}
}
