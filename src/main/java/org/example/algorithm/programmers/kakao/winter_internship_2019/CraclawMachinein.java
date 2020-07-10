package org.example.algorithm.programmers.kakao.winter_internship_2019;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 카카오 2019 겨울 인턴십 - 크레인 인형뽑기
 * https://programmers.co.kr/learn/courses/30/lessons/64061
 *
 * @author Changhee Choi
 * @since 10/07/2020
 */
public class CraclawMachinein {

    private int n;
    private Queue<Integer>[] boardQueues;
    private Stack<Integer> bucket;

    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        init(board);
        answer = moveToBucket(moves);
        return answer;
    }

    private void init(int[][] board) {
        bucket = new Stack<>();
        n = board.length;
        boardQueues = new Queue[n];

        for (int i = 0; i < n; i++) {
            boardQueues[i] = new LinkedList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[j][i] > 0) {
                    boardQueues[i].add(board[j][i]);
                }
            }
        }
    }

    private int moveToBucket(int[] moves) {
        int count = 0;

        for (int i = 0; i < moves.length; i++) {
            if (boardQueues[moves[i] - 1].size() == 0) {
                continue;
            }

            int doll = boardQueues[moves[i] - 1].poll();
            if (bucket.size() > 0 && bucket.peek() == doll) {
                bucket.pop();
                count += 2;
            } else {
                bucket.push(doll);
            }
        }

        return count;
    }
}
