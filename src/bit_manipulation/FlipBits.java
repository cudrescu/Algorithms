package bit_manipulation;

public class FlipBits {

    /**
     * count nr of bits to flip to turn a into b
     *
     * @param a
     * @param b
     * @return
     */
    private static int countBitFlip(int a, int b) {
        int diff = a ^ b;
        return countSetBits(diff);
    }

    private static int countSetBits(int n) {
        int count = 0;

        while(n > 0) {
            count += n & 1;
            n = n >> 1;
        }

        return count;
    }

    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        System.out.println(countBitFlip(a, b));
    }
}
