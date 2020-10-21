package org.example.algorithm.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/is-subsequence/
 *
 * @author Changhee Choi
 * @since 21/10/2020
 */
public class IsSubsequence {
    /**
     * s가 한개일때는 일대일 검사 알고리즘으로도 풀이가 가능
     * s가 여러개로 들어올때도 처리가 가능하도록 구현하려면?
     * t의 문자열에 대해서 각 알파벳들이 몇 번째 위치에 등장하는지 전처리를 한 후,
     * 각 s가 subsequence 인지 검사한다.
     * 모든 알파벳이 이전 알파벳보다 이후에 등장하는지 검사하는 방식으로 구현 (바이너리 서치 이용)
     *
     * @param s substring
     * @param t original string
     * @return s is a subsequence of t?
     */
    public boolean isSubsequence(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        List<Integer>[] indices = new List[26];

        for (int i = 0; i < tLen; i++) {
            int index = t.charAt(i) - 97;
            if (indices[index] == null) {
                indices[index] = new ArrayList<>();
            }
            indices[index].add(i);
        }

        int lastFound = -1;
        for (int i = 0; i < sLen; i++) {
            int index = s.charAt(i) - 97;
            if (indices[index] == null) {
                return false;
            }
            int foundIdx = binarySearch(indices[index], lastFound);
            if (foundIdx == -1) {
                return false;
            }
            lastFound = foundIdx;
        }

        return true;
    }

    private int binarySearch(List<Integer> indices, int lastFound) {
        int left = 0;
        int right = indices.size() - 1;
        int result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (indices.get(mid) > lastFound) {
                result = indices.get(mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}
