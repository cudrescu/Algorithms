package dinamyc_programming;

public class CutRod {

	private static int topDownSolution(int[] prices, int rodSize, int[] partialResults) {

		if(partialResults[rodSize] >= 0) {
			return partialResults[rodSize];
		}

		int maxPrice;
		if(rodSize == 0) {
			maxPrice = 0;
		} else {
			maxPrice = Integer.MIN_VALUE;
			for(int i = 1; i <= rodSize; i++) {
				maxPrice = Math.max(maxPrice, prices[i-1] + topDownSolution(prices, rodSize - i, partialResults));
			}
		}
		partialResults[rodSize] = maxPrice;

		return maxPrice;
	}

	private static int bottomUpSolution(int[] prices, int rodSize) {

		int[] solution = new int[rodSize+1];

		for(int i = 1; i <= rodSize; i++) {
			solution[i] = prices[i-1];
		}

		for(int i = 1; i <= rodSize; i++) {
			for(int j = 0; j < i; j++) {
				solution[i] = Math.max(solution[i], solution[j] + solution[i-j]);
			}
		}

		return solution[rodSize];
	}

	public static void main(String[] args) {

		int[] prices = new int[]{1, 8, 4, 5, 7, 3, 2};
		int rodSize = 6;

		int[] partialResults = new int[rodSize + 1];
		partialResults[1] = prices[0];
		for(int i = 2; i < partialResults.length; i++) {
			partialResults[i] = Integer.MIN_VALUE;
		}
		System.out.println("Top-down approach: ");
		System.out.println(topDownSolution(prices, rodSize, partialResults));

		System.out.println("Bottom-up approach: ");
		System.out.println(bottomUpSolution(prices, rodSize));
	}

}
