package dinamyc_programming;

public class MatrixChainOrder {

	/**
	 * each matrix has dimension p[i] * p[i-1]
	 *
	 * recursion: m[i][j] = m[i, k] + m[k+1, j] + p[i-1] * p[k] * p[j]
	 * so the best cost to compute the product of matrixes A i...j is the best cost to compute A i .. K + best cost of A k+1...j + the cost of mutiplying the 2 submatrixes
	 *
	 * @param p
	 * @param m
	 * @param s
	 */
	private static void matrixChainOrder(int[] p, int[][] m, int[][] s) {

		int n = p.length - 1;
		for(int i = 0; i < n; i++) {
			m[i][i] = 0;
		}
		for(int l = 2; l < n ; l++) {
			for(int i = 1; i < n - l + 1; i++) {
				int j = i + l -1;
				m[i][j] = Integer.MAX_VALUE;

				for(int k = i; k < j - 1; k++) {
					int q = m[i][k] + m[k+1][j] + p[i-1] * p[k] * p[j];
					if(q < m[i][j]) {
						m[i][j] = q;
						s[i][j] = k;
					}
				}
			}
		}
	}

	private static void printSolution(int[][] s, int i, int j) {
		if(i == j) {
			System.out.print("A[" + i + "] ");
		} else {
			System.out.print("(");
			printSolution(s, i, s[i][j]);
			printSolution(s, s[i][j] + 1, j);
			System.out.print(")");
		}
	}

	public static void main(String[] args) {

		/*
		A1: 4,5
		A2: 5,3
		A3: 3,7
		A4: 7,5
		 */
		int[] p = new int[]{4,5,3,7,5};
		int[][] m = new int[p.length-1][p.length-1];
		int[][] s = new int[p.length][p.length];

		matrixChainOrder(p, m, s);

		printSolution(s, 1, 3);
	}

}
