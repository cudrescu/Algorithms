package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SamSubstrings {

    private static final long modulo = 1000000007;

    // Complete the substrings function below.
    static int substrings(String n) {

        char[] arr = n.toCharArray();
        int m = arr.length;

        long[][] numbers = new long[m+1][m+1];
        long[][] sol = new long[m+1][m+1];

        numbers[1][1] = Long.valueOf("" + arr[0]);
        sol[1][1] = Long.valueOf("" + arr[0]);
        for(int i = 2; i <= m; i++) {
            for(int j = 1; j <= i; j++) {
                numbers[i][j] = Long.valueOf(String.valueOf(numbers[i-1][j]) + arr[i-1]);
                if(sol[i-1][j] != 0) {
                    sol[i][j] = (numbers[i][j] % modulo +
                            sol[i-1][j] % modulo +
                            sol[i][j-1] % modulo -
                            sol[i-1][j-1] % modulo) % modulo ;
                } else {
                    sol[i][j] = (numbers[i][j] % modulo + sol[i][j-1] % modulo) % modulo;
                }
            }
        }

        return (int)sol[m][m];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String n = "123";

        int result = substrings(n);

        System.out.println(result);
    }
}
