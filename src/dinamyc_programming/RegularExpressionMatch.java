package dinamyc_programming;

public class RegularExpressionMatch {

    private static boolean match(String s, String p) {

        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();

        int n = str.length;
        int m = pattern.length;

        boolean[][] ans = new boolean[n+1][m+1];
        for(int i = 0; i <= n; i++) {
            ans[i][0] = false;
        }
        for(int i = 0; i <= m; i++) {
            ans[0][i] = false;
        }

        ans[0][0] = true;
        for(int i = 1; i <= n ; i++) {
            for(int j = 1; j <= m; j++) {
                if(str[i-1] == pattern[j-1] || pattern[j-1] == '.') {
                    ans[i][j] = ans[i-1][j-1];
                } else {
                    if(pattern[j-1] == '*') {
                        if (ans[i][j - 2]) {
                            ans[i][j] = true;
                        } else {
                            if(str[i-1] == pattern[j-2] || pattern[j-2] == '.') {
                                ans[i][j] = ans[i-1][j];
                            }
                        }
                    } else {
                        ans[i][j] = false;
                    }
                }
            }
        }

        return ans[n][m];
    }

    public static void main(String[] args) {

        String s = "xaabyc";
        String pattern = "xa*b.c";

        System.out.println(match(s, pattern));
    }

}
