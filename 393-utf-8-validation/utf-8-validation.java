class Solution {

    public boolean validUtf8(int[] data) {

        // Number of continuation bytes expected
        int remaining = 0;

        for (int num : data) {

            // Only the least significant 8 bits are used
            int byteValue = num & 0xFF;

            // ------------------------------------------------
            // We are inside a multi-byte character
            // ------------------------------------------------
            if (remaining > 0) {

                // Continuation byte must start with 10
                if ((byteValue & 0b11000000) != 0b10000000) {
                    return false;
                }

                remaining--;
            }

            // ------------------------------------------------
            // Start of a new character
            // ------------------------------------------------
            else {

                // 1-byte character: 0xxxxxxx
                if ((byteValue & 0b10000000) == 0) {
                    remaining = 0;
                }

                // 2-byte character: 110xxxxx
                else if ((byteValue & 0b11100000) == 0b11000000) {
                    remaining = 1;
                }

                // 3-byte character: 1110xxxx
                else if ((byteValue & 0b11110000) == 0b11100000) {
                    remaining = 2;
                }

                // 4-byte character: 11110xxx
                else if ((byteValue & 0b11111000) == 0b11110000) {
                    remaining = 3;
                }

                // Invalid starting byte
                else {
                    return false;
                }
            }
        }

        // If continuation bytes are still required,
        // the encoding is incomplete.
        return remaining == 0;
    }
}