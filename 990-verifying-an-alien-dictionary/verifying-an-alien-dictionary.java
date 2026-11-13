class Solution {
    public boolean isAlienSorted(String[] words, String order) {

        int[] rank = new int[26];

        // Store the position of every character in alien alphabet
        for (int i = 0; i < 26; i++) {
            rank[order.charAt(i) - 'a'] = i;
        }

        // Compare adjacent words
        for (int i = 0; i < words.length - 1; i++) {

            String w1 = words[i];
            String w2 = words[i + 1];

            int len = Math.min(w1.length(), w2.length());
            boolean foundDifference = false;

            for (int j = 0; j < len; j++) {

                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                if (c1 != c2) {

                    // w1 should come before w2
                    if (rank[c1 - 'a'] > rank[c2 - 'a']) {
                        return false;
                    }

                    foundDifference = true;
                    break;
                }
            }

            // Same prefix, but longer word comes first -> invalid
            if (!foundDifference && w1.length() > w2.length()) {
                return false;
            }
        }

        return true;
    }
}