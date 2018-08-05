package order_statistics;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class RandomizedSelect {

    private static int choosePivot(int[] array, int left, int right) {
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

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static int partition(int[] array, int left, int right) {

        int p = choosePivot(array, left, right);
        int referenceValue = array[p];

        swap(array, right, p);

        int i = left - 1;
        for(int j = left; j < right; j++) {
            if(array[j] <= referenceValue) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i+1, right);

        return i+1;
    }

    private static int findKthMinimum(int[] array, int left, int right, int k) {

        if(left >= right) {
            return array[left];
        }

        int pivot = partition(array, left, right);

        if(pivot == k) {
            return array[pivot];
        }

        if(pivot > k) {
            return findKthMinimum(array, left, pivot - 1, k);
        } else {
            return findKthMinimum(array, pivot + 1, right, k - pivot);
        }

    }

    public static void main(String[] args) {

        int[] input = new int[] {12, 2, 6, 23, 2, 4, 7, 8};

        System.out.println(findKthMinimum(input, 0, input.length-1, 3));
    }



}
