class Solution {
    public int minPartitions(String n) {
        int maxDigit = 0;
        
        char[] digits = n.toCharArray();
        
        for(int i=0; i<n.length(); i++) {
            maxDigit = Math.max(maxDigit, digits[i] - '0');
        }
        
        return maxDigit;
    }
}
