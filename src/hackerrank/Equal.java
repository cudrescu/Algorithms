package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Equal {

	static void updateArray(int[] arr, int[] refValues, int ref, int next) {
		int diff = next - ref;
		boolean skipped = false;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == next && !skipped) {
				skipped = true;
			} else {
				arr[i] += diff;
			}
			if(arr[i] < refValues[0]) {
				refValues[1] = refValues[0];
				refValues[0] = arr[i];
			} else {
				if(arr[i] < refValues[1] && arr[i] > refValues[0]) {
					refValues[1] = arr[i];
				}
			}
		}
	}

	static int calculateSteps(int ref, int next) {
		int sum = next - ref;
		int steps = 0;
		while(sum != 0) {
			if(sum - 5 >= 0) {
				sum -= 5;
			} else {
				if(sum - 2 >= 0) {
					sum -= 2;
				} else {
					sum --;
				}
			}
			steps ++;
		}

		return steps;
	}

	static int getNrOfSteps(int[] arr, int[] refValues) {

		if(refValues[1] == Integer.MAX_VALUE) {
			return 0;
		}

		int ref = refValues[0];
		int next = refValues[1];

		refValues = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
		updateArray(arr, refValues, ref, next);

		return calculateSteps(ref, next) + getNrOfSteps(arr, refValues);
	}

	// Complete the equal function below.
	static int equal(int[] arr) {

		int[] refValues = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};

		for(Integer i : arr) {
			if(i < refValues[0]) {
				refValues[1] = refValues[0];
				refValues[0] = i;
			} else {
				if(i < refValues[1] && i > refValues[0]) {
					refValues[1] = i;
				}
			}
		}

		return getNrOfSteps(arr, refValues);
	}

	public static void main(String[] args) throws IOException {

		int[] input = new int[] {2, 2, 3, 7};

		System.out.println(equal(input));
	}
}
