package selected;

public class SparseTableMinQuery {

    private static int[][] preprocess(int[] arr) {

        int n = arr.length;
        int m = log(n, 2) + 1;

        int[][] result = new int[n][m];

        for(int i = 0; i < n; i++) {
            result[i][0] = arr[i];
        }

        for(int j = 1; (j << 1) < n; j++) {
            for(int i = 0; i < n; i++) {
                int k = i + ((j - 1) == 0 ? 1 : ((j - 1) << 1));
                if(k < n) {
                    result[i][j] = Math.min(result[i][j - 1], result[k][j - 1]);
                }
            }
        }

        return result;
    }

    private static int query(int[][] pp, int left, int right) {
        int diff = right - left + 1;
        int k = log(diff, 2);

        return Math.min(pp[left][k], pp[right - (1 << k) + 1][k]);
    }

    private static int log(int x, int base) {
        return (int) (Math.log(x) / Math.log(base));
    }

    public static void main(String[] args) {

        int[] input = new int[] {4, 6, 1, 5, 7, 3};

        int[][] pp = preprocess(input);
        for(int i = 0; i < pp.length; i++) {
            for(int j = 0; j < pp[i].length; j++) {
                System.out.print(pp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println(query(pp, 0, 3));
        System.out.println(query(pp, 0, 5));
        System.out.println(query(pp, 3, 5));
    }
}
