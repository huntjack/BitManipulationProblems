public class Conversion {
    public static int getNumberOfBitsToFlip(int numberOne, int numberTwo) {
        int resultBitVector = numberOne ^ numberTwo;
        int count = 0;
        while(resultBitVector != 0) {
            count += (resultBitVector & 1);
            resultBitVector >>>= 1;
        }
        return count;
    }
    public static void main(String[] args) {
        int result = getNumberOfBitsToFlip(39, 32);
        System.out.println("Result: " + result);
    }
}
