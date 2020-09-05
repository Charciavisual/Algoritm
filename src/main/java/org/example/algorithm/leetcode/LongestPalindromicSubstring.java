package org.example.algorithm.leetcode;

/**
 * Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * @author Changhee Choi
 * @since 05/09/2020
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        String ret = "";
        for (int i = 0; i < s.length(); i++) {
            String palindrome1 = makePalindrome(s, i, i);
            //팰린드롬의 길이가 짝수인 경우를 처리
            String palindrome2 = makePalindrome(s, i, i + 1);

            String palindrome = palindrome1.length() > palindrome2.length() ? palindrome1 : palindrome2;
            ret = palindrome.length() > ret.length() ? palindrome : ret;
        }
        return ret;
    }

    private String makePalindrome(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }

        // s= "racecar" 이면, length가 7이 반환되어야 하므로 L = 0, R = 6 이 되어야 한다.
        // 그런데 while 루프에서 0번째 r과 6번째 r이 매칭되고 L--, R++ 가 실행되므로 return할때의 L=-1, R=7이 된다.
        return s.substring(L + 1, R);
    }

    public void runTest(String s) {
        System.out.println(longestPalindrome(s));
    }
}
