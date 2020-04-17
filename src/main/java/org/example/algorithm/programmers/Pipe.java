package org.example.algorithm.programmers;

import java.util.Stack;

/**
 * 프로그래머스 - 쇠막대기
 * https://programmers.co.kr/learn/courses/30/lessons/42585
 *
 * @author Changhee Choi
 * @since 17/04/2020
 */
public class Pipe {
    /**
     * @param arrangement 쇠막대기와 레이저의 배치를 표현한 문자열 - () : 레이저 , ( : 쇠막대기 왼쪽 끝, ) : 쇠막대기 오른쪽 끝
     * @return 잘린 쇠막대기 조각의 총 개수
     */
    public int solution(String arrangement) {
        int answer = 0;

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder(arrangement);

        int overlapped = 0;

        for (int i = 0; i < sb.length(); i++) {
            Character curChar = sb.charAt(i);

            if (curChar == '(') {
                stack.push(curChar);
                overlapped++;
                continue;
            }

            Character lastChar = stack.pop();
            //현재 닫는 괄호가 레이저를 표시한다면 앞의 여는 괄호는 겹치는 막대 개수에서 제외시키고,
            //현재 닫는 괄호가 막대의 오른쪽 끝을 표시한다면 해당 막대는 더이상 겹쳐지지 않으므로 겹치는 막대 개수에서 제외시킨다.
            overlapped--;

            if (lastChar == '(') {
                //겹쳐진 개수만큼 조각이 발생
                answer += overlapped;
            } else {
                //더이상 겹쳐지지 않는 막대의 조각을 더해준다
                answer++;
            }

            //스택에 저장
            stack.push(lastChar);
            stack.push(curChar);
        }
        return answer;
    }
}
