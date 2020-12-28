package org.example.algorithm.leetcode.dp;

/**
 * @link https://leetcode.com/problems/palindromic-substrings/
 * @author Changhee Choi
 * @since 29/12/2020
 */
public class PalindromicSubstrings {
  public int countSubstrings(String s) {
    final char[] charArray = s.toCharArray();

    int answer = 0;
    for (int i = 0; i < charArray.length; i++) {
      answer += countPalindrome(charArray, i, i);
      answer += countPalindrome(charArray, i, i + 1);
    }
    return answer;
  }

  private int countPalindrome(char[] s, int left, int right) {
    final int len = s.length;
    int l = left, r = right;

    int count = 0;
    while (l >= 0 && r < len && s[l] == s[r]) {
      l--;
      r++;
      count++;
    }
    return count;
  }
}
