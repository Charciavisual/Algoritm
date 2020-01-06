package org.example.algorithm.template;

import java.util.Stack;

public class LIS{
    class Pair {
        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }
        public int getValue() {
            return value;
        }
    }

    private Pair[] lis, tracking;
    private int len;

    public LIS(int n) {
        lis = new Pair[n + 1];
        tracking = new Pair[n];
        lis[0] = new Pair(-1, -1); //시작 지점을 위한 더미 데이터
    }

    public boolean add(int pos, int value) {

        Pair pair_data = new Pair(pos, value);

        if (pair_data.getValue() > lis[len].getValue()) { //현재 값이 lis 마지막 원소보다 크면
            lis[len + 1] = pair_data; //lis의 마지막 원소로 저장
            tracking[pos] = lis[len]; //현재 데이터가 이전 원소를 가리키도록
            len++;
        } else { //마지막 원소보다 작을때
            int target_idx = lower_bound(1, len, pair_data.getValue()); //lis 원소 중 현재 데이터보다 큰 데이터중 가장 작은 값 또는 현재 데이터와 같은 데이터의 위치를 찾는다.
            lis[target_idx] = pair_data; //위에서 찾은 위치의 데이터를 현재 데이터로 교체;
            tracking[pos] = lis[target_idx - 1]; //현재 데이터가 이전 원소를 가리키도록
        }

        return true;
    }

    private int lower_bound(int start, int end, int target) {
        //이분 탐색으로 target 보다 큰 데이터중 가장 작은 값 또는 현재 데이터와 같은 데이터의 위치를 찾는다.
        while (start < end) {
            int mid = (start + end) / 2;
            if (lis[mid].getValue() >= target)
                end = mid;
            else start = mid + 1;
        }
        return end;
    }

    public void printLIS() {
        Stack<Pair> stack = new Stack<>();

        Pair cur = lis[len]; //lis 의 가장 마지막 원소에서 부터 시작
        stack.push(cur);
        Pair prev = tracking[cur.getKey()]; //현재 원소가 가리키는 원소를 가져온다.

        while (prev.getKey() != -1) { //prev가 시작 데이터가 아닌 경우 루프 진행
            stack.push(prev);
            cur = prev;
            prev = tracking[cur.getKey()];
        }

        System.out.println("LIS length : " + stack.size());
        System.out.print("LIS elements : ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().getValue() + " ");
        }
        System.out.println();
    }
}
