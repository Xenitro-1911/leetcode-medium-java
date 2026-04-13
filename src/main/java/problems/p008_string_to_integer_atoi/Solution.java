package problems.p008_string_to_integer_atoi;

class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        if (s.isEmpty()) return 0;

        int i = 0;
        int sign = 1;
        long res = 0; // Use long to detect overflow easily

        // 1. Handle Sign
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 2. Convert Digits
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            res = res * 10 + (s.charAt(i) - '0');

            // 3. Handle Overflow immediately
            if (sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && res * -1 < Integer.MIN_VALUE) return Integer.MIN_VALUE;

            i++;
        }

        return (int) res * sign;
    }
}
