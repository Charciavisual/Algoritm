package org.example.algorithm.leetcode.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Changhee Choi
 * @since 10/05/2021
 */
public class PartitionLabels {
  public List<Integer> partitionLabels(String s) {

    List<Integer> partitions = new ArrayList<>();

    Map<Character, Integer> startIdxMap = new HashMap<>();
    Map<Character, Integer> lastIdxMap = new HashMap<>();

    char[] chars = s.toCharArray();

    startIdxMap.put(chars[0], 0);

    // 알파벳의 시작위치, 마지막위치 기록
    for (int i = 1; i < chars.length; i++) {
      if (startIdxMap.containsKey(chars[i])) {
        lastIdxMap.put(chars[i], i); // 마지막위치 갱신
      } else {
        startIdxMap.put(chars[i], i);
      }
    }

    int left = 0;
    int partitionSize = 1;

    while (left < s.length()) {
      // 투포인터 사용, left: 알파벳의 시작위치, right: 알파벳의 마지막위치
      // 알파벳이 한번만 등장하는 경우: 시작위치 = 마지막위치
      int right = lastIdxMap.getOrDefault(chars[left], startIdxMap.get(chars[left]));

      // 어떤 알파벳의 시작위치 ~ 마지막위치 사이에 등장하는 알파벳은 같은 파티션에 해당한다.
      while (left < right) {
        // left 포인터를 한칸씩 이동하면서 더 긴 마지막위치를 갖는 알파벳이 있다면 해당 위치로 right를 갱신한다. (파티션확장)
        left += 1;
        int lastIdx = lastIdxMap.getOrDefault(chars[left], startIdxMap.get(chars[left]));
        if (lastIdx > right) {
          right = lastIdx;
        }
        partitionSize += 1;
      }
      // left = right: 파티션 처리 완료
      partitions.add(partitionSize);
      // 새로운 파티션 작업 시작
      partitionSize = 1;
      left += 1;
    }

    return partitions;
  }
}
