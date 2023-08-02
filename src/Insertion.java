public class Insertion {
    public static int insert(int n, int m, int i, int j) {
        if(i > j || i < 0 || j >= 32) {
            return 0;
        }
        n = clearBits(n, i, j);
        return n | (m << i);
    }
    private static int clearBits(int n, int i, int j) {
        int left = j < 31 ? (-1 << (j + 1)) : 0;
        int right = (1 << i) - 1;
        int mask = left | right;
        return n & mask;
    }
    public static void main(String args[]) {
        int result = insert(1405, 19, 2, 6);
        String resultBinaryString = Integer.toBinaryString(result);
        System.out.println(resultBinaryString);
    }
}
