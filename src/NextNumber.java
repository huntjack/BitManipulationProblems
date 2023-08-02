public class NextNumber {
    public static int findNext(int baseNumber) {
        if(baseNumber < 1) {
            throw new IllegalArgumentException();
        }
        int baseNumberOfOnes = calculateNumberOfOnes(baseNumber);
        int candidateNumber = baseNumber;
        int candidateNumberOfOnes;
        do {
            candidateNumberOfOnes = calculateNumberOfOnes(++candidateNumber);
        } while (candidateNumberOfOnes != baseNumberOfOnes);
        return candidateNumber;
    }
    private static int calculateNumberOfOnes(int number) {
        int count = 0;
        for(int i = 0; i < 32; i++) {
            boolean digit = getBit(number, i);
            if(digit) {
                count++;
            }
        }
        return count;
    }
    private static boolean getBit(int number, int i) {
        return (number & (1 << i)) != 0;
    }
    public static int findPrevious(int baseNumber) {
        if(baseNumber < 2) {
            throw new IllegalArgumentException();
        }
        int baseNumberOfOnes = calculateNumberOfOnes(baseNumber);
        int candidateNumber = baseNumber;
        int candidateNumberOfOnes;
        do {
            candidateNumberOfOnes = calculateNumberOfOnes(--candidateNumber);
        } while (candidateNumberOfOnes != baseNumberOfOnes);
        return candidateNumber;
    }
    public static void main(String args[]) {
        int number = 23;
        int next = findNext(number);
        int previous = findPrevious(number);
        StringBuilder stringBuilder = new StringBuilder("Base: ");
        stringBuilder.append(number);
        stringBuilder.append("  Next: ");
        stringBuilder.append(next);
        stringBuilder.append("  Previous: ");
        stringBuilder.append(previous);
        System.out.println(stringBuilder);
    }
}
