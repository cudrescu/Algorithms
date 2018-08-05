package data_structures;

public class BinarySearchTree {

    static class TreeNode {

        int value;
        private TreeNode parent;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }

        public void insertValue(int newValue) {
            if(this.value >= newValue) {
                if(this.left == null) {
                    this.left = new TreeNode(newValue);
                    this.left.parent = this;
                } else {
                    this.left.insertValue(newValue);
                }
            } else {
                if(this.right == null) {
                    this.right = new TreeNode(newValue);
                    this.right.parent = this;
                } else {
                    this.right.insertValue(newValue);
                }
            }
        }

        public boolean searchValue(int valueToSearch) {
            if(this.value == valueToSearch) {
                return true;
            }

            if(this.value >= valueToSearch) {
                return this.left != null && this.left.searchValue(valueToSearch);
            } else {
                return this.right != null && this.right.searchValue(valueToSearch);
            }
        }

        public int getMinimum() {
            if(this.left == null) {
                return this.value;
            }

            return this.left.getMinimum();
        }

        public int getMaximum() {
            if(this.right == null) {
                return this.value;
            }

            return this.right.getMaximum();
        }

        public int succesor(TreeNode node) {
            if(node.right != null) {
                return node.right.getMinimum();
            }

            TreeNode parent = node.parent;
            while(parent != null && parent.right == node) {
                node = parent;
                parent = parent.parent;
            }

            return parent != null ? parent.value : -1;
        }
    }

    private static TreeNode createTree(int[] input) {
        TreeNode root = new TreeNode(input[0]);

        for(int i = 1; i < input.length; i++) {
            root.insertValue(input[i]);
        }

        return root;
    }

    private static void inOrderTreeWalk(TreeNode node) {
        if(node == null) {
            return;
        }

        inOrderTreeWalk(node.left);
        System.out.println(node.value);
        inOrderTreeWalk(node.right);
    }

    public static void main(String[] args) {

        int[] input = new int[]{2, 1, 3, 1, 2};

        TreeNode root = createTree(input);

        inOrderTreeWalk(root);

        System.out.println("Has element 3: " + root.searchValue(3));
        System.out.println("Has element 4: " + root.searchValue(4));

        System.out.println("Minimum: " + root.getMinimum());
        System.out.println("Maximum: " + root.getMaximum());

        System.out.println("Succesor: " + root.succesor(root.left));
    }
}
