package sorting;

import java.util.Arrays;

public class CountingSort {

    private static int[] sort(int[] array) {

        int max = Arrays.stream(array).max().orElse(0);
        int[] c = new int[max+1];
        int[] b = new int[array.length];

        for(int i = 0; i <= max; i++) {
            c[i] = 0;
        }

        for(int a : array) {
            c[a] ++;
        }


        for(int i = 1; i <= max; i++) {
            c[i] += c[i-1];
        }

        for(int i = array.length-1; i >= 0; i--) {
            b[c[array[i]] - 1] = array[i];
            c[array[i]] = c[array[i]] - 1;
        }

        return b;
    }

    public static void main(String[] args) {

        int[] input = new int[] { 2, 4, 6, 3, 8, 6, 1, 2, 1 };

        input = sort(input);
        for (Integer element : input) {
            System.out.print(element + " ");
        }

    }

}
