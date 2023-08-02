public class FlipBitToWin {
    public static int getLongestSequencesOfOnes(int number) {
        if(~number == 0) {
            return Integer.BYTES * 8;
        }
        int sum = 0;
        int distanceSinceLastZero = 0;
        boolean hasVisitedZero = false;
        int longestString = 1;
        for(int i = 0 ; i < 32; i++) {
            boolean current = getBit(number, i);
            if(current) {
                sum++;
                distanceSinceLastZero++;
            } else if(!current && !hasVisitedZero) {
                hasVisitedZero = true;
                sum++;
                distanceSinceLastZero = 0;
            } else {
                sum = distanceSinceLastZero + 1;
                distanceSinceLastZero = 0;
            }
            longestString = updateLongestString(sum, longestString);
        }
        return longestString;
    }
    private static boolean getBit(int number, int i) {
        return (number & (1 << i)) != 0;
    }
    private static int updateLongestString(int sum, int longestString) {
        if(sum > longestString) {
            longestString = sum;
        }
        return longestString;
    }
    public static void main(String args[]) {
        int input = 1775;
        System.out.println(Integer.toBinaryString(input));
        int result = getLongestSequencesOfOnes(input);
        System.out.println(result);
    }
}
