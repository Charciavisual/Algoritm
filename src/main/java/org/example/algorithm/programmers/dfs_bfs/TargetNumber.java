package org.example.algorithm.programmers.dfs_bfs;

public class TargetNumber {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        answer = calc(numbers, target, 0, 0);
        return answer;
    }

    private int calc(int[] numbers, int target, int result, int idx) {
        if (idx == numbers.length) { //마지막 수까지 다 더했을 때
            if (result == target) return 1; //계산된 결과가 target 과 같으면 방법의 수 + 1
            else return 0;
        }

        int ret = 0;
        ret += calc(numbers, target, result + numbers[idx], idx + 1); //idx 번째 수를 더하는 경우
        ret += calc(numbers, target, result - numbers[idx], idx + 1); //idx 번째 수를 빼는 경우

        return ret;
    }
}
