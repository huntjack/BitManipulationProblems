public class DrawLine {
    public static byte[] drawLine(byte[] screen,
                                  int width,
                                  int x1,
                                  int x2,
                                  int y) {
        if(screen == null || !isValid(width, x1, x2, y) || isOutOfBounds(width, x1, x2)) {
            throw new IllegalArgumentException();
        }
        int startIndex = getByteIndex(y, width, x1);
        int startBit = getBitIndex(x1);
        int endIndex = getByteIndex(y, width, x2);
        int endBit = getBitIndex(x2);
        byte startByte = screen[startIndex];
        if(startIndex == endIndex) {
            screen[startIndex] = setBitFromIndexToIndex(startByte, startBit, endBit);
        } else {
            screen[startIndex] = setBitFromIndexToEnd(startByte, startBit);
            if(endIndex > startIndex + 1) {
                int current = startIndex + 1;
                while(current < endIndex) {
                    screen[current] = -1;
                    current++;
                }
            }
            byte endByte = screen[endIndex];
            screen[endIndex] = setBitFromStartToIndex(endByte, endBit);
        }
        return screen;
    }
    private static int getByteIndex(int row, int width, int x) {
        int bytesPerRow = width / 8;
        int indexOfByteInRow = x / 8;
        return (row * bytesPerRow) + indexOfByteInRow;
    }
    private static int getBitIndex(int x) {
        return x % 8;
    }
    private static byte setBitFromIndexToEnd(byte target, int bitIndex) {
        int mask = -1 << bitIndex;
        return (byte) (target | mask);
    }
    private static byte setBitFromStartToIndex(byte target, int bitIndex) {
        int mask = (1 << (bitIndex + 1)) -1;
        return (byte) (target | mask);
    }
    private static byte setBitFromIndexToIndex(byte target, int startIndex, int endIndex) {
        int mask = -1 << startIndex;
        int clearMask = (1 << endIndex + 1) - 1;
        mask &= clearMask;
        return (byte) (target | mask);
    }
    private static boolean isOutOfBounds(int width, int x1, int x2) {
        return x2 >= width || x2 < x1;
    }
    private static boolean isValid(int width, int x1, int x2, int y) {
        return isDivisibleBy(width, 8) &&
                x1 >= 0 &&
                x2 >= 0 &&
                y >= 0;
    }
    public static void printScreen(byte[] screen, int width) {
        int bitCount = 0;
        for(int i = 0; i < screen.length; i++) {
            int current = screen[i];
            printByte(current);
            bitCount += 8;
            if(isDivisibleBy(bitCount, width)) {
                System.out.println();
                bitCount = 0;
            }
        }
    }
    private static void printByte(int current) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 8; i++) {
            stringBuilder.append((current & 1) + "  ");
            current >>>= 1;
        }
        System.out.print(stringBuilder);
    }
    private static boolean isDivisibleBy(int dividend, int divisor) {
        return dividend % divisor == 0;
    }
    public static void main(String[] args) {
        int size = 72;
        int width = 24;
        byte[] screen = new byte[size];
        for (int i = 0; i < size; i++) {
            screen[i] = (byte) 0;
        }
        screen = drawLine(screen, width, 2, 21, 6);
        printScreen(screen, width);
    }
}
