package org.example.algorithm.jongmanbook.graph.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SortingGame {

    HashMap<ArrayList, Integer> countToSort = new HashMap<>();

    /* 원본 리스트에서 i~j를 뒤집은 결과를 리턴한다 */
    private ArrayList<Integer> reverse(ArrayList<Integer> list, int start, int end){
        ArrayList<Integer> tempList = new ArrayList<>();
        tempList.addAll(list);
        while(start < end) {
            int temp = tempList.get(start);
            tempList.set(start, tempList.get(end));
            tempList.set(end, temp);
            start++;
            end--;
        }

        return tempList;
    }

    /* 시작값(정렬된 상태)에서 특정 상태로 변하려면 몇번 뒤집어야 하는지 미리 계산하는 전처리 메소드 */
    public void preCalc(int n) {
        countToSort.clear();

        //순열의 상태를 나타내는 리스트
        ArrayList<Integer> perm = new ArrayList<>(n);

        // 정렬된 기본 순열 생성 0123...n-1 (시작상태)
        for(int i=0; i<n; i++){
            perm.add(i,i);
        }

        Queue<ArrayList> queue = new LinkedList<>();
        queue.add(perm); //시작 상태를 큐에 저장
        countToSort.put(perm, 0); //시작 상태는 뒤집기 횟수 0

        while(!queue.isEmpty()){
            ArrayList<Integer> cur = queue.poll();
            int count = countToSort.get(cur);

            for(int i=0; i<n; i++) {
                for(int j=i+1; j<n; j++){
                    //순열의 i~j를 뒤집는다.
                    ArrayList<Integer> afterReverse = reverse(cur, i, j);
                    //뒤집은 결과가 map에 없으면 저장
                    if(countToSort.get(afterReverse) == null){
                        countToSort.put(afterReverse, count+1);
                        queue.add(afterReverse);
                    }
                }
            }
        }
    }

    /* 입력 데이터를 [0..n-1] 범위로 변환하고 전처리 된 뒤집기 횟수를 리턴한다 */
    private int targetCount(int size, int[] data) {
        ArrayList<Integer> target = new ArrayList<>(size);
        //입력 데이터를 상대적 크기값으로 변경하여 [0...n-1]로 만든다
        for(int i=0; i<size; i++) {
            int smaller = 0;
            for(int j=0; j<size; j++) {
                if(data[j] < data[i])
                    smaller++;
            }
            target.add(i, smaller);
        }
        return countToSort.get(target);
    }

    /**
     * 입력을 처리하고 각 케이스 별 답을 출력한다.
     * input
     * 3
     * 8
     * 1 2 3 4 8 7 6 5
     * 4
     * 3 9999 1 2
     * 3
     * 1000 2000 3000
     *
     * output
     * 1
     * 2
     * 0
     * */
    public void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int tcase = 0; tcase < testCase; tcase++) {
            int size = Integer.parseInt(br.readLine());
            int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            preCalc(size);
            System.out.println(targetCount(size, data));
        }
    }
}
