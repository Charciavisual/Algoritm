package org.example.algorithm.leetcode;

import java.util.Arrays;

/**
 * Longest Substring Without Repeating Characters
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * @author Changhee Choi
 * @since 28/08/2020
 */
public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int answer = 0, start = 0;
        int[] index = new int[128]; //ascii

        Arrays.fill(index, -1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //if c is duplicated, move start to max(previous c index + 1, current start)
            //if string is "aabaab" and c is 2nd b, previous b index + 1 = 3, current start = 4
            start = Math.max(index[c] + 1, start);
            answer = Math.max(answer, i - start + 1);
            index[c] = i;
        }

        return answer;
    }

    public void runTest(String s) {
        int length = lengthOfLongestSubstring(s);
        System.out.println(length);
    }
}
