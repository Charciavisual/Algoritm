package programmers.dfs_bfs;

public class ChangeWord {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        answer = changeWord(words, target, begin, new boolean[words.length], 0);
        return answer == 51 ? 0 : answer;
    }

    private int changeWord(String[] words, String target, String curStr, boolean[] visited, int count) {

        if (curStr.equals(target))
            return count;

        int ret = 51; // words는 최대 50개의 단어를 가지므로 count 는 항상 51 보다 작다.

        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && checkChange(curStr, words[i])) {
                visited[i] = true;
                ret = Integer.min(ret, changeWord(words, target, words[i], visited, count + 1));
                visited[i] = false;
            }
        }

        return ret;
    }

    /* 현재 단어를 다음 단어로 변경할 수 있는지 체크 */
    private boolean checkChange(String cur, String next) {
        int count = 0;
        for (int i = 0; i < cur.length(); i++) {
            if (cur.charAt(i) != next.charAt(i)) count++;
        }

        if (count == 1) return true;
        return false;
    }
}
