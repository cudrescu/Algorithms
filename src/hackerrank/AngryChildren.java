package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class AngryChildren {

    static long diff(int[] arr, int i, int j) {
        long sum = 0;
        for(int k = i; k < j; k++) {
            sum += Math.abs(arr[j] - arr[k]);
        }

        return sum;
    }

    // Complete the angryChildren function below.
    static long angryChildren(int k, int[] packets) {

        Arrays.sort(packets);

        int n = packets.length;
        long[][] ans = new long[n+1][k+1];

        long result = Long.MAX_VALUE;
        for(int l = 2; l <= k ; l++) {
            for(int i = 1; i <= n-l+1; i++) {
                int j = i+l-1;
                ans[i][l] = ans[i][l-1] + diff(packets, i-1, j-1);
                if(l == k) {
                    if(ans[i][l] < result) {
                        result = ans[i][l];
                    }
                }
            }
        }

        return result;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int k = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] packets = new int[n];

        for (int i = 0; i < n; i++) {
            int packetsItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            packets[i] = packetsItem;
        }

        long result = angryChildren(k, packets);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
