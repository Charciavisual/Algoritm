package programmers.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class Network {

    public int solution(int n, int[][] computers) {
        int answer = 0;
        answer = countNetwork(n, computers);
        return answer;
    }

    private int countNetwork(int n, int[][] computers) {

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        int count = 0;
        int cur = 0;

        queue.add(cur);
        visited[cur] = true;

        while (!queue.isEmpty()) {
            cur = queue.poll();

            for (int i = 0; i < n; i++) {
                //현재 컴퓨터와 연결된 컴퓨터 중 아직 방문하지 않은 컴퓨터들을 큐에 추가
                if (computers[cur][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }

            // 현재 네트워크에 더이상 연결된 컴퓨터가 없다면 count +1
            if (queue.isEmpty()) {
                count++;
                // 아직 남은 컴퓨터가 있다면 새로운 네트워크로 만듬
                for (int j = 0; j < n; j++) {
                    if (!visited[j]) {
                        queue.add(j);
                        visited[j] = true;
                        break;
                    }
                }
            }
        }
        return count;
    }
}
