package org.example.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Changhee Choi
 * @since 19/05/2021
 */
public class TopKFrequentElements {
  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> frequencyMap = new HashMap<>();
    PriorityQueue<NumberFrequency> pq = new PriorityQueue<>();
    int[] answer = new int[k];

    for (int num : nums) {
      frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
    }

    frequencyMap
        .entrySet()
        .forEach(entry -> pq.add(new NumberFrequency(entry.getKey(), entry.getValue())));

    for (int i = 0; i < k; i++) {
      answer[i] = pq.poll().num;
    }

    return answer;
  }

  class NumberFrequency implements Comparable<NumberFrequency> {
    int num;
    int frequency;

    public NumberFrequency(int num, int frequency) {
      this.num = num;
      this.frequency = frequency;
    }

    @Override
    public int compareTo(NumberFrequency o) {
      return o.frequency - this.frequency;
    }
  }
}
