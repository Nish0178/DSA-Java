class Solution {
    public String evaluate(String s, List<List<String>> k) {
        Map<String,String> m = new HashMap<>();
        for (List<String> x : k) m.put(x.get(0), x.get(1));

        StringBuilder r = new StringBuilder();
        for (int i = 0; i < s.length();) {
            if (s.charAt(i) == '(') {
                int j = s.indexOf(')', i);
                r.append(m.getOrDefault(s.substring(i + 1, j), "?"));
                i = j + 1;
            } else r.append(s.charAt(i++));
        }
        return r.toString();
    }
}

