package problems.p006_zigzag_conversion;

public class Solution {
    public String convert(String s, int numRows) {
        if(numRows==1||numRows>s.length()){
            return s;
        }
        int j=0;
        StringBuilder x[] = new StringBuilder[numRows];
        for(int i = 0; i < numRows; i++) {
            x[i] = new StringBuilder();
        }
        while(j<s.length()){
            for(int i=0;i<numRows;i++){
                if(j<s.length()){
                    x[i].append(s.charAt(j));
                    j++;
                }
            }
            for(int i=numRows-2;i>=1;i--){
                if(j<s.length()){
                    x[i].append(s.charAt(j));
                    j++;
                }
            }
        }
        StringBuilder c = new StringBuilder();
        for(int i=0;i<numRows;i++){
            c.append(x[i]);
        }
        return c.toString();
    }
}