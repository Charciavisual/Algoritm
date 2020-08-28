package org.example.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Longest Substring Without Repeating Characters
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * @author Changhee Choi
 * @since 28/08/2020
 */
public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int answer = 0;
        Queue<Character> substring = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (substring.contains(c)) {
                answer = Math.max(answer, substring.size());
                //같은 Character를 만날때까지 큐에서 제거
                while (substring.peek() != c) {
                    substring.poll();
                }
                //같은 Character를 제거
                substring.poll();
            }
            substring.add(c);
        }

        answer = Math.max(answer, substring.size());
        return answer;
    }

    public void runTest(String s) {
        int length = lengthOfLongestSubstring(s);
        System.out.println(length);
    }
}
