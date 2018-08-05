package sorting;

public class RadixSort {

    private static int getDigit(int value, int digitIndex) {
        return (int) ((value % Math.pow(10, digitIndex + 1))/Math.pow(10, digitIndex));
    }

    private static int[] sortOnDigit(int[] array, int digitIndex) {

        int[] c = new int[10];
        for(int i = 0; i < array.length; i++) {
            int digit = getDigit(array[i], digitIndex);
            c[digit] ++;
        }

        for(int i = 1; i < 10; i++) {
            c[i] += c[i-1];
        }

        int[] result = new int[array.length];
        for(int i = array.length-1; i >= 0; i--) {
            int digit = getDigit(array[i], digitIndex);
            result[c[digit] - 1] = array[i];
            c[digit] --;
        }

        return result;
    }

    private static int[] sort(int[] array, int digitsNo) {

        //we assume all numbers have exactly the same number of digits
        for(int i = 0; i < digitsNo; i++) {
            array = sortOnDigit(array, i);
        }

        return array;
    }

    public static void main(String[] args) {

        int[] input = new int[]{173, 456, 232, 334, 234, 446};

        input = sort(input, 3);
        for (Integer element : input) {
            System.out.print(element + " ");
        }
    }
}
