package bit_manipulation;

public class RotateBits {

    /**
     * rotate n by d positions to the left
     *
     * @param n
     * @param d
     * @return
     */
    private static int N_BITS = 32;

    private static int leftRotate(int n, int d) {
        return n << d | n >> (N_BITS - d);
    }

    public static void main(String[] args) {

        int n = 16;
        int d = 2;
        System.out.print("Left Rotation of " + n +
                " by " + d + " is ");
        System.out.print(leftRotate(n, d));

    }

}
