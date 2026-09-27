class Solution {
    public String reverseParentheses(String s) {
        Stack<StringBuilder> st = new Stack<>();
        st.push(new StringBuilder());

        for (char c : s.toCharArray()) {
            if (c == '(') {
                st.push(new StringBuilder());
            } 
            else if (c == ')') {
                StringBuilder temp = st.pop().reverse();
                st.peek().append(temp);
            } 
            else {
                st.peek().append(c);
            }
        }

        return st.pop().toString();
    }
}