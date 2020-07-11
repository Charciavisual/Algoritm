package org.example.algorithm.programmers.dfs_bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ChangeWord {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        answer = changeWord(words, begin, target);
        return answer == 51 ? 0 : answer;
    }

    private int changeWord(String[] words, String begin, String target) {
        Queue<Node> queue = new LinkedList<>();
        boolean[] used = new boolean[words.length];

        Arrays.fill(used, false);
        queue.add(new Node(begin, 0));

        int ret = 51; // words는 최대 50개의 단어를 가지므로 count 는 항상 51 보다 작다.

        while(!queue.isEmpty()) {
            Node cur = queue.poll();

            if(cur.getWord().equals(target)) {
                ret = Math.min(ret, cur.getDistance());
                break;
            }

            for(int i=0; i<words.length; i++) {
                if(!used[i] && checkChange(cur.getWord(), words[i])) {
                    used[i] = true;
                    queue.add(new Node(words[i], cur.getDistance() + 1));
                }
            }
        }

        return ret;
    }

    /* 현재 단어를 다음 단어로 변경할 수 있는지 체크 */
    private boolean checkChange(String cur, String next) {
        int count = 0;
        for(int i=0; i<cur.length(); i++) {
            if(cur.charAt(i) != next.charAt(i)) count ++;
        }

        if(count == 1) return true;
        return false;
    }

    class Node {
        private String word;
        private int distance;

        public Node(String word, int distance) {
            this.word = word;
            this.distance = distance;
        }

        public String getWord(){
            return this.word;
        }
        public int getDistance(){
            return this.distance;
        }
    }
}
