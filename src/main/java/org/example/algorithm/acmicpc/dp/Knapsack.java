package org.example.algorithm.acmicpc.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 동적계획법 - 평범한 배낭
 * https://www.acmicpc.net/problem/12865
 *
 * @author Changhee Choi
 * @since 07/07/2020
 */
public class Knapsack {

    private int n;
    private int maxWeight;
    private int[] weights;
    private int[] values;
    private int[][] cache;

    public int solution() {
        int answer = 0;
        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException("초기화 과정에서 입력오류가 발생했습니다.");
        }

        answer = calcMaxValue();

        return answer;
    }

    private void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] params = br.readLine().split(" ");
        n = Integer.parseInt(params[0]);
        maxWeight = Integer.parseInt(params[1]);

        weights = new int[n + 1];
        values = new int[n + 1];
        cache = new int[n + 1][maxWeight + 1];

        for (int i = 1; i <= n; i++) {
            String[] productParams = br.readLine().split(" ");
            int weight = Integer.parseInt(productParams[0]);
            int value = Integer.parseInt(productParams[1]);
            weights[i] = weight;
            values[i] = value;
        }

        br.close();
    }

    private int calcMaxValue() {
        int ret = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= maxWeight; j++) {
                /**
                 * 만들려는 무게 j가 i번째 물건의 무게보다 작은 경우라면
                 * 무게 j를 만드는데 i번째 물건을 포함할 수 없으므로
                 * i-1번째 물건까지 이용하여 무게 j를 만들었을때의 가치 최대합과 같다.
                 */
                if (j < weights[i]) {
                    cache[i][j] = cache[i - 1][j];
                }
                /**
                 * i번째 물건을 포함시킬 수 있다면
                 * i번째 물건을 포함하지 않았을 때 vs 포함 했을때의 가치 최대합을 비교하여 더 큰쪽을 선택한다.
                 */
                else {
                    cache[i][j] = Math.max(cache[i - 1][j], cache[i - 1][j - weights[i]] + values[i]);
                }
            }
        }

        ret = cache[n][maxWeight];
        return ret;
    }
}
