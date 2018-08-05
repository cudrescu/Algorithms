package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BankMedianSorting {

    static int choosePivot(int[] array, int left, int right) {
        int mid = left + (right - left)/2;
        if(array[left] > array[right]) {
            if(array[left] < array[mid]) {
                return left;
            } else {
                return mid;
            }
        } else {
            if(array[right] < array[mid]) {
                return right;
            } else {
                return mid;
            }
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int partition(int[] arr, int left, int right) {

        int pivot = choosePivot(arr, left, right);
        int referenceValue = arr[pivot];

        swap(arr, pivot, right);
        int i = left - 1;
        for(int j = left; j < right; j++) {
            if(arr[j] <= referenceValue) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i+1, right);

        return i+1;

    }

    static int findKthElement(int[] arr, int left, int right, int k) {

        if(left >= right) {
            return arr[left];
        }

        int pivot = partition(arr, left, right);
        if(pivot == k) {
            return arr[pivot];
        }
        if(pivot > k) {
            return findKthElement(arr, left, pivot - 1, k);
        } else {
            return findKthElement(arr, pivot + 1, right, pivot - k);
        }
    }

    static int getMedian(int[] arr, int d) {
        int median = 0;
        if(d%2 == 0) {
            median = (findKthElement(arr, 0, d-1, d/2) + findKthElement(arr, 0, d-1, d/2-1))/2 ;
        } else {
            median = findKthElement(arr, 0, d-1, d/2);
        }

        return median;
    }

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {

        int[] temp = new int[d];
        for(int i = 0; i < d; i++) {
            temp[i] = expenditure[i];
        }
        int median = getMedian(temp, d);

        int notifications = 0;
        for(int i = d; i < expenditure.length; i++) {
            if(expenditure[i] >= median) {
                notifications ++;
            }
            temp[i-(i/d * d)] = expenditure[i];
            median = getMedian(temp, d);
        }

        return notifications;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int[] input = new int[] {1, 2, 3, 4, 4};

        System.out.println(activityNotifications(input, 4));
    }
}
