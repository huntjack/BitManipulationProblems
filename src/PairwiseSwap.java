public class PairwiseSwap {
    private static final int CLEAR_EVEN_MASK = -1431655766;
    public static int doPairwiseSwap(int number) {
        int updatedOddBits = number << 1;
        int updatedEvenBits = number >>> 1;
        updatedOddBits &= CLEAR_EVEN_MASK;
        updatedEvenBits &= ~CLEAR_EVEN_MASK;
        return updatedEvenBits | updatedOddBits;
    }
    public static void main(String[] args) {
        int result = doPairwiseSwap(182);
        System.out.println("This should be 121: " + result);
    }
}
