package hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class BSTreeSolution {

    static class TreeNode {

        int value;
        int count;
        private TreeNode parent;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value) {
            this.value = value;
            this.count = 1;
        }

        public TreeNode insertValue(int newValue) {
            if(this.value >= newValue) {
                if(this.left == null) {
                    this.count ++;
                    this.left = new TreeNode(newValue);
                    this.left.parent = this;
                    return this.left;
                } else {
                    this.count ++;
                    return this.left.insertValue(newValue);
                }
            } else {
                if(this.right == null) {
                    this.count ++;
                    this.right = new TreeNode(newValue);
                    this.right.parent = this;
                    return this.right;
                } else {
                    this.count ++;
                    return this.right.insertValue(newValue);
                }
            }
        }
    }

    private static int countShifts(TreeNode node, int referenceValue) {
        if(node == null || node.parent == null) {
            return 0;
        }

        int count = 0;
        TreeNode parent = node.parent;
        while(parent != null) {
            while(parent != null && parent.right == node) {
                node = parent;
                parent = parent.parent;
            }
            if(parent != null && node == parent.left) {
                if(parent.value != referenceValue)
                    count ++;
                if(parent.right != null) {
                    count += parent.right.count;
                }
                node = parent;
                parent = parent.parent;
            }
        }

        return count;
    }

    // Complete the insertionSort function below.
    static int insertionSort(int[] arr) {

        if(arr == null || arr.length <= 1) {
            return 0;
        }

        int totalShifts = 0;
        TreeNode root = new TreeNode(arr[0]);
        for(int i = 1; i < arr.length; i++) {
            TreeNode node = root.insertValue(arr[i]);
            totalShifts += countShifts(node, arr[i]);
        }

        return totalShifts;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int[] arr = new int[]{2, 1, 3, 1, 2};

        int result = insertionSort(arr);

        System.out.println(result);


        scanner.close();
    }
}
