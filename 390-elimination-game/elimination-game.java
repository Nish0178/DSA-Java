class Solution {
    public int lastRemaining(int n) {
        int head = 1;
        int step = 1;
        int remaining = n;
        boolean left = true;

        while (remaining > 1) {

            // Head changes when eliminating from left
            // OR when eliminating from right with odd count
            if (left || remaining % 2 == 1) {
                head += step;
            }

            remaining /= 2;
            step *= 2;

            left = !left;
        }

        return head;
    }
}