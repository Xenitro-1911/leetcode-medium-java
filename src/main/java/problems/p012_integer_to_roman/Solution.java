package problems.p012_integer_to_roman;

public class Solution {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder s=new StringBuilder();
        int i=0;
        while(num!=0){
            int x=values[i];
            if(num>=x){
                s.append(symbols[i]);
                num = num - x;
            }
            else if(num<x){
                i++;
            }
        }
        return s.toString();
    }
}