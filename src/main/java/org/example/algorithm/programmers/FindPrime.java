package org.example.algorithm.programmers;

import java.util.HashSet;

/**
 * 프로그래머스 - 소수 찾기
 * https://programmers.co.kr/learn/courses/30/lessons/42839
 *
 * @author Changhee Choi
 * @since 09/04/2020
 */
public class FindPrime {
    public int solution(String numbers) {
        int answer = 0;

        String[] num_arr = numbers.split("");

        HashSet<Integer> checked = new HashSet<>();

        for (int i = 0; i < num_arr.length; i++) {
            answer += makeNum(num_arr, checked, "", 0, i + 1);
        }

        return answer;
    }

    /* 숫자 조합을 생성하는 메소드 */
    private int makeNum(String[] arr, HashSet<Integer> checked, String numString, int visited, int toPick) {

        if (toPick == 0) {
            int num = Integer.parseInt(numString);

            //중복검사
            if (checked.contains(num)) return 0;
            checked.add(num);
            //소수 판별
            if (isPrime(num)) return 1;
            return 0;
        }

        int ret = 0;

        // 자기자신을 중복선택하지 않는 순열 생성 (visitied 사용여부에 따라)
        for (int i = 0; i < arr.length; i++) {
            if ((visited & (1 << i)) > 0)
                continue;
            ret += makeNum(arr, checked, numString + arr[i], visited + (1 << i), toPick - 1);
        }

        return ret;
    }

    /* 소수판별 메소드 */
    private boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2) return true;

        //2를 제외한 모든 짝수는 소수가 아님
        if (num % 2 == 0) return false;

        /**
         * num 이 p * q 라고 할때 한 수는 항상 sqrt(num) 이하의 값을 갖는다. (ex, num = 24, p = [1, 2, 3, 4], q = [6, 8, 12, 24])
         * 따라서 num 이 sqrt(num) 이하의 값중 하나로 나눠지는지 체크한다. (ex, 24 가 4 이하의 숫자로 나눠지는지 체크,, 1,2 는 예외)
         */
        int sqrtn = (int) Math.sqrt(num);
        for (int i = 3; i <= sqrtn; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
