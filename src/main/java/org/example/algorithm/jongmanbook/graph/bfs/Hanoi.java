package org.example.algorithm.jongmanbook.graph.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 하노이탑 양방향 탐색 구현
 */
public class Hanoi {

    /* 해당 상태값에서 원반이 속한 기둥 번호를 반환 */
    private int get(int state, int index) {
        return (state >> (index * 2)) & 3;
        //return state & ( 3 << (index *2));
    }

    /* 원반을 새로운 기둥으로 옮긴 이후의 상태를 반환 */
    private int set(int state, int index, int value) {
        return (state & ~(3 << (index * 2))) | (value << (index * 2));
    }

    /* 부호(진행 방향)을 반환 */
    private int sign(int count) {
        if (count == 0) return 0;
        return count > 0 ? 1 : -1;
    }

    /* 탐색 횟수(절대값) 증가 */
    private int incCount(int count) {
        if (count < 0) return count - 1;
        return count + 1;
    }

    /* 원반 이동 횟수 탐색 */
    private int countMoveDisk(int numOfDisk, int start, int dest) {

        if(start == dest) return 0;
        
        
        Queue<Integer> queue = new LinkedList<>();
        int[] count = new int[1 << (numOfDisk * 2)];

        queue.add(start);
        count[start] = 1;
        
        queue.add(dest);
        count[dest] = -1;
        
        while(!queue.isEmpty()) {
            //현재 상태
            int curState = queue.poll();
            
            //현재 상태의 각 기둥별 최상위 원반 번호를 계산
            int[] topOfColumn = {-1,-1,-1,-1};
            for (int disk = numOfDisk - 1; disk >= 0; disk--) {
                topOfColumn[get(curState, disk)] = disk;
            }

            for (int i = 0; i < 4; i++) {
                if (topOfColumn[i] != -1) {
                    for (int j = 0; j < 4; j++) {
                        if (i != j && (topOfColumn[j] == -1 || topOfColumn[i] < topOfColumn[j])) {
                            int afterState = set(curState, topOfColumn[i], j);
                            //처음 만든 상태라면 이동 횟수를 저장하고 상태를 큐에 넣는다.
                            if (count[afterState] == 0) {
                                count[afterState] = incCount(count[curState]);
                                queue.add(afterState);
                            }
                            //반대 진행 방향과 같은 상태를 만들었다면 중간에서 만난 것
                            //같은 상태인지만 체크하면 동일한 진행 방향에서 만들어지는 중복된 상태인 경우도 있다
                            else if (sign(count[curState]) != sign(count[afterState])) {
                                return Math.abs(count[curState]) + Math.abs(count[afterState]) - 1;
                            }
                        }
                    }
                }
            }
        }
        //탐색 실패
        return -1;
    }

    /*

     */
    public void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numOfTest = Integer.parseInt(br.readLine());

        for (int test = 0; test < numOfTest; test++) {

            int numOfDisk = Integer.parseInt(br.readLine());

            //start 상태 초기화
            int start = 0;
            for (int col = 0; col < 4; col++) {
                //0: 원반갯수, [1..n]:원반번호
                int[] disks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int disk = 0; disk < disks[0]; disk++) {
                    //디스크 번호 [1...n]을 [0...n-1]로 변경하여 저장
                    start = set(start, disks[disk + 1]-1, col);
                }
            }

            //dest 상태 초기화
            int dest = 0;
            for (int col = 0; col < 4; col++) {
                //0: 원반갯수, [1..n]:원반번호
                int[] disks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int disk = 0; disk < disks[0]; disk++) {
                    //입력의 디스크 번호 [1...n]을 [0...n-1]로 변경하여 저장
                    dest = set(dest, disks[disk + 1]-1, col);
                }
            }

            int result = countMoveDisk(numOfDisk, start, dest);
            System.out.println(result);
        }

        br.close();
    }
}
