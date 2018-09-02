package data_structures;

public class FenwickTree {
    
    private static void updateTree(int[] binaryIndexedTree, int index, int value) {
        while(index < binaryIndexedTree.length) {
            binaryIndexedTree[index] += value;
            index = getNext(index);
        }
    }

    private static int getNext(int index) {
        return index + (index & -index);
    }

    private static int getParent(int index) {
        return index - (index & -index);
    }

    private static int getSum(int[] binaryIndexedTree, int index) {
        int sum = 0;
        while(index > 0) {
            sum += binaryIndexedTree[index];
            index = getParent(index);
        }

        return sum;
    }

    private static int[] createTree(int[] arr) {
        int[] binaryIndexedTree = new int[arr.length + 1];
        for(int i = 1; i <= arr.length; i++) {
            updateTree(binaryIndexedTree, i, arr[i-1]);
        }

        return binaryIndexedTree;
    }

    public static void main(String[] args) {

        int input[] = {1,2,3,4,5,6,7};
        int binaryIndexedTree[] = createTree(input);

        assert 1 == getSum(binaryIndexedTree, 0);
        assert 3 == getSum(binaryIndexedTree, 1);
        assert 6 == getSum(binaryIndexedTree, 2);
        assert 10 == getSum(binaryIndexedTree, 3);
        assert 15 == getSum(binaryIndexedTree, 4);
        assert 21 == getSum(binaryIndexedTree, 5);
        assert 28 == getSum(binaryIndexedTree, 6);

        System.out.println("Done !");
    }

}
