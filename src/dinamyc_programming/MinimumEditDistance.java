package dinamyc_programming;

public class MinimumEditDistance {


    private static int minDist(String s1, String s2) {

        /**
         * Convert a string s1 to string s2 using any 3 operations: add, delete, replace
         * determine minimum number of operations
         *
         * recursion: sol[i][j] = min(sol[i-1][j-1], sol[i-1][j], sol[i][j-1]) + 1, if s1[i] != s2[j];
         *            sol[i][j] = sol[i-1][j-1] otherwise
         */

        int n = s1.length();
        int m = s2.length();

        int[][] sol = new int[m+1][n+1];

        for(int i = 0; i <= n; i ++) {
            sol[0][i] = i;
        }

        for(int i = 0; i <= m; i ++) {
            sol[i][0] = i;
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(s1.charAt(j-1) == s2.charAt(i-1)) {
                    sol[i][j] = sol[i-1][j-1];
                } else {
                    sol[i][j] = Math.min(sol[i - 1][j - 1], Math.min(sol[i - 1][j], sol[i][j - 1])) + 1;
                }
            }
        }

        for(int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }

        return sol[m][n];
    }


    public static void main(String[] args) {

        String s1 = "abcdef";
        String s2 = "azced";

        System.out.println(minDist(s1, s2));
    }

}
