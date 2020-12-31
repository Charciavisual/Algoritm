package org.example.algorithm.leetcode.dp;

import java.util.*;

/**
 * @link https://leetcode.com/problems/cheapest-flights-within-k-stops/
 * @author Changhee Choi
 * @since 30/12/2020
 */
public class CheapestFlightsWithinKStops {

  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    Map<Integer, List<Path>> pathMap = new HashMap<>();

    for (int[] flight : flights) {
      int from = flight[0];
      int to = flight[1];
      int weight = flight[2];

      List<Path> nextPaths = pathMap.computeIfAbsent(from, city -> new ArrayList<>());
      nextPaths.add(new Path(to, 0, weight));
    }

    return dijkstra(pathMap, n, src, dst, K);
  }

  private int dijkstra(Map<Integer, List<Path>> pathMap, int n, int src, int dst, int K) {
    PriorityQueue<Path> q = new PriorityQueue<>(Comparator.comparingInt(path -> path.weight));
    q.add(new Path(src, 0, 0));

    while (!q.isEmpty()) {
      Path curPath = q.poll();

      if (curPath.stop - 1 > K) {
        continue;
      }

      if (curPath.city == dst) {
        return curPath.weight;
      }

      List<Path> nextPaths = pathMap.get(curPath.city);

      if (nextPaths == null) {
        continue;
      }

      for (Path nextPath : nextPaths) {
        q.add(new Path(nextPath.city, curPath.stop + 1, curPath.weight + nextPath.weight));
      }
    }

    return -1;
  }

  class Path {
    int city;
    int stop;
    int weight;

    public Path(int city, int stop, int weight) {
      this.city = city;
      this.stop = stop;
      this.weight = weight;
    }
  }
}
